package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.Enemigo;

/**
 *
 * RESPUESTA ROL: ============== Es el paquete que nos devuelve el servidor.
 * Contiene el resultado de la operación (éxito/fracaso) y los datos
 * solicitados.
 *
 */
public class RespuestaEnemigo implements Serializable {

    // 1. Identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIO BIEN LA OPERACION?
    private boolean exito;

    // 3. ¿HAY INFORMACION PARA EL CLIENTE?
    // (Delete/Update/Create)
    private String mensaje;

    // 4. ¿QUE INFORMACION DE ROL (OBJETO) TIENES?
    // (Create/Read/Update)
    private Enemigo enemigo;

    // 5. ¿Y SI HEMOS PEDIDO MUCHOS ROLES? (Read_all)
    private List<Enemigo> enemigos;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public RespuestaEnemigo() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaEnemigo(boolean exito, String mensaje, Enemigo enemigo, List<Enemigo> enemigos) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.enemigo = enemigo;
        this.enemigos = enemigos;
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

    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(List<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

}
