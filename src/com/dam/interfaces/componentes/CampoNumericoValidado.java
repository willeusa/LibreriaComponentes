package com.dam.interfaces.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Componente visual compuesto segun la especificacion JavaBeans.
 * Encapsula un campo de texto con validacion numerica y notificacion de estado.
 */
public class CampoNumericoValidado extends JPanel implements Serializable {

    public static final String PROP_VALOR = "valor";
    public static final String PROP_COLOR_ERROR = "colorError";

    private final JTextField campoTexto;
    private final JLabel etiquetaEstado;
    private final PropertyChangeSupport propertySupport;

    private int valor;
    private Color colorError;
    private boolean estadoValido;

    public CampoNumericoValidado() {
        propertySupport = new PropertyChangeSupport(this);
        campoTexto = new JTextField(10);
        etiquetaEstado = new JLabel("✓");
        colorError = new Color(255, 200, 200);
        valor = 0;
        estadoValido = true;

        inicializarInterfaz();
        configurarEscuchadores();
    }

    private void inicializarInterfaz() {
        setLayout(new BorderLayout(5, 5));
        add(campoTexto, BorderLayout.CENTER);
        add(etiquetaEstado, BorderLayout.EAST);
        campoTexto.setText(String.valueOf(valor));
    }

    private void configurarEscuchadores() {
        campoTexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { validar(); }
            @Override
            public void removeUpdate(DocumentEvent e) { validar(); }
            @Override
            public void changedUpdate(DocumentEvent e) { validar(); }
        });
    }

    private void validar() {
        String texto = campoTexto.getText().trim();
        try {
            int nuevoValor = Integer.parseInt(texto);
            int valorAnterior = this.valor;
            this.valor = nuevoValor;
            this.estadoValido = true;
            campoTexto.setBackground(Color.WHITE);
            etiquetaEstado.setText("✓");
            etiquetaEstado.setForeground(new Color(0, 150, 0));
            propertySupport.firePropertyChange(PROP_VALOR, valorAnterior, nuevoValor);
        } catch (NumberFormatException ex) {
            this.estadoValido = false;
            campoTexto.setBackground(this.colorError);
            etiquetaEstado.setText("✕");
            etiquetaEstado.setForeground(Color.RED);
        }
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int nuevoValor) {
        int valorAnterior = this.valor;
        this.valor = nuevoValor;
        campoTexto.setText(String.valueOf(nuevoValor));
        propertySupport.firePropertyChange(PROP_VALOR, valorAnterior, nuevoValor);
    }

    public Color getColorError() {
        return colorError;
    }

    public void setColorError(Color colorError) {
        Color viejoColor = this.colorError;
        this.colorError = colorError;
        propertySupport.firePropertyChange(PROP_COLOR_ERROR, viejoColor, colorError);
    }

    public boolean isEstadoValido() {
        return estadoValido;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.addPropertyChangeListener(listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.removePropertyChangeListener(listener);
        }
    }
}