/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojosgd;
import java.io.Serializable;
/**
 *
 * @author DAM203
 */
public class PersonajeHabilidad implements Serializable{
    
    private Personaje personajeHabilidadIdUsuario;
    private Habilidad personajeHabilidadIdHabilidad;

    public PersonajeHabilidad() {
    }

    public PersonajeHabilidad(Personaje personajeHabilidadIdUsuario, Habilidad personajeHabilidadIdHabilidad) {
        this.personajeHabilidadIdUsuario = personajeHabilidadIdUsuario;
        this.personajeHabilidadIdHabilidad = personajeHabilidadIdHabilidad;
    }

    public Personaje getPersonajeHabilidadIdUsuario() {
        return personajeHabilidadIdUsuario;
    }

    public void setPersonajeHabilidadIdUsuario(Personaje personajeHabilidadIdUsuario) {
        this.personajeHabilidadIdUsuario = personajeHabilidadIdUsuario;
    }

    public Habilidad getPersonajeHabilidadIdHabilidad() {
        return personajeHabilidadIdHabilidad;
    }

    public void setPersonajeHabilidadIdHabilidad(Habilidad personajeHabilidadIdHabilidad) {
        this.personajeHabilidadIdHabilidad = personajeHabilidadIdHabilidad;
    }

    @Override
    public String toString() {
        return "PersonajeHabilidad{" + "personajeHabilidadIdUsuario=" + personajeHabilidadIdUsuario + ", personajeHabilidadIdHabilidad=" + personajeHabilidadIdHabilidad + '}';
    }
}
