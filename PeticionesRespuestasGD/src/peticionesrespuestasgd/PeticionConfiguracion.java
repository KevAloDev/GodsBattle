package com.marina.godsbattle;

import java.io.Serializable;
import pojosgd.Configuracion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 */

/**
 *
 *
 * PETICIÓN
 * ==========
 * Es el sobre que envía el cliente. Representa la información que viaja 
 * desde la app de android hacia el servidor.
 *
 * IMPORTANTE: es serializable para poder viajar por la red convertido en bytes.
 */
public class PeticionConfiguracion implements Serializable {

    // 1. Identificación: creamos un identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. CREAMOS EL ENUM DE TIPOOPERACION
    public enum TipoOperacion {
        CREATE,    // Crea
        READ,      // Lee
        READ_ALL,  // Lee todos
        UPDATE,    // Actualiza
        DELETE,    // Borra
        PING       // Comprueba la conexión
    }

    // 3. ¿QUÉ QUIERES HACER?
    private TipoOperacion tipoOperacion;

    // 4. ¿CON QUÉ DATOS? (Create/Update)
    private Configuracion configuracion;

    // 5. ¿CON QUÉ ID? (Read/Delete)
    // NO HAY
    
    // 6. CONSTRUCTORES

    // 6.1 Constructor vacío
    public PeticionConfiguracion() {
        // Nada
    }

    // 6.2 Constructor para Read_All o PING
    public PeticionConfiguracion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // 6.3 Constructor para Read o Delete
    // NO HAY

    // 6.4 Constructor para Create/Update
    public PeticionConfiguracion(TipoOperacion tipoOperacion, Configuracion configuracion) {
        this.tipoOperacion = tipoOperacion;
        this.configuracion = configuracion; // Este puede sobrar según uso
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
}