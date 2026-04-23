package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.Personaje;

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
public class PeticionPersonaje implements Serializable {

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
    private Personaje personaje;

    //5.¿CON QUÉ ID? (Read/Delete)
    private int idPersonaje;

    // 6. CONSTRUCTORES
    //6.1 VACIO
    public PeticionPersonaje() {
        //Nada
    }
    
    //6.2 Constructor para Leer_Todo
    public PeticionPersonaje(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    //6.3 Constructor para Read/Delete
    public PeticionPersonaje(TipoOperacion tipoOperacion, int idPersonaje) {
        this.tipoOperacion = tipoOperacion;
        this.idPersonaje = idPersonaje;
    }

    //6.4 Constructor para Create/Update
    public PeticionPersonaje(TipoOperacion tipoOperacion, Personaje personaje, int idPersonaje) {
        this.tipoOperacion = tipoOperacion;
        this.personaje = personaje; //ESTE IGUAL SOBRA
        this.idPersonaje = idPersonaje;
    }
    
    // 7. GETTERS AND SETTERS

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
}
