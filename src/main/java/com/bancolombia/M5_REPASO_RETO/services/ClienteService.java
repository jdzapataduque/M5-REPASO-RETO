package com.bancolombia.M5_REPASO_RETO.services;

import com.bancolombia.M5_REPASO_RETO.entities.Cliente;
import com.bancolombia.M5_REPASO_RETO.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente guardarCliente(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }
}
