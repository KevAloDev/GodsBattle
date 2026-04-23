package peticionesrespuestasgd;

import java.io.Serializable;
import java.util.List;
import pojosgd.Personaje;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 * RESPUESTA:
 * ==============
 * Es el paquete que nos devuelve el servidor. Contiene el resultado de la 
 * operación (éxito/fracaso) y los datos solicitados.
 * 
 */
public class RespuestaPersonaje implements Serializable{
    
    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;
    
    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;
    
    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Usuario no encontrado", "Guardado", "Actualizado")
    //Delete/Update/Create
    private String mensaje;
    
    // 4. ¿QUE INFORMACIÓN DE EMPLEADO (OBJETO) TIENES? (Create/Read/Update)
    private Personaje personaje;
    
    // 5. ¿Y SI HEMOS PEDIDO MUCHOS EMPLEADOS? (Read_all)
    private List<Personaje> personajes;
    
    //6. CONSTRUCTORES
    
    //6.1. Constructor Vacio
    public RespuestaPersonaje() {
        //NADA
    }
    // 6.2 Constructor completo

    public RespuestaPersonaje(boolean exito, String mensaje, Personaje personaje, List<Personaje> personajes) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.personaje = personaje;
        this.personajes = personajes;
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

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
    
   
   
    
}
