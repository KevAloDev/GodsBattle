package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.Enemigo;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *
 * PETICIÓN ================= Es el sobre que envia el cliente. Representa la
 * información que viaja desde la app de android hacia el servidor
 *
 *
 * IMPORTANTE : es serializabl para poder viajar por la red convetida en bytes.
 */
public class PeticionEnemigo implements Serializable {

    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;

    // 2. CREAMOS EL ENUM DE TIPOOPERACION.
    public enum TipoOperacion {
        READ, //Lee
        READ_ALL, //Lee todos       
        PING //Comprueba la conexion
    }

    // 3. ¿QUÉ QUIERES HACER?
    private TipoOperacion tipoOperacion;

    //4.¿CON QUÉ DATOS? (Create/Update)
    private Enemigo enemigo;

    //5.¿CON QUÉ ID? (Read/Delete)
    private int idEnemigo;

    // 6. CONSTRUCTORES
    //6.1 VACIO
    public PeticionEnemigo() {
    }

    //6.2 Constructor para Leer_Todo
    public PeticionEnemigo(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    //6.3 Constructor para Read
    public PeticionEnemigo(TipoOperacion tipoOperacion, int idEnemigo) {
        this.tipoOperacion = tipoOperacion;
        this.idEnemigo = idEnemigo;
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    public int getIdEnemigo() {
        return idEnemigo;
    }

    public void setIdEnemigo(int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

}
