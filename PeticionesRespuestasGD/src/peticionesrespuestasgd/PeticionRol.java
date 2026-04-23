package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.Rol;

/**
 * PETICIÓN ROL =================
 * Es el sobre que envía el cliente.
 * Representa la información que viaja desde la app de android hacia el servidor.
 *
 * IMPORTANTE: es serializable para poder viajar por la red convertida en bytes.
 */
public class PeticionRol implements Serializable {

    // 1. Identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ENUM de tipo de operación
    public enum TipoOperacion {
        CREATE,
        READ,
        READ_ALL,
        UPDATE,
        DELETE,
        PING
    }

    // 3. ¿Qué quieres hacer?
    private TipoOperacion tipoOperacion;

    // 4. ¿Con qué datos? (Create/Update)
    private Rol rol;

    // 5. ¿Con qué ID? (Read/Delete)
    private int idRol;

    // 6. CONSTRUCTORES

    // 6.1 Vacío
    public PeticionRol() {
    }

    // 6.2 Constructor para READ_ALL o PING
    public PeticionRol(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // 6.3 Constructor para READ / DELETE
    public PeticionRol(TipoOperacion tipoOperacion, int idRol) {
        this.tipoOperacion = tipoOperacion;
        this.idRol = idRol;
    }

    // 6.4 Constructor para CREATE / UPDATE
    public PeticionRol(TipoOperacion tipoOperacion, Rol rol, int idRol) {
        this.tipoOperacion = tipoOperacion;
        this.rol = rol;
        this.idRol = idRol;
    }

    // 7. GETTERS Y SETTERS

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}