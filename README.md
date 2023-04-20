## Micronaut with gRPC demo

This project is a demo example of grpc framework within the context of micronaut. The project is webapp with a simple grpc endpoint, and 
a simple java client is included which interacts with the endpoint. 

### Summary of Gprc

Grpc is a google backed technology which connects service in and across data center. It uses protocol buffers to communicate between 
backend microservices mainly. Protobuf is faster and more efficient and uses http/2 protocol, which is better then the previous http 1.0. Gprc
is growing to be the communication protocol of choice between backend polyglot microservices, although front end to backend with protobuf and gprc 
is not widely supported yet. 

Here are some good references to gain an introductory view of grpc:

IBM grpc video
- https://www.youtube.com/watch?v=hVrwuMnCtok

gRPC introduction
- https://www.youtube.com/watch?v=gnchfOojMk4

Intro
- https://grpc.io/docs/what-is-grpc/introduction/

### Implementation Details

Micronaut as a component project which helps start with grpc. You can go to the micronaut launch page and choose "gRPC Application" as the
application type and create a project. Alternatively, you can go use curl

```shell
curl --location --request GET 'https://launch.micronaut.io/create/grpc/micronaut-grpc-java?lang=JAVA&build=GRADLE' --output micronaut-grpc-java.zip
```

This will create a micronaut project with grpc and protobuf dependencies that are needed.

### proto file

The main proto file for the project is `niravStuff.proto`. It creates a service to receive a message, and defines a request and reply. 
The service will respond with "Hello World".

## Endpoint

The `NiravEndPoint.java` file is the interface where gPRC receives the protobuf message. It must extend a generated class `NiravServiceGrpc.NiravServiceImplBase` that is 
derived from the proto file. The classes are generated from the build process with gradle. 

Subsequently, you can inject your local micronaut service class and process the request and respond with gRPC response observers.

## Integration Test

The `NiravEndPointTest.java` creates a request and sends the message to the running microservice. In order to connect to the server and grpc endpoint 
in a test, you need to create a stub with a `ManagingChannel`. We do this with `@Factory` in the test.

## Java Client

You can create a stub and client by using the host name and port. We did this with `HelloWorldClientPlainText` and `HelloWorldClient`.
First you create the channel, and then using the channel, you must create the stub to the endpoint. You then use the stub to access the Grpc service.

## Default Hello World Included

The grpc dependencies come with a default `helloworld.proto` as well, and I was able to excerise that endpoint, which is inherently built in with 
the build process. See `micronaut-grpc-opentracing-3.5.0.jar!\helloworld.proto`.

The classes `GreetingEndpointTest` and `GreetingEndpoint` demonstrate the communication. 

## References

These references helped in developing the same application:

- https://micronaut-projects.github.io/micronaut-grpc/snapshot/guide/index.html
- https://github.com/micronaut-projects/micronaut-grpc
- https://github.com/grpc/grpc-java