package com.cine.ms_confiteria.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cine.ms_confiteria.Client.PagoClient;
import com.cine.ms_confiteria.Model.Combo;
import com.cine.ms_confiteria.Model.DTO.ComboPagoDTO;
import com.cine.ms_confiteria.Model.DTO.PagoDTO;
import com.cine.ms_confiteria.Repository.ComboRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ComboServiceTest {

    @Autowired
    private ComboService comboService;

    @MockitoBean
    private ComboRepository repository;

    @MockitoBean
    private PagoClient client;

    @Test
    public void testComprarComboExitoso() {
        Integer comboId = 1;
        Combo combo = new Combo();
        combo.setId(comboId);
        combo.setStock(5);
        combo.setValor(1000.0);

        PagoDTO respuestaPago = new PagoDTO();
        respuestaPago.setEstado("APROBADO");

        reset(repository, client);
        when(repository.findById(comboId)).thenReturn(Optional.of(combo));
        when(client.pagarCombo(any(ComboPagoDTO.class))).thenReturn(respuestaPago);

        PagoDTO resultado = comboService.comprarCombo(comboId, "DEBITO");

        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        verify(repository, times(1)).save(any(Combo.class));
        verify(client, times(1)).pagarCombo(any(ComboPagoDTO.class));
    }

    @Test
    public void testComprarComboSinStock() {
        Integer comboId = 2;
        Combo comboSinStock = new Combo();
        comboSinStock.setId(comboId);
        comboSinStock.setStock(0);

        reset(repository, client);
        when(repository.findById(comboId)).thenReturn(Optional.of(comboSinStock));

        assertThrows(RuntimeException.class, () -> {
            comboService.comprarCombo(comboId, "CREDITO");
        });
    }

    @Test
    public void testComprarComboInexistente() {
        Integer comboId = 999;
        reset(repository, client);
        when(repository.findById(comboId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            comboService.comprarCombo(comboId, "CREDITO");
        });
    }

    @Test
    public void testObtenerConfiteria() {
        reset(repository, client);
        when(repository.findAll()).thenReturn(Arrays.asList(new Combo(), new Combo()));

        List<Combo> resultado = comboService.ObtenerConfiteria();

        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }
}