package com.marina.godsbattle;

import java.io.Serializable;
import java.util.List;
import pojosgd.PersonajeHabilidad;

public class RespuestaPersonajeHabilidad implements Serializable {
        
    // 1. Identificación: creamos un identificador único de versión de
    //serialización
    private static final long serialVersionUID = 1L;
    
    // 2. ¿SALIÓ BIEN LA OPERACIÓN?
    private boolean exito;
    
    // 3. ¿HAY INFORMACIÓN PARA EL CLIENTE? ("Usuario no encontrado", "Guardado", "Actualizado")
    //Delete/Update/Create
    private String mensaje;
    
    // 4. ¿QUE INFORMACIÓN DE EMPLEADO (OBJETO) TIENES? (Create/Read/Update)
    private PersonajeHabilidad personajeHabilidad;
    
    // 5. ¿Y SI HEMOS PEDIDO MUCHOS EMPLEADOS? (Read_all)
    private List<PersonajeHabilidad> personajeHabilidades;
    
    //6. CONSTRUCTORES
    
    //6.1. Constructor Vacio
    public RespuestaPersonajeHabilidad() {
        //NADA
    }
    // 6.2 Constructor completo

    public RespuestaPersonajeHabilidad(boolean exito, String mensaje, PersonajeHabilidad personajeHabilidad, List<PersonajeHabilidad> personajeHabilidades) {
        super();
        this.exito = exito;
        this.mensaje = mensaje;
        this.personajeHabilidad = personajeHabilidad;
        this.personajeHabilidades = personajeHabilidades;
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

    public PersonajeHabilidad getPersonajeHabilidad() {
        return personajeHabilidad;
    }

    public void setPersonajeHabilidad(PersonajeHabilidad personajeHabilidad) {
        this.personajeHabilidad = personajeHabilidad;
    }

    public List<PersonajeHabilidad> getPersonajeHabilidades() {
        return personajeHabilidades;
    }

    public void setPersonajeHabilidades(List<PersonajeHabilidad> personajeHabilidades) {
        this.personajeHabilidades = personajeHabilidades;
    }

}
