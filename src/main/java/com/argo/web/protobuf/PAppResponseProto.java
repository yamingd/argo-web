// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PAppResponse.proto

package com.argo.web.protobuf;

public final class PAppResponseProto {
  private PAppResponseProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PAppResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PAppResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022PAppResponse.proto\"z\n\014PAppResponse\022\013\n\003" +
      "msg\030\001 \001(\t\022\021\n\tsessionId\030\002 \001(\t\022\017\n\007version\030" +
      "\003 \001(\005\022\014\n\004code\030\004 \002(\005\022\r\n\005total\030\005 \001(\005\022\014\n\004da" +
      "ta\030\006 \003(\014\022\016\n\006errors\030\007 \003(\tB,\n\025com.argo.web" +
      ".protobufB\021PAppResponseProtoP\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_PAppResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PAppResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PAppResponse_descriptor,
        new String[] { "Msg", "SessionId", "Version", "Code", "Total", "Data", "Errors", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
