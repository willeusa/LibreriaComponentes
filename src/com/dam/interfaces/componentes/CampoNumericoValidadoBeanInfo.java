package com.dam.interfaces.componentes;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * Descriptor explicito para CampoNumericoValidado.
 * Controla la exposicion de propiedades e iconos en el disenador visual Matisse.
 */
public class CampoNumericoValidadoBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        // Asocia el descriptor directamente con la clase del componente visual
        BeanDescriptor descriptor = new BeanDescriptor(CampoNumericoValidado.class);
        descriptor.setDisplayName("Campo Numerico Validado");
        descriptor.setShortDescription("Campo de texto con validacion numerica y cambio de estado visual.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            // Descriptor para la propiedad 'valor'
            PropertyDescriptor propValor = new PropertyDescriptor(
                "valor", 
                CampoNumericoValidado.class, 
                "getValor", 
                "setValor"
            );
            propValor.setDisplayName("Valor Numerico");
            propValor.setShortDescription("Valor entero validado contenido en el campo.");
            propValor.setPreferred(true); // Prioridad alta: se muestra en la seccion principal de propiedades

            // Descriptor para la propiedad 'colorError'
            PropertyDescriptor propColorError = new PropertyDescriptor(
                "colorError", 
                CampoNumericoValidado.class, 
                "getColorError", 
                "setColorError"
            );
            propColorError.setDisplayName("Color de Error");
            propColorError.setShortDescription("Color de fondo aplicado cuando la entrada no es un entero valido.");
            propColorError.setPreferred(true);

            // Al retornar unicamente este array, se ocultan las decenas de propiedades heredadas de JPanel
            return new PropertyDescriptor[] { propValor, propColorError };

        } catch (IntrospectionException e) {
            System.err.println("Error al introspeccionar propiedades: " + e.getMessage());
            return super.getPropertyDescriptors();
        }
    }

    @Override
    public Image getIcon(int iconKind) {
        // Carga el icono segun la resolucion solicitada por la paleta del IDE
        if (iconKind == ICON_COLOR_16x16 || iconKind == ICON_MONO_16x16) {
            return loadImage("/com/dam/interfaces/componentes/imagenes/icono_componente16.png");
        }
        return null;
    }
}