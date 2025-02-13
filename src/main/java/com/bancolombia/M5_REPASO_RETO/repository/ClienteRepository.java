package com.bancolombia.M5_REPASO_RETO.repository;

import com.bancolombia.M5_REPASO_RETO.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
