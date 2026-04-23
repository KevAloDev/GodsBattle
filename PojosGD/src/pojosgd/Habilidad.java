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
public class Habilidad implements Serializable
{
    private Integer habilidadId;
    private String habilidadNombre;
    private String habilidadDescripcion;
    private Integer habilidadPrecio;
    private Integer habilidadFuerza;
    private Integer habilidadEfecto;

    public Habilidad() {
    }

    public Habilidad(Integer habilidadId, String habilidadNombre, String habilidadDescripcion, Integer habilidadPrecio, Integer habilidadFuerza, Integer habilidadEfecto) {
        this.habilidadId = habilidadId;
        this.habilidadNombre = habilidadNombre;
        this.habilidadDescripcion = habilidadDescripcion;
        this.habilidadPrecio = habilidadPrecio;
        this.habilidadFuerza = habilidadFuerza;
        this.habilidadEfecto = habilidadEfecto;
    }

    public Integer getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Integer habilidadId) {
        this.habilidadId = habilidadId;
    }

    public String getHabilidadNombre() {
        return habilidadNombre;
    }

    public void setHabilidadNombre(String habilidadNombre) {
        this.habilidadNombre = habilidadNombre;
    }

    public String getHabilidadDescripcion() {
        return habilidadDescripcion;
    }

    public void setHabilidadDescripcion(String habilidadDescripcion) {
        this.habilidadDescripcion = habilidadDescripcion;
    }

    public Integer getHabilidadPrecio() {
        return habilidadPrecio;
    }

    public void setHabilidadPrecio(Integer habilidadPrecio) {
        this.habilidadPrecio = habilidadPrecio;
    }

    public Integer getHabilidadFuerza() {
        return habilidadFuerza;
    }

    public void setHabilidadFuerza(Integer habilidadFuerza) {
        this.habilidadFuerza = habilidadFuerza;
    }

    public Integer getHabilidadEfecto() {
        return habilidadEfecto;
    }

    public void setHabilidadEfecto(Integer habilidadEfecto) {
        this.habilidadEfecto = habilidadEfecto;
    }

    @Override
    public String toString() {
        return "Habilidad{" + "habilidadId=" + habilidadId + ", habilidadNombre=" + habilidadNombre + ", habilidadDescripcion=" + habilidadDescripcion + ", habilidadPrecio=" + habilidadPrecio + ", habilidadFuerza=" + habilidadFuerza + ", habilidadEfecto=" + habilidadEfecto + '}';
    }
}
