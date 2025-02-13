package com.bancolombia.M5_REPASO_RETO.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cliente_id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamoList;

    public Cliente(Long cliente_id, String nombre) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
    }
}
