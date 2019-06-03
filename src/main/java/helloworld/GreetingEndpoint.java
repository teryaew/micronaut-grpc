package helloworld;

// tag::imports[]

import io.grpc.stub.StreamObserver;
import javax.inject.Singleton;

// tag::clazz[]
@Singleton
public class GreetingEndpoint extends GreeterGrpc.GreeterImplBase { // <1>

    private final GreetingService greetingService;

    // <2>
    public GreetingEndpoint(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        // <3>
        final String message = greetingService.sayHello(request.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
// end::clazz[]
