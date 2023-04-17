package micronaut.grpc.java;

import io.grpc.examples.helloworld.GreetingService;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Singleton;

@Singleton
public class NiravEndpoint extends NiravServiceGrpc.NiravServiceImplBase {

    private final GreetingService greetingService;

    public NiravEndpoint(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void send(NiravRequest request, StreamObserver<NiravReply> responseObserver) {

        final String message = greetingService.sayHello(request.getName());
        NiravReply reply = NiravReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
