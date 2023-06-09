package me.n1ar4.fake.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: config.proto")
public final class RPCStartGrpc {

  private RPCStartGrpc() {}

  public static final String SERVICE_NAME = "me.n1ar4.fake.rpc.RPCStart";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<RPCNull,
      RPCResp> METHOD_START =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "me.n1ar4.fake.rpc.RPCStart", "start"),
          io.grpc.protobuf.ProtoUtils.marshaller(RPCNull.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(RPCResp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<RPCPort,
      RPCResp> METHOD_STOP =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "me.n1ar4.fake.rpc.RPCStart", "stop"),
          io.grpc.protobuf.ProtoUtils.marshaller(RPCPort.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(RPCResp.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RPCStartStub newStub(io.grpc.Channel channel) {
    return new RPCStartStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RPCStartBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RPCStartBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static RPCStartFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RPCStartFutureStub(channel);
  }

  /**
   */
  public static abstract class RPCStartImplBase implements io.grpc.BindableService {

    /**
     */
    public void start(RPCNull request,
                      io.grpc.stub.StreamObserver<RPCResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_START, responseObserver);
    }

    /**
     */
    public void stop(RPCPort request,
                     io.grpc.stub.StreamObserver<RPCResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STOP, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_START,
            asyncUnaryCall(
              new MethodHandlers<
                RPCNull,
                RPCResp>(
                  this, METHODID_START)))
          .addMethod(
            METHOD_STOP,
            asyncUnaryCall(
              new MethodHandlers<
                RPCPort,
                RPCResp>(
                  this, METHODID_STOP)))
          .build();
    }
  }

  /**
   */
  public static final class RPCStartStub extends io.grpc.stub.AbstractStub<RPCStartStub> {
    private RPCStartStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RPCStartStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RPCStartStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RPCStartStub(channel, callOptions);
    }

    /**
     */
    public void start(RPCNull request,
                      io.grpc.stub.StreamObserver<RPCResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_START, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stop(RPCPort request,
                     io.grpc.stub.StreamObserver<RPCResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_STOP, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RPCStartBlockingStub extends io.grpc.stub.AbstractStub<RPCStartBlockingStub> {
    private RPCStartBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RPCStartBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RPCStartBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RPCStartBlockingStub(channel, callOptions);
    }

    /**
     */
    public RPCResp start(RPCNull request) {
      return blockingUnaryCall(
          getChannel(), METHOD_START, getCallOptions(), request);
    }

    /**
     */
    public RPCResp stop(RPCPort request) {
      return blockingUnaryCall(
          getChannel(), METHOD_STOP, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RPCStartFutureStub extends io.grpc.stub.AbstractStub<RPCStartFutureStub> {
    private RPCStartFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RPCStartFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RPCStartFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RPCStartFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RPCResp> start(
        RPCNull request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_START, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RPCResp> stop(
        RPCPort request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_STOP, getCallOptions()), request);
    }
  }

  private static final int METHODID_START = 0;
  private static final int METHODID_STOP = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RPCStartImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RPCStartImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_START:
          serviceImpl.start((RPCNull) request,
              (io.grpc.stub.StreamObserver<RPCResp>) responseObserver);
          break;
        case METHODID_STOP:
          serviceImpl.stop((RPCPort) request,
              (io.grpc.stub.StreamObserver<RPCResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class RPCStartDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return FakeServerRPC.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RPCStartGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RPCStartDescriptorSupplier())
              .addMethod(METHOD_START)
              .addMethod(METHOD_STOP)
              .build();
        }
      }
    }
    return result;
  }
}
