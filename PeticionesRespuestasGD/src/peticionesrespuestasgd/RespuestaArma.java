package com.marina.godsbattle;

import java.io.Serializable;
import java.util.List;
import pojosgd.Arma;

public class RespuestaArma implements Serializable {
    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;
    
    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;
    
    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Usuario no encontrado", "Guardado", "Actualizado")
    //Delete/Update/Create
    private String mensaje;
    
    // 4. ¿QUE INFORMACIÓN DE EMPLEADO (OBJETO) TIENES? (Create/Read/Update)
    private Arma arma;
    
    // 5. ¿Y SI HEMOS PEDIDO MUCHOS EMPLEADOS? (Read_all)
    private List<Arma> armas;
    
    //6. CONSTRUCTORES
    
    //6.1. Constructor Vacio
    public RespuestaArma() {
        //NADA
    }
    // 6.2 Constructor completo

    public RespuestaArma(boolean exito, String mensaje, Arma arma, List<Arma> armas) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.arma = arma;
        this.armas = armas;
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

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public void setArmas(List<Arma> armas) {
        this.armas = armas;
    }
    
}
