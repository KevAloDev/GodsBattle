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
public class Usuario implements Serializable
{
    private Integer usuarioIdUsuario;
    private String usuarioNombre;
    private String usuarioCorreo;
    private String usuarioContrasena;
    private Rol usuarioIdRol;

    public Usuario() {
    }

    public Usuario(Integer usuarioIdUsuario, String usuarioNombre, String usuarioCorreo, String usuarioContrasena, Rol usuarioIdRol) {
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.usuarioNombre = usuarioNombre;
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioContrasena = usuarioContrasena;
        this.usuarioIdRol = usuarioIdRol;
    }

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Integer usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

    public Rol getUsuarioIdRol() {
        return usuarioIdRol;
    }

    public void setUsuarioIdRol(Rol usuarioIdRol) {
        this.usuarioIdRol = usuarioIdRol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioIdUsuario=" + usuarioIdUsuario + ", usuarioNombre=" + usuarioNombre + ", usuarioCorreo=" + usuarioCorreo + ", usuarioContrasena=" + usuarioContrasena + ", usuarioIdRol=" + usuarioIdRol + '}';
    }
}
