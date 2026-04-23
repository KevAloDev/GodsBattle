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
public class EnemigoHabilidad implements Serializable
{
    private Enemigo enemigoHabilidadIdEnemigo;
    private Habilidad enemigoHabilidadIdHabilidad;

    public EnemigoHabilidad() {
    }

    public EnemigoHabilidad(Enemigo enemigoHabilidadIdEnemigo, Habilidad enemigoHabilidadIdHabilidad) {
        this.enemigoHabilidadIdEnemigo = enemigoHabilidadIdEnemigo;
        this.enemigoHabilidadIdHabilidad = enemigoHabilidadIdHabilidad;
    }

    public Enemigo getEnemigoHabilidadIdEnemigo() {
        return enemigoHabilidadIdEnemigo;
    }

    public void setEnemigoHabilidadIdEnemigo(Enemigo enemigoHabilidadIdEnemigo) {
        this.enemigoHabilidadIdEnemigo = enemigoHabilidadIdEnemigo;
    }

    public Habilidad getEnemigoHabilidadIdHabilidad() {
        return enemigoHabilidadIdHabilidad;
    }

    public void setEnemigoHabilidadIdHabilidad(Habilidad enemigoHabilidadIdHabilidad) {
        this.enemigoHabilidadIdHabilidad = enemigoHabilidadIdHabilidad;
    }

    @Override
    public String toString() {
        return "EnemigoHabilidad{" + "enemigoHabilidadIdEnemigo=" + enemigoHabilidadIdEnemigo + ", enemigoHabilidadIdHabilidad=" + enemigoHabilidadIdHabilidad + '}';
    }
}
