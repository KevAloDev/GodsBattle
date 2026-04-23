package com.marina.godsbattle;

import java.io.Serializable;
import pojosgd.Arma;

public class PeticionArma implements Serializable {
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
    private Arma arma;

    //5.¿CON QUÉ ID? (Read/Delete)
    private int idArma;

    // 6. CONSTRUCTORES
    //6.1 VACIO
    public PeticionArma() {
        //Nada
    }
    
    //6.2 Constructor para Leer_Todo
    public PeticionArma(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    //6.3 Constructor para Read/Delete
    public PeticionArma(TipoOperacion tipoOperacion, int idArma) {
        this.tipoOperacion = tipoOperacion;
        this.idArma = idArma;
    }

    //6.4 Constructor para Create/Update
    public PeticionArma(TipoOperacion tipoOperacion, Arma arma, int idArma) {
        this.tipoOperacion = tipoOperacion;
        this.arma = arma;
        this.idArma = idArma;
    }
    
    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }
    
}
