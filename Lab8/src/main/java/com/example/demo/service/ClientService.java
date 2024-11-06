package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.ClientDTO;
import com.example.demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Transactional
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return clientMapper.toDto(client);
    }

    @Transactional
    public ClientDTO update(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client updatedClient = clientRepository.findById(clientDTO.getId()).orElse(null);

        if(client.getName()!=null) {
            assert updatedClient != null;
            updatedClient.setName(clientDTO.getName());
        }

        if(clientDTO.getSurname()!=null) {
            assert updatedClient != null;
            updatedClient.setSurname(clientDTO.getSurname());
        }

        if(clientDTO.getMiddleName()!=null) {
            assert updatedClient != null;
            updatedClient.setMiddleName(clientDTO.getMiddleName());
        }

        if(clientDTO.getPhoneNumber()!=null) {
            assert updatedClient != null;
            updatedClient.setPhoneNumber(clientDTO.getPhoneNumber());
        }

        if(clientDTO.getDateOfBirth()!=null) {
            assert updatedClient != null;
            updatedClient.setDateOfBirth(clientDTO.getDateOfBirth());
        }


        return clientMapper.toDto(updatedClient);
    }

    @Transactional
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }


    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
