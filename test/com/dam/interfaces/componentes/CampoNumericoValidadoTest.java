package com.dam.interfaces.componentes;


import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CampoNumericoValidadoTest {


    private CampoNumericoValidado componente;


    @BeforeEach
    void setUp() {
        componente = new CampoNumericoValidado();
    }


    @Test
    @DisplayName("El estado inicial debe ser 0 y valido")
    void testEstadoInicial() {
        assertEquals(0, componente.getValor());
        assertTrue(componente.isEstadoValido());
    }


    @Test
    @DisplayName("El metodo setValor debe notificar el cambio mediante PropertyChangeEvent")
    void testNotificacionEventoPropiedad() {
        AtomicBoolean notificado = new AtomicBoolean(false);


        componente.addPropertyChangeListener(CampoNumericoValidado.PROP_VALOR, evt -> {
            notificado.set(true);
            assertEquals(0, evt.getOldValue());
            assertEquals(50, evt.getNewValue());
        });


        componente.setValor(50);


        assertTrue(notificado.get(), "La propiedad enlazada debio emitir un evento.");
        assertEquals(50, componente.getValor());
    }


    @Test
    @DisplayName("La actualizacion de colorError debe ser persistente")
    void testModificacionColorError() {
        Color colorPrueba = Color.ORANGE;
        componente.setColorError(colorPrueba);
        assertEquals(colorPrueba, componente.getColorError());
    }
}
