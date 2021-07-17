package com.github.fabriciolfj.server.config;

import com.github.fabriciolfj.server.config.GrpcServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcServerRunner implements CommandLineRunner {

    private final GrpcServer grpcServer;

    @Override
    public void run(String... args) throws Exception {
        grpcServer.start();
        grpcServer.block();
    }
}
