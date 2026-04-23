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
public class Enemigo implements Serializable{
    private Integer enemigoIdEnemigo;
    private String enemigoNombre;
    private Integer enemigoVidaMaxima;
    private Integer enemigoDanoBase;
    private Integer enemigoDefensaBase;
    private Integer enemigoDinero;

    public Enemigo() {
    }

    public Enemigo(Integer enemigoIdEnemigo, String enemigoNombre, Integer enemigoVidaMaxima, Integer enemigoDanoBase, Integer enemigoDefensaBase, Integer enemigoDinero) {
        this.enemigoIdEnemigo = enemigoIdEnemigo;
        this.enemigoNombre = enemigoNombre;
        this.enemigoVidaMaxima = enemigoVidaMaxima;
        this.enemigoDanoBase = enemigoDanoBase;
        this.enemigoDefensaBase = enemigoDefensaBase;
        this.enemigoDinero = enemigoDinero;
    }

    public Integer getEnemigoIdEnemigo() {
        return enemigoIdEnemigo;
    }

    public void setEnemigoIdEnemigo(Integer enemigoIdEnemigo) {
        this.enemigoIdEnemigo = enemigoIdEnemigo;
    }

    public String getEnemigoNombre() {
        return enemigoNombre;
    }

    public void setEnemigoNombre(String enemigoNombre) {
        this.enemigoNombre = enemigoNombre;
    }

    public Integer getEnemigoVidaMaxima() {
        return enemigoVidaMaxima;
    }

    public void setEnemigoVidaMaxima(Integer enemigoVidaMaxima) {
        this.enemigoVidaMaxima = enemigoVidaMaxima;
    }

    public Integer getEnemigoDanoBase() {
        return enemigoDanoBase;
    }

    public void setEnemigoDanoBase(Integer enemigoDanoBase) {
        this.enemigoDanoBase = enemigoDanoBase;
    }

    public Integer getEnemigoDefensaBase() {
        return enemigoDefensaBase;
    }

    public void setEnemigoDefensaBase(Integer enemigoDefensaBase) {
        this.enemigoDefensaBase = enemigoDefensaBase;
    }

    public Integer getEnemigoDinero() {
        return enemigoDinero;
    }

    public void setEnemigoDinero(Integer enemigoDinero) {
        this.enemigoDinero = enemigoDinero;
    }

    @Override
    public String toString() {
        return "Enemigo{" + "enemigoIdEnemigo=" + enemigoIdEnemigo + ", enemigoNombre=" + enemigoNombre + ", enemigoVidaMaxima=" + enemigoVidaMaxima + ", enemigoDanoBase=" + enemigoDanoBase + ", enemigoDefensaBase=" + enemigoDefensaBase + ", enemigoDinero=" + enemigoDinero + '}';
    }
    

    
}


