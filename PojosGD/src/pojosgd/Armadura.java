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
public class Armadura implements Serializable
{
    private Integer armaduraId;
    private String armaduraNombre;
    private Integer armaduraDefensa;
    private Integer armaduraPrecio;

    public Armadura() {
    }

    public Armadura(Integer armaduraId, String armaduraNombre, Integer armaduraDefensa, Integer armaduraPrecio) {
        this.armaduraId = armaduraId;
        this.armaduraNombre = armaduraNombre;
        this.armaduraDefensa = armaduraDefensa;
        this.armaduraPrecio = armaduraPrecio;
    }

    public Integer getArmaduraId() {
        return armaduraId;
    }

    public void setArmaduraId(Integer armaduraId) {
        this.armaduraId = armaduraId;
    }

    public String getArmaduraNombre() {
        return armaduraNombre;
    }

    public void setArmaduraNombre(String armaduraNombre) {
        this.armaduraNombre = armaduraNombre;
    }

    public Integer getArmaduraDefensa() {
        return armaduraDefensa;
    }

    public void setArmaduraDefensa(Integer armaduraDefensa) {
        this.armaduraDefensa = armaduraDefensa;
    }

    public Integer getArmaduraPrecio() {
        return armaduraPrecio;
    }

    public void setArmaduraPrecio(Integer armaduraPrecio) {
        this.armaduraPrecio = armaduraPrecio;
    }

    @Override
    public String toString() {
        return "Armadura{" + "armaduraId=" + armaduraId + ", armaduraNombre=" + armaduraNombre + ", armaduraDefensa=" + armaduraDefensa + ", armaduraPrecio=" + armaduraPrecio + '}';
    }    
}
