package com.github.fabriciolfj.client.mapper;


import com.github.fabriciolfj.api.grpc.v1.PersonRequest;
import com.github.fabriciolfj.api.grpc.v1.PersonRequestCode;
import com.github.fabriciolfj.api.grpc.v1.PersonResponse;
import com.github.fabriciolfj.client.dto.PersonRequestDTO;
import com.github.fabriciolfj.client.dto.PersonResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonMapper {

    public static PersonRequest toRequest(final PersonRequestDTO dto) {
        return PersonRequest
                .newBuilder()
                .setDocument(dto.getDocument())
                .setName(dto.getName())
                .build();
    }

    public static PersonResponseDTO toResponse(final PersonResponse response) {
        return PersonResponseDTO.builder()
                .code(response.getCode())
                .build();
    }

    public static PersonRequestCode toRequest(final String code) {
        return PersonRequestCode
                .newBuilder()
                .setCode(code)
                .build();
    }
}
