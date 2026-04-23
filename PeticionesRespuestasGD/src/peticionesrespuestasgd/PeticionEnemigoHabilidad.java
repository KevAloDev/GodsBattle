package com.marina.godsbattle;

import java.io.Serializable;
import pojosgd.EnemigoHabilidad;

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
 *
 * IMPORTANTE: es serializable para poder viajar por la red convertido en bytes.
 */
public class PeticionEnemigoHabilidad implements Serializable {

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
    private EnemigoHabilidad enemigoHabilidad;

    // 5. ¿CON QUÉ ID? (Read/Delete)
    private int idEnemigo;
    private int idHabilidad;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public PeticionEnemigoHabilidad() {
        // Nada
    }

    // 6.2 Constructor para Read_All o PING
    public PeticionEnemigoHabilidad(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // 6.3 Constructor para Read o Delete
    public PeticionEnemigoHabilidad(TipoOperacion tipoOperacion, int idEnemigo, int idHabilidad) {
        this.tipoOperacion = tipoOperacion;
        this.idEnemigo = idEnemigo;
        this.idHabilidad = idHabilidad;
    }

    // 6.4 Constructor para Create/Update
    public PeticionEnemigoHabilidad(TipoOperacion tipoOperacion, EnemigoHabilidad enemigoHabilidad) {
        this.tipoOperacion = tipoOperacion;
        this.enemigoHabilidad = enemigoHabilidad;
        this.idEnemigo = enemigoHabilidad.getEnemigoHabilidadIdEnemigo().getEnemigoIdEnemigo();
        this.idHabilidad = enemigoHabilidad.getEnemigoHabilidadIdHabilidad().getHabilidadId();
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public EnemigoHabilidad getEnemigoHabilidad() {
        return enemigoHabilidad;
    }

    public void setEnemigoHabilidad(EnemigoHabilidad enemigoHabilidad) {
        this.enemigoHabilidad = enemigoHabilidad;
    }

    public int getIdEnemigo() {
        return idEnemigo;
    }

    public void setIdEnemigo(int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }
}