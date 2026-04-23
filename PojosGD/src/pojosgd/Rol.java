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
public class Rol implements Serializable
{
    private Integer rolIdRol;
    private String rolNombre;

    public Rol() {
    }

    public Rol(Integer rolIdRol, String rolNombre) {
        this.rolIdRol = rolIdRol;
        this.rolNombre = rolNombre;
    }

    public Integer getRolIdRol() {
        return rolIdRol;
    }

    public void setRolIdRol(Integer rolIdRol) {
        this.rolIdRol = rolIdRol;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    @Override
    public String toString() {
        return "Rol{" + "rolIdRol=" + rolIdRol + ", rolNombre=" + rolNombre + '}';
    }
}
