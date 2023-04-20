package io.grpc.examples.helloworld;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClientPlainText {

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClientPlainText(Channel channel) {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);
        System.out.println("Greeting: " + response.getMessage());
    }

    public static void main(String[] args) {
        int port = 50051;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();
        HelloWorldClientPlainText client = new HelloWorldClientPlainText(channel);
        client.greet("Nirav Plain Text");
    }
}
