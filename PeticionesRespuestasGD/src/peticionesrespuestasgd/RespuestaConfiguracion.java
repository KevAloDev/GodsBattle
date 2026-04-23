package com.marina.godsbattle;

import java.io.Serializable;
import java.util.List;
import pojosgd.Configuracion;

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
 */
public class RespuestaConfiguracion implements Serializable {

    // 1. Identificación: creamos un identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;

    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Configuración no encontrada", "Guardado", "Actualizado")
    private String mensaje;

    // 4. ¿QUÉ INFORMACIÓN DE CONFIGURACION (OBJETO) TIENES? (Create/Read/Update)
    private Configuracion configuracion;

    // 5. ¿Y SI HEMOS PEDIDO MUCHAS CONFIGURACIONES? (Read_all)
    private List<Configuracion> configuraciones;

    // 6. CONSTRUCTORES

    // 6.1 Constructor vacío
    public RespuestaConfiguracion() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaConfiguracion(boolean exito, String mensaje, Configuracion configuracion, List<Configuracion> configuraciones) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.configuracion = configuracion;
        this.configuraciones = configuraciones;
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

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public List<Configuracion> getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(List<Configuracion> configuraciones) {
        this.configuraciones = configuraciones;
    }
}