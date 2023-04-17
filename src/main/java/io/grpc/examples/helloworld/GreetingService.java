package io.grpc.examples.helloworld;

import jakarta.inject.Singleton;

@Singleton
public class GreetingService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
