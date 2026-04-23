package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.Usuario;

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
public class PeticionUsuario implements Serializable {

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
        LOGIN,
        PING //Comprueba la conexion
    }

    // 3. ¿QUÉ QUIERES HACER?
    private TipoOperacion tipoOperacion;

    //4.¿CON QUÉ DATOS? (Create/Update)
    private Usuario usuario;

    //5.¿CON QUÉ ID? (Read/Delete)
    private int idUsuario;
    

    // 6. CONSTRUCTORES
    //6.1 VACIO
    public PeticionUsuario() {
        //Nada
    }
    
    //6.2 Constructor para Leer_Todo
    public PeticionUsuario(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    //6.3 Constructor para Read/Delete
    public PeticionUsuario(TipoOperacion tipoOperacion, int idUsuario) {
        this.tipoOperacion = tipoOperacion;
        this.idUsuario = idUsuario;
    }
    
    
    //6.4 Constructor para Create/Update
    public PeticionUsuario(TipoOperacion tipoOperacion, Usuario usuario, int idUsuario) {
        this.tipoOperacion = tipoOperacion;
        this.usuario = usuario; //ESTE IGUAL SOBRA
        this.idUsuario = idUsuario;
    }
    
    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
