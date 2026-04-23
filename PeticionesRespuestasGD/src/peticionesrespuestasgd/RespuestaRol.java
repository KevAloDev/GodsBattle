package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.Rol;

/**
 *
 * RESPUESTA ROL:
 * ==============
 * Es el paquete que nos devuelve el servidor. Contiene el resultado de la 
 * operación (éxito/fracaso) y los datos solicitados.
 *
 */
public class RespuestaRol implements Serializable {

    // 1. Identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIO BIEN LA OPERACION?
    private boolean exito;

    // 3. ¿HAY INFORMACION PARA EL CLIENTE?
    // (Delete/Update/Create)
    private String mensaje;

    // 4. ¿QUE INFORMACION DE ROL (OBJETO) TIENES?
    // (Create/Read/Update)
    private Rol rol;

    // 5. ¿Y SI HEMOS PEDIDO MUCHOS ROLES? (Read_all)
    private List<Rol> roles;

    // 6. CONSTRUCTORES

    // 6.1 Constructor vacío
    public RespuestaRol() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaRol(boolean exito, String mensaje, Rol rol, List<Rol> roles) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.rol = rol;
        this.roles = roles;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}