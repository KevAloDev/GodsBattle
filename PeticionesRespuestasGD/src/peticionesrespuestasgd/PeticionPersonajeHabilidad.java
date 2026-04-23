package com.marina.godsbattle;

import java.io.Serializable;
import pojosgd.PersonajeHabilidad;

public class PeticionPersonajeHabilidad implements Serializable {

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
    private PersonajeHabilidad personajeHabilidad;

    //5.¿CON QUÉ ID? (Read/Delete)
    private int idUsuario;
    private int idHabilidad;

    // 6. CONSTRUCTORES
    //6.1 VACIO
    public PeticionPersonajeHabilidad() {
        //Nada
    }

    //6.2 Constructor para Leer_Todo
    public PeticionPersonajeHabilidad(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    //6.3 Constructor para Read/Delete
    public PeticionPersonajeHabilidad(TipoOperacion tipoOperacion, int idUsuario, int idHabilidad) {
        this.tipoOperacion = tipoOperacion;
        this.idUsuario = idUsuario;
        this.idHabilidad = idHabilidad;
    }

    //6.4 Constructor para Create/Update
    public PeticionPersonajeHabilidad(TipoOperacion tipoOperacion, PersonajeHabilidad personajeHabilidad) {
        this.tipoOperacion = tipoOperacion;
        this.personajeHabilidad = personajeHabilidad;
        this.idUsuario = personajeHabilidad.getPersonajeHabilidadIdUsuario().getPersonajeIdUsuario().getUsuarioIdUsuario();
        this.idHabilidad = personajeHabilidad.getPersonajeHabilidadIdHabilidad().getHabilidadId();
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public PersonajeHabilidad getPersonajeHabilidad() {
        return personajeHabilidad;
    }

    public void setPersonajeHabilidad(PersonajeHabilidad personajeHabilidad) {
        this.personajeHabilidad = personajeHabilidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

}
