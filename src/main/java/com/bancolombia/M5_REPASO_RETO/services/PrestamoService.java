package com.bancolombia.M5_REPASO_RETO.services;

import com.bancolombia.M5_REPASO_RETO.entities.Cliente;
import com.bancolombia.M5_REPASO_RETO.entities.Historial;
import com.bancolombia.M5_REPASO_RETO.entities.Prestamo;
import com.bancolombia.M5_REPASO_RETO.repository.ClienteRepository;
import com.bancolombia.M5_REPASO_RETO.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

@Service
public class PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public Prestamo SolicitarPrestamo (Long cliente_id, float monto, float duracionMeses){

        Prestamo prestamo = new Prestamo();
        prestamo.setMonto(monto);
        prestamo.setInteres(0.2F);
        prestamo.setDuracionMeses(duracionMeses);
        prestamo.setEstado("Pendiente");
        prestamo.setCliente(this.clienteRepository.getReferenceById(cliente_id));

        Historial historial = new Historial();
        historial.setMontoSolicitado(monto);
        historial.setEstado(prestamo.getEstado());
        historial.setPrestamo(prestamo);

        prestamo.setHistorial(List.of(historial));

        Prestamo prestamoSaved = this.prestamoRepository.save(prestamo);
        Prestamo prestamoReturn = new Prestamo();
        prestamoReturn.setPrestamo_id(prestamoSaved.getPrestamo_id());
        prestamoReturn.setMonto(prestamoSaved.getMonto());
        prestamoReturn.setInteres(prestamoSaved.getInteres());
        prestamoReturn.setEstado(prestamoSaved.getEstado());

        Cliente cliente = new Cliente(prestamoSaved.getCliente().getCliente_id(), prestamoSaved.getCliente().getNombre());

        prestamoReturn.setCliente(cliente);

        return prestamoReturn;
    }

    public Prestamo AprobarPrestamo (Long prestamo_id){

        Prestamo prestamoAprovado = new Prestamo();
        prestamoAprovado.setMonto(this.prestamoRepository.getReferenceById(prestamo_id).getMonto());
        prestamoAprovado.setInteres(this.prestamoRepository.getReferenceById(prestamo_id).getInteres());
        prestamoAprovado.setDuracionMeses(this.prestamoRepository.getReferenceById(prestamo_id).getDuracionMeses());
        prestamoAprovado.setEstado("Aprobado");
        prestamoAprovado.setCliente(this.prestamoRepository.getReferenceById(prestamo_id).getCliente());
        prestamoAprovado.setPrestamo_id(prestamo_id);

        Historial historial = new Historial();
        historial.setMontoSolicitado(prestamoAprovado.getMonto());
        historial.setEstado(prestamoAprovado.getEstado());
        historial.setPrestamo(prestamoAprovado);

        prestamoAprovado.setHistorial(List.of(historial));

        return this.prestamoRepository.save(prestamoAprovado);
    }

    public Prestamo RechazarPrestamo (Long prestamo_id){

        Prestamo prestamoRechazado = new Prestamo();
        prestamoRechazado.setMonto(this.prestamoRepository.getReferenceById(prestamo_id).getMonto());
        prestamoRechazado.setInteres(this.prestamoRepository.getReferenceById(prestamo_id).getInteres());
        prestamoRechazado.setDuracionMeses(this.prestamoRepository.getReferenceById(prestamo_id).getDuracionMeses());
        prestamoRechazado.setEstado("Rechazado");
        prestamoRechazado.setCliente(this.prestamoRepository.getReferenceById(prestamo_id).getCliente());
        prestamoRechazado.setPrestamo_id(prestamo_id);

        Historial historial = new Historial();
        historial.setMontoSolicitado(prestamoRechazado.getMonto());
        historial.setEstado(prestamoRechazado.getEstado());
        historial.setPrestamo(prestamoRechazado);

        prestamoRechazado.setHistorial(List.of(historial));

        return this.prestamoRepository.save(prestamoRechazado);
    }

    public String ConsultarPrestamo (Long prestamo_id){
        Prestamo prestamoConsultado = this.prestamoRepository.getReferenceById(prestamo_id);
        if (prestamoConsultado == null)
            return null;
        String estado = prestamoConsultado.getEstado();
        return estado;
    }

    public List<Prestamo> ListarPrestamos (Long cliente_id){
        Cliente cliente1 = this.clienteRepository.getReferenceById(cliente_id);


        List<Prestamo> prestamos = cliente1.getPrestamoList()
                .stream().map(x -> {
                    Long prestamo_id = x.getPrestamo_id();
                    float monto = x.getMonto();
                    float interes = x.getInteres();;
                    float duracionMeses = x.getDuracionMeses();
                    String estado = x.getEstado();
                    String nombre = x.getCliente().getNombre();
                    return new Prestamo( prestamo_id, monto, interes, duracionMeses, estado);
                }).collect(Collectors.toList());
        return prestamos;
    }

    public double simularCuotas (float monto, float interes, float duracion){
        double denominador =  pow(1+interes, -1*duracion);
        double cuota = (monto * interes) / (1 - denominador);
        return cuota;
    }

}
