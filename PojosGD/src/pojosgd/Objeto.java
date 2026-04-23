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
public class Objeto implements Serializable
{
    private Integer objetoIdObjeto;
    private String objetoNombre;
    private String objetoDescripcion;
    private Integer objetoPrecio;
    private Integer objetoUsos;
    private Integer objetoFuerza;
    private Integer objetoEfecto;

    public Objeto() {
    }

    public Objeto(Integer objetoIdObjeto, String objetoNombre, String objetoDescripcion, Integer objetoPrecio, Integer objetoUsos, Integer objetoFuerza, Integer objetoEfecto) {
        this.objetoIdObjeto = objetoIdObjeto;
        this.objetoNombre = objetoNombre;
        this.objetoDescripcion = objetoDescripcion;
        this.objetoPrecio = objetoPrecio;
        this.objetoUsos = objetoUsos;
        this.objetoFuerza = objetoFuerza;
        this.objetoEfecto = objetoEfecto;
    }

    public Integer getObjetoIdObjeto() {
        return objetoIdObjeto;
    }

    public void setObjetoIdObjeto(Integer objetoIdObjeto) {
        this.objetoIdObjeto = objetoIdObjeto;
    }

    public String getObjetoNombre() {
        return objetoNombre;
    }

    public void setObjetoNombre(String objetoNombre) {
        this.objetoNombre = objetoNombre;
    }

    public String getObjetoDescripcion() {
        return objetoDescripcion;
    }

    public void setObjetoDescripcion(String objetoDescripcion) {
        this.objetoDescripcion = objetoDescripcion;
    }

    public Integer getObjetoPrecio() {
        return objetoPrecio;
    }

    public void setObjetoPrecio(Integer objetoPrecio) {
        this.objetoPrecio = objetoPrecio;
    }

    public Integer getObjetoUsos() {
        return objetoUsos;
    }

    public void setObjetoUsos(Integer objetoUsos) {
        this.objetoUsos = objetoUsos;
    }

    public Integer getObjetoFuerza() {
        return objetoFuerza;
    }

    public void setObjetoFuerza(Integer objetoFuerza) {
        this.objetoFuerza = objetoFuerza;
    }

    public Integer getObjetoEfecto() {
        return objetoEfecto;
    }

    public void setObjetoEfecto(Integer objetoEfecto) {
        this.objetoEfecto = objetoEfecto;
    }

    @Override
    public String toString() {
        return "Objeto{" + "objetoIdObjeto=" + objetoIdObjeto + ", objetoNombre=" + objetoNombre + ", objetoDescripcion=" + objetoDescripcion + ", objetoPrecio=" + objetoPrecio + ", objetoUsos=" + objetoUsos + ", objetoFuerza=" + objetoFuerza + ", objetoEfecto=" + objetoEfecto + '}';
    }

    
}
