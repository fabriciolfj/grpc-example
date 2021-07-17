package com.github.fabriciolfj.client.component;

import com.github.fabriciolfj.api.grpc.v1.PersonServiceGrpc;
import com.github.fabriciolfj.api.grpc.v1.PersonServiceGrpc.PersonServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class GrpcClient {

    @Value("${grpc.server.host:localhost}")
    private String host;

    @Value("${grpc.server.port:9090}")
    private int port;

    private ManagedChannel channel;
    private PersonServiceBlockingStub personServiceBlockingStub;

    public void start() {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        personServiceBlockingStub = PersonServiceGrpc.newBlockingStub(channel);
        log.info("gRPC client connected to {}:{}", host, port);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
        log.info("gRPC client disconnected successfully.");
    }

    public PersonServiceBlockingStub getSourceServiceStub() {
        return this.personServiceBlockingStub;
    }

}
