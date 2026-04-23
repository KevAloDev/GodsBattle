package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.EnemigoPiso;

/**
 * PETICIÓN ENEMIGOPISO: Es el sobre que envía el cliente. Representa la
 * información que viaja desde la app hacia el servidor. IMPORTANTE: es
 * serializable para poder viajar por la red convertida en bytes.
 */
public class PeticionEnemigoPiso implements Serializable {

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
    private EnemigoPiso enemigoPiso;

    // 5. ¿CON QUÉ ID? (Read/Delete)
    private int idEnemigo;
    private int idPiso;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public PeticionEnemigoPiso() {
    }

    // 6.2 Constructor para Leer_Todo
    public PeticionEnemigoPiso(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // 6.3 Constructor para Read/Delete
    public PeticionEnemigoPiso(TipoOperacion tipoOperacion, int idEnemigo, int idPiso) {
        this.tipoOperacion = tipoOperacion;
        this.idEnemigo = idEnemigo;
        this.idPiso = idPiso;
    }

    // 6.4 Constructor para Create/Update
    public PeticionEnemigoPiso(TipoOperacion tipoOperacion, EnemigoPiso enemigoPiso) {
        this.tipoOperacion = tipoOperacion;
        this.enemigoPiso = enemigoPiso;
        this.idEnemigo = enemigoPiso.getEnemigoPisoIdEnemigo().getEnemigoIdEnemigo();
        this.idPiso = enemigoPiso.getEnemigoPisoIdPiso().getPisoId();
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public EnemigoPiso getEnemigoPiso() {
        return enemigoPiso;
    }

    public void setEnemigoPiso(EnemigoPiso enemigoPiso) {
        this.enemigoPiso = enemigoPiso;
    }

    public int getIdEnemigo() {
        return idEnemigo;
    }

    public void setIdEnemigo(int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    public int getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(int idPiso) {
        this.idPiso = idPiso;
    }
    
}
