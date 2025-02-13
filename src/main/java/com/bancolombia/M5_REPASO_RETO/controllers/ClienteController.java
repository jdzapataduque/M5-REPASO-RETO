package com.bancolombia.M5_REPASO_RETO.controllers;

import com.bancolombia.M5_REPASO_RETO.entities.Cliente;
import com.bancolombia.M5_REPASO_RETO.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/guardar")
    public ResponseEntity<Cliente> guardar (@RequestBody Cliente cliente){
        Cliente clienteResponse = this.clienteService.guardarCliente(cliente);
        return ResponseEntity.ok().body(clienteResponse);
    }



}
