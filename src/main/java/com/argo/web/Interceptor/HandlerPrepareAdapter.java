package com.argo.web.Interceptor;

import com.argo.security.SessionCookieHolder;
import com.argo.security.exception.UnauthorizedException;
import com.argo.web.CSRFToken;
import com.argo.web.MvcController;
import com.argo.web.WebConfig;
import com.argo.web.WebContext;
import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yamingdeng
 * Date: 13-11-17
 * Time: 上午9:56
 */
public class HandlerPrepareAdapter extends HandlerInterceptorAdapter {

    public static final String X_MOBILE = "X-app";
    public static final String GET = "GET";
    public static final String CSRF = "csrf";
    public static final String PATH_ASSETS = "/assets/";

    public static HandlerPrepareAdapter instance = null;

    private static final String cookieId = "_after";

    protected static Logger logger = LoggerFactory.getLogger(HandlerPrepareAdapter.class);

    public HandlerPrepareAdapter() {
        instance = this;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        if (url.startsWith(PATH_ASSETS)){
            return;
        }

        long ts = System.currentTimeMillis() - WebContext.getContext().getStartAt();
        logger.info("handle {}. method={}. duration={}ms", request.getRequestURI(), request.getMethod(), ts);
    }

    /**
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        return ExceptionGlobalResolver.XMLHTTP_REQUEST.equalsIgnoreCase(request.getHeader(ExceptionGlobalResolver.X_REQUESTED_WITH));
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {


        String url = request.getRequestURI();
        url = url.replace(request.getContextPath(), "");
        if (url.startsWith(PATH_ASSETS)){
            return true;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("x-app: {}", request.getHeader(X_MOBILE), request.getMethod());
        }
        boolean isMobile = request.getHeader(X_MOBILE) != null;


        WebContext.getContext().mark();
        WebContext.getContext().setRootPath(request.getContextPath());

        MvcController c = this.getController(handler);
        if (null == c){
            return false;
        }
        Object user = null;
        // 若是远程服务，则需要配置bean.authorizationService的实现
        // 若是本地服务，不需要配置
        try{

            user = c.verifyCookie(request, response);
            if (user != null){
                if (logger.isDebugEnabled()) {
                    logger.debug("preHandle verifyCookie is OK. User={}", user);
                }
            }
            if (isMobile){
                if (logger.isDebugEnabled()) {
                    logger.debug("preHandle verifyCookie. User={}", user);
                }
            }else {
                saveLastAccessUrl(request, response, true);
            }
//            String lastAccessUrl = this.getLastAccessUrl(request);
//            if (lastAccessUrl != null && !isMobile){
//                logger.info("redirect: {}", lastAccessUrl);
//                response.sendRedirect(lastAccessUrl);
//                return false;
//            }

        }catch (UnauthorizedException ex){
            saveLastAccessUrl(request, response, false);
            if (c.needLogin()) {
                throw ex;
            }
        }

        if (null != user) {
            request.setAttribute("currentUser", user);
        }
        if (!isMobile && request.getMethod().equalsIgnoreCase(GET)){
            request.setAttribute(CSRF, new CSRFToken(request, response));
        }

        c.verifyAccess(request.getRequestURI());
        c.init();
        request.setAttribute("menu", c.getMenu());

        return true;
    }

    /**
     * 保存上次访问地址
     * @param request 请求
     * @param response 响应
     */
    public static void saveLastAccessUrl(HttpServletRequest request, HttpServletResponse response, boolean referer){
        if (!GET.equalsIgnoreCase(request.getMethod()) || isAjax(request)){
            return;
        }

        String lastAccessUrl0 = null;

        if (referer) {

            String prefix = WebConfig.instance.getLogin();
            if (!request.getRequestURI().startsWith(prefix)){
                return;
            }

            lastAccessUrl0 = request.getHeader("Referer");
            if (null == lastAccessUrl0) {
                return;
            }

            if (!lastAccessUrl0.startsWith(WebConfig.instance.getDomain())) {
                return;
            }
        }else{
            lastAccessUrl0 = request.getRequestURI() + "?" + request.getQueryString();
        }

        String lastAccessUrl = BaseEncoding.base64Url().encode(lastAccessUrl0.getBytes());
        SessionCookieHolder.setCookie(response, cookieId, lastAccessUrl, 3600*8);
        if (logger.isDebugEnabled()){
            logger.debug("lastAccessUrl: {}", lastAccessUrl0);
        }
    }

    /**
     * 获取上次访问地址
     * @param request 请求
     * @return String
     */
    public static String getLastAccessUrl(HttpServletRequest request){
        Cookie cookie = SessionCookieHolder.getCookie(request, cookieId);
        if (cookie == null){
            return null;
        }
        byte[] lastAccessUrl = BaseEncoding.base64Url().decode(cookie.getValue());
        return new String(lastAccessUrl);
    }

    /**
     * 获取访问的控制类
     * @param handler
     * @return MvcController
     */
    private MvcController getController(Object handler){
        if(handler instanceof MvcController){
            return (MvcController)handler;
        }else if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod)handler;
            if(hm.getBean() instanceof MvcController){
                return (MvcController)hm.getBean();
            }
        }
        logger.error("!!!Handler is not instanceof MvcController");
        return null;
    }
}
