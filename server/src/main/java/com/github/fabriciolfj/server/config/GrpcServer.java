package com.github.fabriciolfj.server.config;

import com.github.fabriciolfj.server.interceptor.ExceptionInterceptor;
import com.github.fabriciolfj.server.service.PersonService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class GrpcServer {

    @Value("${grpc.server.port:9090}")
    private int port;
    private Server server;
    private PersonService personService;
    private ExceptionInterceptor exceptionInterceptor;

    public GrpcServer(PersonService personService, ExceptionInterceptor exceptionInterceptor) {
        this.personService = personService;
        this.exceptionInterceptor = exceptionInterceptor;
    }

    public void start() throws IOException, InterruptedException {
        log.info("gRPC server is starting on port: {}.", port);
        server = ServerBuilder.forPort(port)
                .addService(personService)
                .intercept(exceptionInterceptor)
                .build().start();
        log.info("gRPC server started and listening on port: {}.", port);
        log.info("Following service are available: ");
        server.getServices().stream()
                .forEach(s -> log.info("Service Name: {}", s.getServiceDescriptor().getName()));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down gRPC server.");
            GrpcServer.this.stop();
            log.info("gRPC server shut down successfully.");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void block() throws InterruptedException {
        if (server != null) {
            // received the request until application is terminated
            server.awaitTermination();
        }
    }
}
