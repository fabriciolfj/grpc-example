package com.github.fabriciolfj.server.service;

import com.github.fabriciolfj.api.grpc.v1.PersonRequest;
import com.github.fabriciolfj.api.grpc.v1.PersonRequestCode;
import com.github.fabriciolfj.api.grpc.v1.PersonResponse;
import com.github.fabriciolfj.api.grpc.v1.PersonServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService extends PersonServiceGrpc.PersonServiceImplBase {

    /*
    * Poderiamos utilizar um repositorio por exemplo
    * */
    @Override
    public void create(PersonRequest request, StreamObserver<PersonResponse> responseObserver) {
        var resp = PersonResponse.newBuilder()
                .setCode(UUID.randomUUID().toString())
                .build();

        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void find(PersonRequestCode request, StreamObserver<PersonResponse> responseObserver) {
        var resp = PersonResponse.newBuilder()
                .setCode(UUID.randomUUID().toString())
                .build();

        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
