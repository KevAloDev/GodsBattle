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
public class Configuracion implements Serializable
{
    private Integer configuracionVidaMaximaPredeterminado;
    private Integer configuracionDanoBasePredeterminado;
    private Integer configuracionDefensaBasePredeterminado;

    public Configuracion() {
    }

    public Configuracion(Integer configuracionVidaMaximaPredeterminado, Integer configuracionDanoBasePredeterminado, Integer configuracionDefensaBasePredeterminado) {
        this.configuracionVidaMaximaPredeterminado = configuracionVidaMaximaPredeterminado;
        this.configuracionDanoBasePredeterminado = configuracionDanoBasePredeterminado;
        this.configuracionDefensaBasePredeterminado = configuracionDefensaBasePredeterminado;
    }

    public Integer getConfiguracionVidaMaximaPredeterminado() {
        return configuracionVidaMaximaPredeterminado;
    }

    public void setConfiguracionVidaMaximaPredeterminado(Integer configuracionVidaMaximaPredeterminado) {
        this.configuracionVidaMaximaPredeterminado = configuracionVidaMaximaPredeterminado;
    }

    public Integer getConfiguracionDanoBasePredeterminado() {
        return configuracionDanoBasePredeterminado;
    }

    public void setConfiguracionDanoBasePredeterminado(Integer configuracionDanoBasePredeterminado) {
        this.configuracionDanoBasePredeterminado = configuracionDanoBasePredeterminado;
    }

    public Integer getConfiguracionDefensaBasePredeterminado() {
        return configuracionDefensaBasePredeterminado;
    }

    public void setConfiguracionDefensaBasePredeterminado(Integer configuracionDefensaBasePredeterminado) {
        this.configuracionDefensaBasePredeterminado = configuracionDefensaBasePredeterminado;
    }

    @Override
    public String toString() {
        return "Configuracion{" + "configuracionVidaMaximaPredeterminado=" + configuracionVidaMaximaPredeterminado + ", configuracionDanoBasePredeterminado=" + configuracionDanoBasePredeterminado + ", configuracionDefensaBasePredeterminado=" + configuracionDefensaBasePredeterminado + '}';
    }
    
    
}
