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
public class Arma implements Serializable
{
    private Integer armaId;
    private String armaNombre;
    private Integer armaDano;
    private Integer armaPrecio;

    public Arma() {
    }

    public Arma(Integer armaId, String armaNombre, Integer armaDano, Integer armaPrecio) {
        this.armaId = armaId;
        this.armaNombre = armaNombre;
        this.armaDano = armaDano;
        this.armaPrecio = armaPrecio;
    }

    public Integer getArmaId() {
        return armaId;
    }

    public void setArmaId(Integer armaId) {
        this.armaId = armaId;
    }

    public String getArmaNombre() {
        return armaNombre;
    }

    public void setArmaNombre(String armaNombre) {
        this.armaNombre = armaNombre;
    }

    public Integer getArmaDano() {
        return armaDano;
    }

    public void setArmaDano(Integer armaDano) {
        this.armaDano = armaDano;
    }

    public Integer getArmaPrecio() {
        return armaPrecio;
    }

    public void setArmaPrecio(Integer armaPrecio) {
        this.armaPrecio = armaPrecio;
    }

    @Override
    public String toString() {
        return "Arma{" + "armaId=" + armaId + ", armaNombre=" + armaNombre + ", armaDano=" + armaDano + ", armaPrecio=" + armaPrecio + '}';
    }
}
