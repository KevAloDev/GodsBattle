package com.marina.godsbattle;

import java.io.Serializable;
import java.util.List;
import pojosgd.EnemigoHabilidad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 */

/**
 *
 * 
 * RESPUESTA:
 * ==============
 * Es el paquete que nos devuelve el servidor. Contiene el resultado de la 
 * operación (éxito/fracaso) y los datos solicitados.
 * 
 * 
 */
public class RespuestaEnemigoHabilidad implements Serializable {

    // 1. Identificación: creamos un identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;

    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("No encontrado", "Guardado", "Actualizado")
    private String mensaje;

    // 4. ¿QUÉ INFORMACIÓN DE ENEMIGOHABILIDAD (OBJETO) TIENES? (Create/Read/Update)
    private EnemigoHabilidad enemigoHabilidad;

    // 5. ¿Y SI HEMOS PEDIDO MUCHOS? (Read_all)
    private List<EnemigoHabilidad> enemigoHabilidades;

    // 6. CONSTRUCTORES

    // 6.1 Constructor vacío
    public RespuestaEnemigoHabilidad() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaEnemigoHabilidad(boolean exito, String mensaje, EnemigoHabilidad enemigoHabilidad, List<EnemigoHabilidad> enemigoHabilidades) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.enemigoHabilidad = enemigoHabilidad;
        this.enemigoHabilidades = enemigoHabilidades;
    }

    // 7. GETTERS AND SETTERS
    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public EnemigoHabilidad getEnemigoHabilidad() {
        return enemigoHabilidad;
    }

    public void setEnemigoHabilidad(EnemigoHabilidad enemigoHabilidad) {
        this.enemigoHabilidad = enemigoHabilidad;
    }

    public List<EnemigoHabilidad> getEnemigoHabilidades() {
        return enemigoHabilidades;
    }

    public void setEnemigoHabilidades(List<EnemigoHabilidad> enemigoHabilidades) {
        this.enemigoHabilidades = enemigoHabilidades;
    }
}