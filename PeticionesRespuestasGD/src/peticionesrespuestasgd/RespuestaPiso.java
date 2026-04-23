package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.Piso;

/**
 *
 * RESPUESTA ROL: ============== Es el paquete que nos devuelve el servidor.
 * Contiene el resultado de la operación (éxito/fracaso) y los datos
 * solicitados.
 *
 */
public class RespuestaPiso implements Serializable {

    // 1. Identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIO BIEN LA OPERACION?
    private boolean exito;

    // 3. ¿HAY INFORMACION PARA EL CLIENTE?
    // (Delete/Update/Create)
    private String mensaje;

    // 4. ¿QUE INFORMACION DE ROL (OBJETO) TIENES?
    // (Create/Read/Update)
    private Piso piso;

    // 5. ¿Y SI HEMOS PEDIDO MUCHOS ROLES? (Read_all)
    private List<Piso> pisos;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public RespuestaPiso() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaPiso(boolean exito, String mensaje, Piso piso, List<Piso> pisos) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.piso = piso;
        this.pisos = pisos;
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

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public List<Piso> getPisos() {
        return pisos;
    }

    public void setPisos(List<Piso> pisos) {
        this.pisos = pisos;
    }

}
