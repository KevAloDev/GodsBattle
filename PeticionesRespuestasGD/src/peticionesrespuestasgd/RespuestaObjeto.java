package com.marina.godsbattle;

import java.io.Serializable;
import java.util.List;
import pojosgd.Objeto;

public class RespuestaObjeto implements Serializable {
    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;
    
    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;
    
    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Usuario no encontrado", "Guardado", "Actualizado")
    //Delete/Update/Create
    private String mensaje;
    
    // 4. ¿QUE INFORMACIÓN DE EMPLEADO (OBJETO) TIENES? (Create/Read/Update)
    private Objeto objeto;
    
    // 5. ¿Y SI HEMOS PEDIDO MUCHOS EMPLEADOS? (Read_all)
    private List<Objeto> objetos;
    
    //6. CONSTRUCTORES
    
    //6.1. Constructor Vacio
    public RespuestaObjeto() {
        //NADA
    }
    // 6.2 Constructor completo

    public RespuestaObjeto(boolean exito, String mensaje, Objeto objeto, List<Objeto> objetos) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.objeto = objeto;
        this.objetos = objetos;
    }
    
    //7.GETTERS AND SETTERS
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

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }
    
}
