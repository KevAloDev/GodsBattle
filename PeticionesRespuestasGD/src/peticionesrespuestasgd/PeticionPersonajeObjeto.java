package peticionesrespuestasgd;

import java.io.Serializable;
import pojosgd.PersonajeObjeto;

/**
 * PETICIÓN PERSONAJE OBJETO: Es el sobre que envía el cliente. Representa la
 * información que viaja desde la app hacia el servidor. IMPORTANTE: es
 * serializable para poder viajar por la red convertida en bytes.
 */
public class PeticionPersonajeObjeto implements Serializable {

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
    private PersonajeObjeto personajeObjeto;

    // 5. ¿CON QUÉ ID? (Read/Delete)
    private int idUsuario;
    private int idObjeto;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public PeticionPersonajeObjeto() {
    }

    // 6.2 Constructor para Leer_Todo
    public PeticionPersonajeObjeto(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // 6.3 Constructor para Read/Delete
    public PeticionPersonajeObjeto(TipoOperacion tipoOperacion, int idUsuario, int idObjeto) {
        this.tipoOperacion = tipoOperacion;
        this.idUsuario = idUsuario;
        this.idObjeto = idObjeto;
    }

    // 6.4 Constructor para Create/Update
    public PeticionPersonajeObjeto(TipoOperacion tipoOperacion, PersonajeObjeto personajeObjeto) {
        this.tipoOperacion = tipoOperacion;
        this.personajeObjeto = personajeObjeto;
        this.idUsuario = personajeObjeto.getPersonajeObjetoIdUsuario().getPersonajeIdUsuario().getUsuarioIdUsuario();
        this.idObjeto = personajeObjeto.getPersonajeObjetoIdObjeto().getObjetoIdObjeto();
    }

    // 7. GETTERS AND SETTERS
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public PersonajeObjeto getPersonajeObjeto() {
        return personajeObjeto;
    }

    public void setPersonajeObjeto(PersonajeObjeto personajeObjeto) {
        this.personajeObjeto = personajeObjeto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }
    
}
