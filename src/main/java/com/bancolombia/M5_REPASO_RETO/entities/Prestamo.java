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
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prestamo_id;
    private float monto;
    private float interes;
    private float duracionMeses;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<Historial> historial;

    public Prestamo(Long prestamo_id, float monto, float interes, float duracionMeses, String estado) {
        this.prestamo_id = prestamo_id;
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
    }
}
