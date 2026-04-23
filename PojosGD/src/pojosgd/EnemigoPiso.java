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
public class EnemigoPiso implements Serializable{
    
    private Enemigo enemigoPisoIdEnemigo;
    private Piso enemigoPisoIdPiso;

    public EnemigoPiso() {
    }

    public EnemigoPiso(Enemigo enemigoPisoIdEnemigo, Piso enemigoPisoIdPiso) {
        this.enemigoPisoIdEnemigo = enemigoPisoIdEnemigo;
        this.enemigoPisoIdPiso = enemigoPisoIdPiso;
    }

    public Enemigo getEnemigoPisoIdEnemigo() {
        return enemigoPisoIdEnemigo;
    }

    public void setEnemigoPisoIdEnemigo(Enemigo enemigoPisoIdEnemigo) {
        this.enemigoPisoIdEnemigo = enemigoPisoIdEnemigo;
    }

    public Piso getEnemigoPisoIdPiso() {
        return enemigoPisoIdPiso;
    }

    public void setEnemigoPisoIdPiso(Piso enemigoPisoIdPiso) {
        this.enemigoPisoIdPiso = enemigoPisoIdPiso;
    }

    @Override
    public String toString() {
        return "EnemigoPiso{" + "enemigoPisoIdEnemigo=" + enemigoPisoIdEnemigo + ", enemigoPisoIdPiso=" + enemigoPisoIdPiso + '}';
    }
}
