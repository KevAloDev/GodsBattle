package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.EnemigoPiso;

/**
 * RESPUESTA ENEMIGOPISO: 
 * Es el paquete que nos devuelve el servidor.
 * Contiene el resultado de la operación (éxito/fracaso) y los datos solicitados.
 */
public class RespuestaEnemigoPiso implements Serializable {
    // 1. Identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIO BIEN LA OPERACION?
    private boolean exito;

    // 3. ¿HAY INFORMACION PARA EL CLIENTE?
    private String mensaje;

    // 4. ¿QUE INFORMACION DE ENEMIGO TIENES? (Create/Read/Update)
    private EnemigoPiso enemigoPiso;

    // 5. ¿Y SI HEMOS PEDIDO MUCHOS ENEMIGOS? (Read_all)
    private List<EnemigoPiso> enemigoPisos;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public RespuestaEnemigoPiso() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaEnemigoPiso(boolean exito, String mensaje, EnemigoPiso enemigoPiso, List<EnemigoPiso> enemigoPisos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.enemigoPiso = enemigoPiso;
        this.enemigoPisos = enemigoPisos;
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

    public EnemigoPiso getEnemigoPiso() {
        return enemigoPiso;
    }

    public void setEnemigoPiso(EnemigoPiso enemigoPiso) {
        this.enemigoPiso = enemigoPiso;
    }

    public List<EnemigoPiso> getEnemigoPisos() {
        return enemigoPisos;
    }

    public void setEnemigoPisos(List<EnemigoPiso> enemigoPisos) {
        this.enemigoPisos = enemigoPisos;
    }

}