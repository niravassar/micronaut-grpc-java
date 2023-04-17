package io.grpc.examples.helloworld;

import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
@MicronautTest
class GreetingEndpointTest {

    @Inject
    GreeterGrpc.GreeterBlockingStub blockingStub;

    @Test
    void testHelloWorld() {
        final HelloRequest request = HelloRequest.newBuilder()
            .setName("Fred")
            .build();
        assertEquals(
            "Hello Fred",
            blockingStub.sayHello(request)
                .getMessage()
        );
    }

}

@Factory
class Clients {

    @Bean
    GreeterGrpc.GreeterBlockingStub blockingStub(
        @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return GreeterGrpc.newBlockingStub(
            channel
        );
    }
}