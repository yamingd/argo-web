// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PAppRequest.proto

package com.argo.sdk.protobuf;

public interface PAppRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PAppRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string authId = 1;</code>
   */
  boolean hasAuthId();
  /**
   * <code>required string authId = 1;</code>
   */
  java.lang.String getAuthId();
  /**
   * <code>required string authId = 1;</code>
   */
  com.google.protobuf.ByteString
      getAuthIdBytes();

  /**
   * <code>optional string sessionId = 2;</code>
   */
  boolean hasSessionId();
  /**
   * <code>optional string sessionId = 2;</code>
   */
  java.lang.String getSessionId();
  /**
   * <code>optional string sessionId = 2;</code>
   */
  com.google.protobuf.ByteString
      getSessionIdBytes();

  /**
   * <code>optional int32 version = 3;</code>
   */
  boolean hasVersion();
  /**
   * <code>optional int32 version = 3;</code>
   */
  int getVersion();

  /**
   * <code>optional string sign = 4;</code>
   */
  boolean hasSign();
  /**
   * <code>optional string sign = 4;</code>
   */
  java.lang.String getSign();
  /**
   * <code>optional string sign = 4;</code>
   */
  com.google.protobuf.ByteString
      getSignBytes();

  /**
   * <code>optional string nonce = 5;</code>
   */
  boolean hasNonce();
  /**
   * <code>optional string nonce = 5;</code>
   */
  java.lang.String getNonce();
  /**
   * <code>optional string nonce = 5;</code>
   */
  com.google.protobuf.ByteString
      getNonceBytes();

  /**
   * <code>optional string userAgent = 6;</code>
   */
  boolean hasUserAgent();
  /**
   * <code>optional string userAgent = 6;</code>
   */
  java.lang.String getUserAgent();
  /**
   * <code>optional string userAgent = 6;</code>
   */
  com.google.protobuf.ByteString
      getUserAgentBytes();

  /**
   * <code>optional string path = 7;</code>
   */
  boolean hasPath();
  /**
   * <code>optional string path = 7;</code>
   */
  java.lang.String getPath();
  /**
   * <code>optional string path = 7;</code>
   */
  com.google.protobuf.ByteString
      getPathBytes();

  /**
   * <code>repeated .PAppRequestParam param = 8;</code>
   */
  java.util.List<com.argo.sdk.protobuf.PAppRequestParam> 
      getParamList();
  /**
   * <code>repeated .PAppRequestParam param = 8;</code>
   */
  com.argo.sdk.protobuf.PAppRequestParam getParam(int index);
  /**
   * <code>repeated .PAppRequestParam param = 8;</code>
   */
  int getParamCount();
  /**
   * <code>repeated .PAppRequestParam param = 8;</code>
   */
  java.util.List<? extends com.argo.sdk.protobuf.PAppRequestParamOrBuilder> 
      getParamOrBuilderList();
  /**
   * <code>repeated .PAppRequestParam param = 8;</code>
   */
  com.argo.sdk.protobuf.PAppRequestParamOrBuilder getParamOrBuilder(
      int index);
}
