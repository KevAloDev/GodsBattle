package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.Armadura;

/**
 * RESPUESTA ARMADURA: 
 * Es el paquete que nos devuelve el servidor.
 * Contiene el resultado de la operación (éxito/fracaso) y los datos solicitados.
 */
public class RespuestaArmadura implements Serializable {
    // 1. Identificación: creamos un identificador único de versión de serialización
    private static final long serialVersionUID = 1L;

    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;

    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Usuario no encontrado", "Guardado", "Actualizado")
    private String mensaje;

    // 4. ¿QUE INFORMACIÓN DE ARMADURA TIENES? (Create/Read/Update)
    private Armadura armadura;

    // 5. ¿Y SI HEMOS PEDIDO MUCHAS ARMADURAS? (Read_all)
    private List<Armadura> armaduras;

    // 6. CONSTRUCTORES
    // 6.1 Constructor vacío
    public RespuestaArmadura() {
        // NADA
    }

    // 6.2 Constructor completo
    public RespuestaArmadura(boolean exito, String mensaje, Armadura armadura, List<Armadura> armaduras) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.armadura = armadura;
        this.armaduras = armaduras;
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

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    public List<Armadura> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(List<Armadura> armaduras) {
        this.armaduras = armaduras;
    }
}