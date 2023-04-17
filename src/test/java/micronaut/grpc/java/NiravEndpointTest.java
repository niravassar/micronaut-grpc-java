package micronaut.grpc.java;

import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
@MicronautTest
class NiravEndpointTest {

    @Inject
    NiravServiceGrpc.NiravServiceBlockingStub blockingStub;

    @Test
    void testNiravStuff() {
        final NiravRequest request = NiravRequest.newBuilder()
            .setName("Nirav")
            .build();
        assertEquals(
            "Hello Nirav",
            blockingStub.send(request)
                .getMessage()
        );
    }

}

@Factory
class Clients {

    @Bean
    NiravServiceGrpc.NiravServiceBlockingStub  blockingStub(
        @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return NiravServiceGrpc.newBlockingStub(
            channel
        );
    }
}