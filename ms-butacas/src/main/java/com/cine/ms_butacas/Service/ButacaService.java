package com.cine.ms_butacas.Service;

import com.cine.ms_butacas.Client.SucursalClient;
import com.cine.ms_butacas.Model.Butaca;
import com.cine.ms_butacas.Model.DTO.SalaDTO;
import com.cine.ms_butacas.Repository.ButacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ButacaService {

    @Autowired
    private ButacaRepository butacaRepository;

    @Autowired
    private SucursalClient sucursalClient;

    public List<Butaca> getButacasPorFuncion(Integer funcionId, Integer salaId) {

        if (butacaRepository.existsByFuncionId(funcionId)) {
            List<Butaca> butacas = butacaRepository.findByFuncionId(funcionId);
            
            liberarLocksVencidos(butacas);
            return butacaRepository.findByFuncionId(funcionId);
        }

        SalaDTO sala = sucursalClient.buscarPorIdC(salaId);
        if(sala != null) {
            int capacidad = sala.getCapacidad();
            List<Butaca> butacas = new ArrayList<>();

            for (int i = 1; i <= capacidad; i++) {
                Butaca b = new Butaca();
                b.setFuncionId(funcionId);
                b.setSalaId(salaId);
                b.setNumero(i);
                b.setStatus("FREE");
                b.setLockedUntil(null);
                butacas.add(b);
            }

            return butacaRepository.saveAll(butacas);

        } else {

            System.out.println("No se encontro la sala de la funcion.");
            return new ArrayList<>();
        }

    }

    public Butaca lockButaca(Integer seatId) {
        Butaca butaca = butacaRepository.findById(seatId).orElse(null);

        if(butaca == null){
            System.out.println("Butaca no encontrada: " + seatId);
            return null;
        }

        if (butaca.getStatus().equals("BOOKED")) {
            System.out.println("La butaca " + butaca.getNumero() + " ya está vendida.");
            return null;
        }
        
        if (butaca.getStatus().equals("LOCKED") && butaca.getLockedUntil().isAfter(LocalDateTime.now())) {
            System.out.println("La butaca " + butaca.getNumero() + " está reservada por otro usuario. Intenta en unos minutos.");
            return null;
        }

        butaca.setStatus("LOCKED");
        butaca.setLockedUntil(LocalDateTime.now().plusMinutes(15));
        return butacaRepository.save(butaca);
    }

    public Butaca bookButaca(Integer seatId) {
        Butaca butaca = butacaRepository.findById(seatId).orElse(null);

        if(butaca == null){

            System.out.println("Butaca no encontrada: " + seatId);
            return null;
        }

        if (butaca.getStatus().equals("BOOKED")) {
            System.out.println("La butaca " + butaca.getNumero() + " ya está vendida.");
            return null;
        }

        butaca.setStatus("BOOKED");
        butaca.setLockedUntil(null);
        return butacaRepository.save(butaca);
    }

    private void liberarLocksVencidos(List<Butaca> butacas) {
        LocalDateTime ahora = LocalDateTime.now();
        
        for(Butaca b: butacas){

            if(b.getStatus().equals("LOCKED") 
                && b.getLockedUntil() != null 
                && b.getLockedUntil().isBefore(ahora)
            ){
                b.setStatus("FREE");
                b.setLockedUntil(null);
                butacaRepository.save(b);
            }
        }

    }

}
