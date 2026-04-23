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
public class PersonajeObjeto implements Serializable
{
    private Personaje personajeObjetoIdUsuario;
    private Objeto personajeObjetoIdObjeto;
    private Integer personajeObjetoUsosRestantes;

    public PersonajeObjeto() {
    }

    public PersonajeObjeto(Personaje personajeObjetoIdUsuario, Objeto personajeObjetoIdObjeto, Integer personajeObjetoUsosRestantes) {
        this.personajeObjetoIdUsuario = personajeObjetoIdUsuario;
        this.personajeObjetoIdObjeto = personajeObjetoIdObjeto;
        this.personajeObjetoUsosRestantes = personajeObjetoUsosRestantes;
    }

    public Personaje getPersonajeObjetoIdUsuario() {
        return personajeObjetoIdUsuario;
    }

    public void setPersonajeObjetoIdUsuario(Personaje personajeObjetoIdUsuario) {
        this.personajeObjetoIdUsuario = personajeObjetoIdUsuario;
    }

    public Objeto getPersonajeObjetoIdObjeto() {
        return personajeObjetoIdObjeto;
    }

    public void setPersonajeObjetoIdObjeto(Objeto personajeObjetoIdObjeto) {
        this.personajeObjetoIdObjeto = personajeObjetoIdObjeto;
    }

    public Integer getPersonajeObjetoUsosRestantes() {
        return personajeObjetoUsosRestantes;
    }

    public void setPersonajeObjetoUsosRestantes(Integer personajeObjetoUsosRestantes) {
        this.personajeObjetoUsosRestantes = personajeObjetoUsosRestantes;
    }

    @Override
    public String toString() {
        return "PersonajeObjeto{" + "personajeObjetoIdUsuario=" + personajeObjetoIdUsuario + ", personajeObjetoIdObjeto=" + personajeObjetoIdObjeto + ", personajeObjetoUsosRestantes=" + personajeObjetoUsosRestantes + '}';
    }
}
