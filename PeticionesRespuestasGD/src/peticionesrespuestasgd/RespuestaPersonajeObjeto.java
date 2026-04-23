package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.PersonajeObjeto;

/**
 * RESPUESTA PERSONAJE OBJETO: 
 * Es el paquete que nos devuelve el servidor.
 * Contiene el resultado de la operación (éxito/fracaso) y los datos solicitados.
 */
public class RespuestaPersonajeObjeto implements Serializable {
    // 1. Identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIO BIEN LA OPERACION?
    private boolean exito;

    // 3. ¿HAY INFORMACION PARA EL CLIENTE?
    private String mensaje;

    // 4. ¿QUE INFORMACION DE PERSONAJE OBJETO TIENES? (Create/Read/Update)
    private PersonajeObjeto personajeObjeto;

    // 5. ¿Y SI HEMOS PEDIDO MUCHOS PERSONAJES OBJETO? (Read_all)
    private List<PersonajeObjeto> personajeObjetos;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public RespuestaPersonajeObjeto() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaPersonajeObjeto(boolean exito, String mensaje, PersonajeObjeto personajeObjeto, List<PersonajeObjeto> personajeObjetos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.personajeObjeto = personajeObjeto;
        this.personajeObjetos = personajeObjetos;
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

    public PersonajeObjeto getPersonajeObjeto() {
        return personajeObjeto;
    }

    public void setPersonajeObjeto(PersonajeObjeto personajeObjeto) {
        this.personajeObjeto = personajeObjeto;
    }

    public List<PersonajeObjeto> getPersonajeObjetos() {
        return personajeObjetos;
    }

    public void setPersonajeObjetos(List<PersonajeObjeto> personajeObjetos) {
        this.personajeObjetos = personajeObjetos;
    }

}