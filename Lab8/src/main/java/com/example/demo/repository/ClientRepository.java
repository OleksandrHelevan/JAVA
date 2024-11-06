package com.example.demo.repository;

import com.example.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    @NonNull
    Optional<Client> findById(@NonNull Long id);

    @Override
    @NonNull
    Client save(@NonNull Client client);

}
