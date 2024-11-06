package com.example.demo.mapper;

import com.example.demo.model.ClientDTO;
import com.example.demo.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDto(Client client);

    Client toEntity(ClientDTO clientDTO);
}
