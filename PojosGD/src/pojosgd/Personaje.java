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
public class Personaje implements Serializable
{
    private Usuario personajeIdUsuario;
    private String personajeNombre;
    private Integer personajeVidaMaxima;
    private Integer personajeDanoBase;
    private Integer personajeDefensaBase;
    private Integer personajeDinero;
    private Arma personajeArma;
    private Armadura personajeArmadura;
    private Piso personajePiso;

    public Personaje() {
    }

    public Personaje(Usuario personajeIdUsuario, String personajeNombre, Integer personajeVidaMaxima, Integer personajeDanoBase, Integer personajeDefensaBase, Integer personajeDinero, Arma personajeArma, Armadura personajeArmadura, Piso personajePiso) {
        this.personajeIdUsuario = personajeIdUsuario;
        this.personajeNombre = personajeNombre;
        this.personajeVidaMaxima = personajeVidaMaxima;
        this.personajeDanoBase = personajeDanoBase;
        this.personajeDefensaBase = personajeDefensaBase;
        this.personajeDinero = personajeDinero;
        this.personajeArma = personajeArma;
        this.personajeArmadura = personajeArmadura;
        this.personajePiso = personajePiso;
    }

    public Usuario getPersonajeIdUsuario() {
        return personajeIdUsuario;
    }

    public void setPersonajeIdUsuario(Usuario personajeIdUsuario) {
        this.personajeIdUsuario = personajeIdUsuario;
    }

    public String getPersonajeNombre() {
        return personajeNombre;
    }

    public void setPersonajeNombre(String personajeNombre) {
        this.personajeNombre = personajeNombre;
    }

    public Integer getPersonajeVidaMaxima() {
        return personajeVidaMaxima;
    }

    public void setPersonajeVidaMaxima(Integer personajeVidaMaxima) {
        this.personajeVidaMaxima = personajeVidaMaxima;
    }

    public Integer getPersonajeDanoBase() {
        return personajeDanoBase;
    }

    public void setPersonajeDanoBase(Integer personajeDanoBase) {
        this.personajeDanoBase = personajeDanoBase;
    }

    public Integer getPersonajeDefensaBase() {
        return personajeDefensaBase;
    }

    public void setPersonajeDefensaBase(Integer personajeDefensaBase) {
        this.personajeDefensaBase = personajeDefensaBase;
    }

    public Integer getPersonajeDinero() {
        return personajeDinero;
    }

    public void setPersonajeDinero(Integer personajeDinero) {
        this.personajeDinero = personajeDinero;
    }

    public Arma getPersonajeArma() {
        return personajeArma;
    }

    public void setPersonajeArma(Arma personajeArma) {
        this.personajeArma = personajeArma;
    }

    public Armadura getPersonajeArmadura() {
        return personajeArmadura;
    }

    public void setPersonajeArmadura(Armadura personajeArmadura) {
        this.personajeArmadura = personajeArmadura;
    }

    public Piso getPersonajePiso() {
        return personajePiso;
    }

    public void setPersonajePiso(Piso personajePiso) {
        this.personajePiso = personajePiso;
    }

    @Override
    public String toString() {
        return "Personaje{" + "personajeIdUsuario=" + personajeIdUsuario + ", personajeNombre=" + personajeNombre + ", personajeVidaMaxima=" + personajeVidaMaxima + ", personajeDanoBase=" + personajeDanoBase + ", personajeDefensaBase=" + personajeDefensaBase + ", personajeDinero=" + personajeDinero + ", personajeArma=" + personajeArma + ", personajeArmadura=" + personajeArmadura + ", personajePiso=" + personajePiso + '}';
    }    
}
