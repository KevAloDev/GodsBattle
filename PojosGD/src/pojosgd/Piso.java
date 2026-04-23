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
public class Piso implements Serializable
{
    private Integer pisoId;
    private Integer pisoNumero;
    private String pisoFondo;

    public Piso() {
    }

    public Piso(Integer pisoId, Integer pisoNumero, String pisoFondo) {
        this.pisoId = pisoId;
        this.pisoNumero = pisoNumero;
        this.pisoFondo = pisoFondo;
    }

    public Integer getPisoId() {
        return pisoId;
    }

    public void setPisoId(Integer pisoId) {
        this.pisoId = pisoId;
    }

    public Integer getPisoNumero() {
        return pisoNumero;
    }

    public void setPisoNumero(Integer pisoNumero) {
        this.pisoNumero = pisoNumero;
    }

    public String getPisoFondo() {
        return pisoFondo;
    }

    public void setPisoFondo(String pisoFondo) {
        this.pisoFondo = pisoFondo;
    }

    @Override
    public String toString() {
        return "Piso{" + "pisoId=" + pisoId + ", pisoNumero=" + pisoNumero + ", pisoFondo=" + pisoFondo + '}';
    }    
}
