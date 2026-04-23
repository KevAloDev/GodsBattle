package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.Armadura;

/**
 * PETICIÓN ARMADURA: 
 * Es el sobre que envía el cliente. Representa la información 
 * que viaja desde la app hacia el servidor.
 * IMPORTANTE: es serializable para poder viajar por la red convertida en bytes.
 */
public class PeticionArmadura implements Serializable {
    // 1. Identificación: creamos un identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. CREAMOS EL ENUM DE TIPO OPERACION.
    public enum TipoOperacion {
        CREATE, // Crea
        READ, // Lee
        READ_ALL, // Lee todos
        UPDATE, // Actualiza
        DELETE, // Borra
        PING // Comprueba la conexión
    }

    // 3. ¿QUÉ QUIERES HACER?
    private TipoOperacion tipoOperacion;

    // 4. ¿CON QUÉ DATOS? (Create/Update)
    private Armadura armadura;

    // 5. ¿CON QUÉ ID? (Read/Delete)
    private int idArmadura;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public PeticionArmadura() {}

    // 6.2 Constructor para Leer_Todo
    public PeticionArmadura(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // 6.3 Constructor para Read/Delete
    public PeticionArmadura(TipoOperacion tipoOperacion, int idArmadura) {
        this.tipoOperacion = tipoOperacion;
        this.idArmadura = idArmadura;
    }

    // 6.4 Constructor para Create/Update
    public PeticionArmadura(TipoOperacion tipoOperacion, Armadura armadura, int idArmadura) {
        this.tipoOperacion = tipoOperacion;
        this.armadura = armadura;
        this.idArmadura = idArmadura;
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    public int getIdArmadura() {
        return idArmadura;
    }

    public void setIdArmadura(int idArmadura) {
        this.idArmadura = idArmadura;
    }
}