package com.marina.godsbattle;

import java.io.Serializable;
import java.util.List;
import pojosgd.Habilidad;

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
public class RespuestaHabilidad implements Serializable {

    // 1. Identificación: creamos un identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;

    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Habilidad no encontrada", "Guardado", "Actualizado")
    private String mensaje;

    // 4. ¿QUÉ INFORMACIÓN DE HABILIDAD (OBJETO) TIENES? (Create/Read/Update)
    private Habilidad habilidad;

    // 5. ¿Y SI HEMOS PEDIDO MUCHAS HABILIDADES? (Read_all)
    private List<Habilidad> habilidades;

    // 6. CONSTRUCTORES

    // 6.1 Constructor vacío
    public RespuestaHabilidad() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaHabilidad(boolean exito, String mensaje, Habilidad habilidad, List<Habilidad> habilidades) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.habilidad = habilidad;
        this.habilidades = habilidades;
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

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }
}