package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.Personaje;
import pojosgd.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class RespuestaUsuario implements Serializable{
    
    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;
    
    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;
    
    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Usuario no encontrado", "Guardado", "Actualizado")
    //Delete/Update/Create
    private String mensaje;
    
    // 4. ¿QUE INFORMACIÓN DE EMPLEADO (OBJETO) TIENES? (Create/Read/Update)
    private Usuario usuario;
    
    // 5. ¿Y SI HEMOS PEDIDO MUCHOS EMPLEADOS? (Read_all)
    private List<Usuario> usuarios;
    
    //6. CONSTRUCTORES
    
    //6.1. Constructor Vacio
    public RespuestaUsuario() {
        //NADA
    }
    // 6.2 Constructor completo

    public RespuestaUsuario(boolean exito, String mensaje, Usuario usuario, List<Usuario> usuarios) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.usuarios = usuarios;
    }
    
    //7.GETTERS AND SETTERS
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
