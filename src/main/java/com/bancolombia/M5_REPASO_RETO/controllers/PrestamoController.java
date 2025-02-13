package com.bancolombia.M5_REPASO_RETO.controllers;

import com.bancolombia.M5_REPASO_RETO.entities.Prestamo;
import com.bancolombia.M5_REPASO_RETO.repository.PrestamoRepository;
import com.bancolombia.M5_REPASO_RETO.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    private PrestamoService prestamoService;

    @PutMapping("/solicitar")
    public ResponseEntity<Prestamo> solicitarPrestamo (@RequestParam("cliente_id") Long cliente_id, @RequestParam("monto") float monto,
                                                      @RequestParam("duracionMeses") float duracionMeses){
        Prestamo prestamo = this.prestamoService.SolicitarPrestamo(cliente_id, monto, duracionMeses);
        if (prestamo == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(prestamo);
    }

    @PutMapping("/aprobar")
    public ResponseEntity<Prestamo> aprobar (@RequestParam("pestamo_id") Long prestamo_id){
        return ResponseEntity.ok().body(this.prestamoService.AprobarPrestamo(prestamo_id));
    }

    @PutMapping("/rechazar")
    public ResponseEntity<Prestamo> rechazar (@RequestParam("pestamo_id") Long prestamo_id){
        return ResponseEntity.ok().body(this.prestamoService.RechazarPrestamo(prestamo_id));
    }

    @GetMapping("/listar")
    public List<Prestamo> listar (@RequestParam("cliente_id") Long cliente_id){
        return this.prestamoService.ListarPrestamos(cliente_id);
    }

    @GetMapping("/obtener")
    public ResponseEntity<String> obtener ( @RequestParam("prestamo_id" )Long prestamo_id){
        return ResponseEntity.ok().body(this.prestamoService.ConsultarPrestamo(prestamo_id));
    }

    @GetMapping("/simular")
    public ResponseEntity<Double> simular ( @RequestParam("monto") float monto, @RequestParam("interes") float interes, @RequestParam("plazo") float plazo){
        return ResponseEntity.ok().body(this.prestamoService.simularCuotas(monto,interes,plazo));
    }


}
