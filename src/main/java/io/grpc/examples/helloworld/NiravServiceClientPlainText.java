package io.grpc.examples.helloworld;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import micronaut.grpc.java.NiravReply;
import micronaut.grpc.java.NiravRequest;
import micronaut.grpc.java.NiravServiceGrpc;

public class NiravServiceClientPlainText {

    private final NiravServiceGrpc.NiravServiceBlockingStub blockingStub;

    public NiravServiceClientPlainText(Channel channel) {
        blockingStub = NiravServiceGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        NiravRequest request = NiravRequest.newBuilder().setName(name).build();
        NiravReply response = blockingStub.send(request);
        System.out.println("Greeting for Nirav Service: " + response.getMessage());
    }

    public static void main(String[] args) {
        int port = 50051;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();
        NiravServiceClientPlainText client = new NiravServiceClientPlainText(channel);
        client.greet("Nirav Service Plain Text");
    }
}
