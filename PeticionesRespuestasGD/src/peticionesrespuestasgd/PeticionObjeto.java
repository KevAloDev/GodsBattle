package com.marina.godsbattle;

import java.io.Serializable;
import pojosgd.Objeto;

public class PeticionObjeto implements Serializable {
    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;

    // 2. CREAMOS EL ENUM DE TIPOOPERACION.
    public enum TipoOperacion {
        CREATE, //Crea
        READ, //Lee
        READ_ALL, //Lee todos
        UPDATE, //Actualiza
        DELETE, //Borra
        PING //Comprueba la conexion
    }

    // 3. ¿QUÉ QUIERES HACER?
    private TipoOperacion tipoOperacion;

    //4.¿CON QUÉ DATOS? (Create/Update)
    private Objeto objeto;

    //5.¿CON QUÉ ID? (Read/Delete)
    private int idObjeto;

    // 6. CONSTRUCTORES
    //6.1 VACIO
    public PeticionObjeto() {
        //Nada
    }
    
    //6.2 Constructor para Leer_Todo
    public PeticionObjeto(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    //6.3 Constructor para Read/Delete
    public PeticionObjeto(TipoOperacion tipoOperacion, int idObjeto) {
        this.tipoOperacion = tipoOperacion;
        this.idObjeto = idObjeto;
    }

    //6.4 Constructor para Create/Update
    public PeticionObjeto(TipoOperacion tipoOperacion, Objeto objeto, int idObjeto) {
        this.tipoOperacion = tipoOperacion;
        this.objeto = objeto;
        this.idObjeto = idObjeto;
    }
    
    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }
    
}
