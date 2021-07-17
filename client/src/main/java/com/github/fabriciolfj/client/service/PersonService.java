package com.github.fabriciolfj.client.service;

import com.github.fabriciolfj.client.component.GrpcClient;
import com.github.fabriciolfj.client.dto.PersonRequestDTO;
import com.github.fabriciolfj.client.dto.PersonResponseDTO;
import com.github.fabriciolfj.client.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonService {

    private final GrpcClient grpcClient;

    public PersonResponseDTO create(final PersonRequestDTO requestDTO) {
        var request = PersonMapper.toRequest(requestDTO);
        log.info("Request create: {}", request.toString());
        return PersonMapper.toResponse(grpcClient.getSourceServiceStub().create(request));
    }

    public PersonResponseDTO find(final String code) {
        var result = grpcClient.getSourceServiceStub().find(PersonMapper.toRequest(code));
        log.info("Result: {}", result.toString());
        return PersonMapper.toResponse(result);
    }
}
