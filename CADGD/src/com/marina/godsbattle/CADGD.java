/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.godsbattle;

import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pojosgd.*;
import java.util.ArrayList;

/**
 *
 * @author DAM203
 */
public class CADGD {

    private Connection conexion;
    private static final String IP = ServerConfig.getDbIp();
    private static final String PORT = ServerConfig.DB_PORT;
    private static final String SID = ServerConfig.DB_SID;
    private static final String USER = ServerConfig.DB_USER;
    private static final String PASSWORD = ServerConfig.DB_PASS;

    private static final String URL = "jdbc:oracle:thin:@" + IP + ":" + PORT + ":" + SID;

    public CADGD() throws ExcepcionGD {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setMensajeErrorBD(ex.getMessage());
            e.setMensajeErrorUsuario("Error general del sistema. Consulta con el administrador");
            throw e;
        }
    }

    public boolean conectarBD() throws ExcepcionGD {
        try {
            this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setMensajeErrorUsuario("Error general del sistema. Consulta con el administrador");
            throw e;
        }
    }

    //LEER-----------------------------------------------------------------------
    /**
     * Este método leerá todas los usuarios de la tabla "Usuario"
     *
     * @return Devuelve un ArrayList con objetos de tipo "Usuario"
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Iván
     */
    public ArrayList<Usuario> leerUsuarios() throws ExcepcionGD {
        conectarBD();
        String dql = "select * from USUARIO";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(resultado.getInt("ID_USUARIO"));
                usuario.setUsuarioNombre(resultado.getString("NOMBRE"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));

                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre("Jugador");
                usuario.setUsuarioIdRol(rol);
                usuarios.add(usuario);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exHR = new ExcepcionGD();
            exHR.setCodigoErrorBD(ex.getErrorCode());
            exHR.setMensajeErrorBD(ex.getMessage());
            exHR.setSentenciaSQL(dql);
            exHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw exHR;
        }

        return usuarios;
    }

    // NUEVOS METODOS A INCORPORAR, KEVIN Y SIMÓN
    // Select de Armadura
    /**
     * Lee una armadura de la base de datos a partir de su identificador.
     *
     * @param idArmadura Identificador de la armadura que se desea consultar
     * @return Un objeto de tipo Armadura con los datos que se han leído de la
     * base de datos, o null si no existe
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public Armadura leerArmadura(Integer idArmadura) throws ExcepcionGD {
        conectarBD();
        Armadura armadura = null;

        String dql = "SELECT * FROM ARMADURA WHERE ID_ARMADURA = " + idArmadura;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                armadura = new Armadura();
                armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                armadura.setArmaduraNombre(resultado.getString("NOMBRE"));
                armadura.setArmaduraDefensa(resultado.getInt("DEFENSA"));
                armadura.setArmaduraPrecio(resultado.getInt("PRECIO"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return armadura;
    }

    //Select de EnemigoPiso
    /**
     * Lee un registro de EnemigoPiso de la base de datos a partir de su
     * identificador.
     *
     * @param idEnemigo Identificador del enemigo cuyo piso se desea consultar
     * @param idPiso Identificador del piso que se desea consultar
     * @return Un objeto de tipo Enemigopiso con los datos que se han leído de
     * la base de datos, o null si no existe
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public EnemigoPiso leerEnemigoPiso(Integer idEnemigo, Integer idPiso) throws ExcepcionGD {
        conectarBD();
        EnemigoPiso enemigoPiso = null;

        String dql = "SELECT * "
                + "FROM enemigopiso ep, enemigo e, piso p "
                + "WHERE ep.id_enemigo = e.id_enemigo "
                + "AND ep.id_piso = p.id_piso "
                + "AND ep.id_enemigo = " + idEnemigo
                + " AND ep.id_piso = " + idPiso;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                enemigoPiso = new EnemigoPiso();

                Enemigo enemigo = new Enemigo();
                enemigo.setEnemigoIdEnemigo(resultado.getInt("ID_ENEMIGO"));
                enemigo.setEnemigoNombre(resultado.getString("NOMBRE"));
                enemigo.setEnemigoVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                enemigo.setEnemigoDanoBase(resultado.getInt("DANO_BASE"));
                enemigo.setEnemigoDefensaBase(resultado.getInt("DEFENSA_BASE"));
                enemigo.setEnemigoDinero(resultado.getInt("DINERO"));
                enemigoPiso.setEnemigoPisoIdEnemigo(enemigo);

                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO"));
                enemigoPiso.setEnemigoPisoIdPiso(piso);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return enemigoPiso;
    }

    //Select_All de Armadura 
    /**
     * Lee todos los registros de la tabla Armadura de la base de datos.
     *
     * @return Una lista de objetos de tipo Armadura con los datos que se han
     * leído de la base de datos
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public ArrayList<Armadura> leerArmaduras() throws ExcepcionGD {
        conectarBD();
        ArrayList<Armadura> armaduras = new ArrayList<>();

        String dql = "SELECT * FROM ARMADURA";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                Armadura armadura = new Armadura();
                armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                armadura.setArmaduraNombre(resultado.getString("NOMBRE"));
                armadura.setArmaduraDefensa(resultado.getInt("DEFENSA"));
                armadura.setArmaduraPrecio(resultado.getInt("PRECIO"));
                armaduras.add(armadura);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();

            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return armaduras;
    }

    //Select_All de EnemigoPiso
    /**
     * Lee todos los registros de la tabla EnemigoPiso de la base de datos.
     *
     * @param idPiso Identificador del piso en el que se buscarán los enemigos
     * disponibles.
     * @return Una lista de objetos de tipo Enemigopiso con los datos que se han
     * leído de la base de datos
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public ArrayList<EnemigoPiso> leerEnemigoPisos(Integer idPiso) throws ExcepcionGD {
        conectarBD();
        ArrayList<EnemigoPiso> enemigoPisos = new ArrayList<>();

        String dql = "SELECT * "
                + "FROM enemigopiso ep, enemigo e, piso p "
                + "WHERE ep.id_enemigo = e.id_enemigo "
                + "AND ep.id_piso = p.id_piso "
                + "AND ep.id_piso = " + idPiso;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                EnemigoPiso enemigoPiso = new EnemigoPiso();

                Enemigo enemigo = new Enemigo();
                enemigo.setEnemigoIdEnemigo(resultado.getInt("ID_ENEMIGO"));
                enemigo.setEnemigoNombre(resultado.getString("NOMBRE"));
                enemigo.setEnemigoVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                enemigo.setEnemigoDanoBase(resultado.getInt("DANO_BASE"));
                enemigo.setEnemigoDefensaBase(resultado.getInt("DEFENSA_BASE"));
                enemigo.setEnemigoDinero(resultado.getInt("DINERO"));
                enemigoPiso.setEnemigoPisoIdEnemigo(enemigo);

                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO"));
                enemigoPiso.setEnemigoPisoIdPiso(piso);

                enemigoPisos.add(enemigoPiso);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return enemigoPisos;
    }

    //Select de Usuario
    /**
     * Lee un registro de Usuario de la base de datos a partir de su
     * identificador.
     *
     * @param idUsuario Identificador del usuario que se desea consultar
     * @return Un objeto de tipo Usuario con los datos que se han leído de la
     * base de datos, o null si no existe
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public Usuario leerUsuario(Integer idUsuario) throws ExcepcionGD {
        conectarBD();
        Usuario usuario = null;

        String dql = "SELECT * FROM USUARIO WHERE ID_USUARIO = " + idUsuario;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setUsuarioIdUsuario(resultado.getInt("ID_USUARIO"));
                usuario.setUsuarioNombre(resultado.getString("NOMBRE"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));

                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                usuario.setUsuarioIdRol(rol);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return usuario;
    }

    /**
     * Lee un registro de Usuario de la base de datos a partir de su correo.
     *
     * @param idUsuario Identificador del usuario que se desea consultar
     * @return Un objeto de tipo Usuario con los datos que se han leído de la
     * base de datos, o null si no existe
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public Usuario leerUsuarioLogin(String correo) throws ExcepcionGD {
        conectarBD();
        Usuario usuario = null;

        String dql = "select * from USUARIO where CORREO = '" + correo + "'";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setUsuarioIdUsuario(resultado.getInt("ID_USUARIO"));
                usuario.setUsuarioNombre(resultado.getString("NOMBRE"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));

                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                usuario.setUsuarioIdRol(rol);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return usuario;
    }

    //Select_All de Habilidad
    /**
     * Lee todos los registros de la tabla Habilidad de la base de datos.
     *
     * @return Una lista de objetos de tipo Habilidad con los datos que se han
     * leído de la base de datos
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public ArrayList<Habilidad> leerHabilidades() throws ExcepcionGD {
        conectarBD();
        ArrayList<Habilidad> habilidades = new ArrayList<>();

        String dql = "SELECT * FROM HABILIDAD";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                Habilidad habilidad = new Habilidad();
                habilidad.setHabilidadId(resultado.getInt("ID_HABILIDAD"));
                habilidad.setHabilidadNombre(resultado.getString("NOMBRE"));
                habilidad.setHabilidadDescripcion(resultado.getString("DESCRIPCION"));
                habilidad.setHabilidadPrecio(resultado.getInt("PRECIO"));
                habilidad.setHabilidadFuerza(resultado.getInt("FUERZA"));
                habilidad.setHabilidadEfecto(resultado.getInt("EFECTO"));

                habilidades.add(habilidad);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return habilidades;
    }

    //Select_All de Personaje
    /**
     * Lee todos los registros de la tabla Personaje de la base de datos.
     *
     * @return Una lista de objetos de tipo Personaje con los datos que se han
     * leído de la base de datos
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón Ortiz
     */
    public ArrayList<Personaje> leerPersonajes() throws ExcepcionGD {
        conectarBD();
        ArrayList<Personaje> personajes = new ArrayList<>();

        String dql = "SELECT PERSONAJE.*, "
                + "USUARIO.NOMBRE AS NOMBRE_USUARIO, USUARIO.CORREO, USUARIO.CONTRASENA, USUARIO.ID_ROL, "
                + "ROL.NOMBRE AS NOMBRE_ROL, "
                + "ARMA.ID_ARMA, ARMA.NOMBRE AS NOMBRE_ARMA, ARMA.DANO, ARMA.PRECIO AS PRECIO_ARMA, "
                + "ARMADURA.ID_ARMADURA, ARMADURA.NOMBRE AS NOMBRE_ARMADURA, ARMADURA.DEFENSA, ARMADURA.PRECIO AS PRECIO_ARMADURA, "
                + "PISO.ID_PISO, PISO.NUMERO_PISO AS NUMERO_PISO, PISO.FONDO AS FONDO_PISO "
                + "FROM PERSONAJE "
                + "JOIN USUARIO ON PERSONAJE.ID_USUARIO = USUARIO.ID_USUARIO "
                + "JOIN ROL ON USUARIO.ID_ROL = ROL.ID_ROL "
                + "LEFT JOIN ARMA ON PERSONAJE.ID_ARMA = ARMA.ID_ARMA "
                + "LEFT JOIN ARMADURA ON PERSONAJE.ID_ARMADURA = ARMADURA.ID_ARMADURA "
                + "JOIN PISO ON PERSONAJE.ID_PISO = PISO.ID_PISO";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                Personaje personaje = new Personaje();

                // ===== USUARIO =====
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(resultado.getInt("ID_USUARIO"));
                usuario.setUsuarioNombre(resultado.getString("NOMBRE_USUARIO"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));

                // ===== ROL =====
                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre(resultado.getString("NOMBRE_ROL"));

                usuario.setUsuarioIdRol(rol);
                personaje.setPersonajeIdUsuario(usuario);

                // ===== PERSONAJE =====
                personaje.setPersonajeNombre(resultado.getString("NOMBRE"));
                personaje.setPersonajeVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                personaje.setPersonajeDanoBase(resultado.getInt("DANO_BASE"));
                personaje.setPersonajeDefensaBase(resultado.getInt("DEFENSA_BASE"));
                personaje.setPersonajeDinero(resultado.getInt("DINERO"));

                // ===== ARMA =====
                int idArma = resultado.getInt("ID_ARMA");
                if (!resultado.wasNull()) {
                    Arma arma = new Arma();
                    arma.setArmaId(idArma);
                    arma.setArmaNombre(resultado.getString("NOMBRE_ARMA"));
                    arma.setArmaDano(resultado.getInt("DANO_ARMA"));          // ✔ ahora sí existe
                    arma.setArmaPrecio(resultado.getInt("PRECIO_ARMA"));
                    personaje.setPersonajeArma(arma);
                }

                // ===== ARMADURA =====
                int idArmadura = resultado.getInt("ID_ARMADURA");
                if (!resultado.wasNull()) {
                    Armadura armadura = new Armadura();
                    armadura.setArmaduraId(idArmadura);
                    armadura.setArmaduraNombre(resultado.getString("NOMBRE_ARMADURA"));
                    armadura.setArmaduraDefensa(resultado.getInt("DEFENSA_ARMADURA")); // ✔ ahora sí existe
                    armadura.setArmaduraPrecio(resultado.getInt("PRECIO_ARMADURA"));
                    personaje.setPersonajeArmadura(armadura);
                }

                // ===== PISO =====
                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO_PISO"));
                personaje.setPersonajePiso(piso);

                personajes.add(personaje);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return personajes;
    }

    //Select_All de EnemigoHabilidad
    /**
     * Lee todos los registros de la tabla EnemigoHabilidad de la base de datos.
     *
     * @param idEnemigo Identificador del enemigo que queremos conocer sus
     * habilidades.
     * @return Una lista de objetos de tipo Enemigohabilidad con los datos
     * leídos de la base de datos.
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos.
     */
    public ArrayList<EnemigoHabilidad> leerEnemigoHabilidades(Integer idEnemigo) throws ExcepcionGD {
        conectarBD();
        ArrayList<EnemigoHabilidad> enemigoHabilidades = new ArrayList<>();

        String dql = "SELECT eh.*, e.*, h.*, "
                + "e.nombre AS nombre_enemigo, "
                + "h.nombre AS nombre_habilidad "
                + "FROM enemigohabilidad eh, enemigo e, habilidad h "
                + "WHERE eh.id_enemigo = e.id_enemigo "
                + "AND eh.id_habilidad = h.id_habilidad "
                + "AND eh.id_enemigo = " + idEnemigo;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                EnemigoHabilidad enemigoHabilidad = new EnemigoHabilidad();

                Enemigo enemigo = new Enemigo();
                enemigo.setEnemigoIdEnemigo(resultado.getInt("ID_ENEMIGO"));
                enemigo.setEnemigoNombre(resultado.getString("NOMBRE_ENEMIGO"));
                enemigo.setEnemigoVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                enemigo.setEnemigoDanoBase(resultado.getInt("DANO_BASE"));
                enemigo.setEnemigoDefensaBase(resultado.getInt("DEFENSA_BASE"));
                enemigo.setEnemigoDinero(resultado.getInt("DINERO"));
                enemigoHabilidad.setEnemigoHabilidadIdEnemigo(enemigo);

                Habilidad habilidad = new Habilidad();
                habilidad.setHabilidadId(resultado.getInt("ID_HABILIDAD"));
                habilidad.setHabilidadNombre(resultado.getString("NOMBRE_HABILIDAD"));
                habilidad.setHabilidadDescripcion(resultado.getString("DESCRIPCION"));
                habilidad.setHabilidadPrecio(resultado.getInt("PRECIO"));
                habilidad.setHabilidadFuerza(resultado.getInt("FUERZA"));
                habilidad.setHabilidadEfecto(resultado.getInt("EFECTO"));
                enemigoHabilidad.setEnemigoHabilidadIdHabilidad(habilidad);

                enemigoHabilidades.add(enemigoHabilidad);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema.");
            throw e;
        }
        return enemigoHabilidades;
    }

    /**
     * Este método leerá un objeto singular de la base de datos GodsBattle de la
     * tabla Objeto
     *
     * @param idObjeto Un integer que se relacionará con la clave primaria de un
     * registro de la tabla Objeto
     * @return Objeto con los datos del registro que se haya leído
     * @throws ExcepcionGD Se lanzará cuando ocurre una excepción SQL
     * @author Iván
     */
    public Objeto leerObjeto(Integer idObjeto) throws ExcepcionGD {
        conectarBD();
        Objeto o = null;
        String dql = "select * from OBJETO where ID_OBJETO = " + idObjeto;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                o = new Objeto();
                o.setObjetoIdObjeto(resultado.getInt("ID_OBJETO"));
                o.setObjetoNombre(resultado.getString("NOMBRE"));
                o.setObjetoDescripcion(resultado.getString("DESCRIPCION"));
                o.setObjetoPrecio(resultado.getInt("PRECIO"));
                o.setObjetoUsos(resultado.getInt("USOS"));
                o.setObjetoFuerza(resultado.getInt("FUERZA"));
                o.setObjetoEfecto(resultado.getInt("EFECTO"));
            }

            conexion.close();
            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw exGD;
        }
        return o;
    }

    /**
     * Lee una habilidad determinada por su identificador
     *
     * @param idHabilidad Identificador de la habilidad que se va a leer
     * @return Objeto de tipo "Habilidad" con los datos que se han leido de la
     * base de datos
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public Habilidad leerHabilidad(Integer idHabilidad) throws ExcepcionGD {
        conectarBD();
        Habilidad habilidad = null;

        String dql = "select * from HABILIDAD where ID_HABILIDAD = " + idHabilidad;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                habilidad = new Habilidad();
                habilidad.setHabilidadId(resultado.getInt("ID_HABILIDAD"));
                habilidad.setHabilidadNombre(resultado.getString("NOMBRE"));
                habilidad.setHabilidadDescripcion(resultado.getString("DESCRIPCION"));
                habilidad.setHabilidadPrecio(resultado.getInt("PRECIO"));
                habilidad.setHabilidadFuerza(resultado.getInt("FUERZA"));
                habilidad.setHabilidadEfecto(resultado.getInt("EFECTO"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw exGD;
        }
        return habilidad;
    }

    /**
     * Lee un piso determinado por su identificador
     *
     * @param idPiso Identificador del piso que se va a leer
     * @return Objeto de tipo "Piso" con los datos que se han leido de la base
     * de datos
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public Piso leerPiso(Integer idPiso) throws ExcepcionGD {
        conectarBD();
        Piso piso = null;

        String dql = "select * from PISO where ID_PISO = " + idPiso;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                piso = new Piso();
                piso.setPisoId((Integer) resultado.getInt("ID_PISO"));
                piso.setPisoNumero((Integer) resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO"));

            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw exGD;
        }
        return piso;
    }

    /**
     * Lee un determinado objeto de un usuario en concreto, ambos buscados por
     * su id
     *
     * @param idUsuario Identificador del usuario al que pertenece el personaje
     * @param idObjeto Identificador del objeto que posee el per personaje
     * @return Objeto de tipo "Personajeobjeto" con los datos que se han leido
     * de la base de datos
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public PersonajeObjeto leerPersonajeObjeto(Integer idUsuario, Integer idObjeto) throws ExcepcionGD {
        conectarBD();
        PersonajeObjeto personajeObjeto = null;

        String dql = "SELECT "
                + "U.ID_USUARIO, U.NOMBRE AS NOMBRE_USUARIO, U.CORREO, U.CONTRASENA, "
                + "R.ID_ROL, R.NOMBRE AS NOMBRE_ROL, "
                + "P.NOMBRE AS NOMBRE_PERSONAJE, P.VIDA_MAXIMA, P.DANO_BASE, P.DEFENSA_BASE, P.DINERO, "
                + "A.ID_ARMA, A.NOMBRE AS NOMBRE_ARMA, A.DANO AS DANO_ARMA, A.PRECIO AS PRECIO_ARMA, "
                + "AR.ID_ARMADURA, AR.NOMBRE AS NOMBRE_ARMADURA, AR.DEFENSA AS DEFENSA_ARMADURA, AR.PRECIO AS PRECIO_ARMADURA, "
                + "PI.ID_PISO, PI.NUMERO_PISO, PI.FONDO AS FONDO_PISO, "
                + "O.ID_OBJETO, O.NOMBRE AS NOMBRE_OBJETO, O.DESCRIPCION AS DESCRIPCION_OBJETO, "
                + "O.PRECIO AS PRECIO_OBJETO, O.USOS, O.FUERZA, O.EFECTO, "
                + "PO.USOS_RESTANTES "
                + "FROM PERSONAJEOBJETO PO "
                + "JOIN PERSONAJE P ON PO.ID_USUARIO = P.ID_USUARIO "
                + "JOIN USUARIO U ON P.ID_USUARIO = U.ID_USUARIO "
                + "JOIN ROL R ON U.ID_ROL = R.ID_ROL "
                + "JOIN OBJETO O ON PO.ID_OBJETO = O.ID_OBJETO "
                + "LEFT JOIN ARMA A ON P.ID_ARMA = A.ID_ARMA "
                + "LEFT JOIN ARMADURA AR ON P.ID_ARMADURA = AR.ID_ARMADURA "
                + "JOIN PISO PI ON P.ID_PISO = PI.ID_PISO "
                + "WHERE PO.ID_USUARIO = " + idUsuario
                + " AND PO.ID_OBJETO = " + idObjeto;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                personajeObjeto = new PersonajeObjeto();

                // ROL
                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre(resultado.getString("NOMBRE_ROL"));

                // USUARIO
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(idUsuario);
                usuario.setUsuarioNombre(resultado.getString("NOMBRE_USUARIO"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));
                usuario.setUsuarioIdRol(rol);

                // PERSONAJE
                Personaje personaje = new Personaje();
                personaje.setPersonajeIdUsuario(usuario);
                personaje.setPersonajeNombre(resultado.getString("NOMBRE_PERSONAJE"));
                personaje.setPersonajeVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                personaje.setPersonajeDanoBase(resultado.getInt("DANO_BASE"));
                personaje.setPersonajeDefensaBase(resultado.getInt("DEFENSA_BASE"));
                personaje.setPersonajeDinero(resultado.getInt("DINERO"));

                // ARMA (opcional)
                if (resultado.getObject("ID_ARMA") != null) {
                    Arma arma = new Arma();
                    arma.setArmaId(resultado.getInt("ID_ARMA"));
                    arma.setArmaNombre(resultado.getString("NOMBRE_ARMA"));
                    arma.setArmaDano(resultado.getInt("DANO_ARMA"));
                    arma.setArmaPrecio(resultado.getInt("PRECIO_ARMA"));
                    personaje.setPersonajeArma(arma);
                }

                // ARMADURA (opcional)
                if (resultado.getObject("ID_ARMADURA") != null) {
                    Armadura armadura = new Armadura();
                    armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                    armadura.setArmaduraNombre(resultado.getString("NOMBRE_ARMADURA"));
                    armadura.setArmaduraDefensa(resultado.getInt("DEFENSA_ARMADURA"));
                    armadura.setArmaduraPrecio(resultado.getInt("PRECIO_ARMADURA"));
                    personaje.setPersonajeArmadura(armadura);
                }

                // PISO
                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO_PISO"));
                personaje.setPersonajePiso(piso);

                personajeObjeto.setPersonajeObjetoIdUsuario(personaje);

                // OBJETO (CORREGIDO)
                Objeto objeto = new Objeto();
                objeto.setObjetoIdObjeto(resultado.getInt("ID_OBJETO"));
                objeto.setObjetoNombre(resultado.getString("NOMBRE_OBJETO"));
                objeto.setObjetoDescripcion(resultado.getString("DESCRIPCION_OBJETO"));
                objeto.setObjetoPrecio(resultado.getInt("PRECIO_OBJETO"));
                objeto.setObjetoUsos(resultado.getInt("USOS"));
                objeto.setObjetoFuerza(resultado.getInt("FUERZA"));
                objeto.setObjetoEfecto(resultado.getInt("EFECTO"));

                personajeObjeto.setPersonajeObjetoIdObjeto(objeto);

                // USOS RESTANTES
                personajeObjeto.setPersonajeObjetoUsosRestantes(resultado.getInt("USOS_RESTANTES"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema.");
            throw exGD;
        }

        return personajeObjeto;
    }

    /**
     * Lee todos los objetos almacenados en la base de datos
     *
     * @return Lista de objetos con los datos leídos de la base de datos
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public ArrayList<Objeto> leerObjetos() throws ExcepcionGD {
        conectarBD();
        ArrayList<Objeto> objetos = new ArrayList<>();

        String dql = "select * from OBJETO";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                Objeto objeto = new Objeto();
                objeto.setObjetoIdObjeto(resultado.getInt("ID_OBJETO"));
                objeto.setObjetoNombre(resultado.getString("NOMBRE"));
                objeto.setObjetoDescripcion(resultado.getString("DESCRIPCION"));
                objeto.setObjetoPrecio(resultado.getInt("PRECIO"));
                objeto.setObjetoUsos(resultado.getInt("USOS"));
                objeto.setObjetoFuerza(resultado.getInt("FUERZA"));
                objeto.setObjetoEfecto(resultado.getInt("EFECTO"));

                objetos.add(objeto);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw exGD;
        }
        return objetos;
    }

    /**
     * Lee todos los objetos que posee el personaje asociado a un usuario y por
     * lo tanto a su personaje
     *
     * @param idUsuario Identificador del usuario propietario del personaje
     * @return Lista de objetos de tipo Personajeobjeto que representan los
     * objetos que posee el personaje
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public ArrayList<PersonajeObjeto> leerPersonajeObjetos(Integer idUsuario) throws ExcepcionGD {
        conectarBD();
        ArrayList<PersonajeObjeto> personajeObjetos = new ArrayList<>();

        String dql = "SELECT "
                + "U.ID_USUARIO, U.NOMBRE AS NOMBRE_USUARIO, U.CORREO, U.CONTRASENA, "
                + "R.ID_ROL, R.NOMBRE AS NOMBRE_ROL, "
                + "P.NOMBRE AS NOMBRE_PERSONAJE, P.VIDA_MAXIMA, P.DANO_BASE, P.DEFENSA_BASE, P.DINERO, "
                + "A.ID_ARMA, A.NOMBRE AS NOMBRE_ARMA, A.DANO AS DANO_ARMA, A.PRECIO AS PRECIO_ARMA, "
                + "AR.ID_ARMADURA, AR.NOMBRE AS NOMBRE_ARMADURA, AR.DEFENSA AS DEFENSA_ARMADURA, AR.PRECIO AS PRECIO_ARMADURA, "
                + "PI.ID_PISO, PI.NUMERO_PISO, PI.FONDO AS FONDO_PISO, "
                + "O.ID_OBJETO, O.NOMBRE AS NOMBRE_OBJETO, O.DESCRIPCION AS DESCRIPCION_OBJETO, "
                + "O.PRECIO AS PRECIO_OBJETO, O.USOS, O.FUERZA, O.EFECTO, "
                + "PO.USOS_RESTANTES "
                + "FROM PERSONAJEOBJETO PO "
                + "JOIN PERSONAJE P ON PO.ID_USUARIO = P.ID_USUARIO "
                + "JOIN USUARIO U ON P.ID_USUARIO = U.ID_USUARIO "
                + "JOIN ROL R ON U.ID_ROL = R.ID_ROL "
                + "JOIN OBJETO O ON PO.ID_OBJETO = O.ID_OBJETO "
                + "LEFT JOIN ARMA A ON P.ID_ARMA = A.ID_ARMA "
                + "LEFT JOIN ARMADURA AR ON P.ID_ARMADURA = AR.ID_ARMADURA "
                + "JOIN PISO PI ON P.ID_PISO = PI.ID_PISO "
                + "WHERE PO.ID_USUARIO = " + idUsuario;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {

                PersonajeObjeto personajeObjeto = new PersonajeObjeto();

                // ROL
                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre(resultado.getString("NOMBRE_ROL"));

                // USUARIO
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(idUsuario);
                usuario.setUsuarioNombre(resultado.getString("NOMBRE_USUARIO"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));
                usuario.setUsuarioIdRol(rol);

                // PERSONAJE
                Personaje personaje = new Personaje();
                personaje.setPersonajeIdUsuario(usuario);
                personaje.setPersonajeNombre(resultado.getString("NOMBRE_PERSONAJE"));
                personaje.setPersonajeVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                personaje.setPersonajeDanoBase(resultado.getInt("DANO_BASE"));
                personaje.setPersonajeDefensaBase(resultado.getInt("DEFENSA_BASE"));
                personaje.setPersonajeDinero(resultado.getInt("DINERO"));

                // ARMA (opcional)
                if (resultado.getObject("ID_ARMA") != null) {
                    Arma arma = new Arma();
                    arma.setArmaId(resultado.getInt("ID_ARMA"));
                    arma.setArmaNombre(resultado.getString("NOMBRE_ARMA"));
                    arma.setArmaDano(resultado.getInt("DANO_ARMA"));
                    arma.setArmaPrecio(resultado.getInt("PRECIO_ARMA"));
                    personaje.setPersonajeArma(arma);
                }

                // ARMADURA (opcional)
                if (resultado.getObject("ID_ARMADURA") != null) {
                    Armadura armadura = new Armadura();
                    armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                    armadura.setArmaduraNombre(resultado.getString("NOMBRE_ARMADURA"));
                    armadura.setArmaduraDefensa(resultado.getInt("DEFENSA_ARMADURA"));
                    armadura.setArmaduraPrecio(resultado.getInt("PRECIO_ARMADURA"));
                    personaje.setPersonajeArmadura(armadura);
                }

                // PISO
                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO_PISO"));
                personaje.setPersonajePiso(piso);

                personajeObjeto.setPersonajeObjetoIdUsuario(personaje);

                // OBJETO
                Objeto objeto = new Objeto();
                objeto.setObjetoIdObjeto(resultado.getInt("ID_OBJETO"));
                objeto.setObjetoNombre(resultado.getString("NOMBRE_OBJETO"));
                objeto.setObjetoDescripcion(resultado.getString("DESCRIPCION_OBJETO"));
                objeto.setObjetoPrecio(resultado.getInt("PRECIO_OBJETO"));
                objeto.setObjetoUsos(resultado.getInt("USOS"));
                objeto.setObjetoFuerza(resultado.getInt("FUERZA"));
                objeto.setObjetoEfecto(resultado.getInt("EFECTO"));

                personajeObjeto.setPersonajeObjetoIdObjeto(objeto);

                // USOS RESTANTES
                personajeObjeto.setPersonajeObjetoUsosRestantes(resultado.getInt("USOS_RESTANTES"));

                personajeObjetos.add(personajeObjeto);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema.");
            throw exGD;
        }

        return personajeObjetos;
    }

    /**
     * Lee todos los enemigos registrados en la base de datos
     *
     * @return Lista de objetos de tipo "Enemigo" con los datos que se han leido
     * de la base de datos
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public ArrayList<Enemigo> leerEnemigos() throws ExcepcionGD {
        conectarBD();
        Enemigo enemigo = null;
        ArrayList<Enemigo> enemigos = new ArrayList<>();

        String dql = "select * from ENEMIGO";

        try {

            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {

                enemigo = new Enemigo();

                enemigo.setEnemigoIdEnemigo((Integer) resultado.getInt("ID_ENEMIGO"));
                enemigo.setEnemigoNombre(resultado.getString("NOMBRE"));
                enemigo.setEnemigoVidaMaxima((Integer) resultado.getInt("VIDA_MAXIMA"));
                enemigo.setEnemigoDanoBase((Integer) resultado.getInt("DANO_BASE"));
                enemigo.setEnemigoDefensaBase((Integer) resultado.getInt("DEFENSA_BASE"));
                enemigo.setEnemigoDinero((Integer) resultado.getInt("DINERO"));

                enemigos.add(enemigo);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw exGD;
        }

        return enemigos;
    }

    /**
     * Lee un único personaje de la base de datos a partir del identificador de
     * usuario.
     *
     * El método recupera toda la información asociada al personaje, incluyendo
     * los datos del usuario, su rol, el arma equipada y la armadura equipada,
     * si existen, mediante las relaciones entre las tablas PERSONAJE, USUARIO,
     * ROL, ARMA y ARMADURA.
     *
     * @param idUsuario que se corresponde con el identificador del usuario cuyo
     * personaje se desea consultar
     * @return Un objeto Personaje con todos los datos del personaje y sus
     * relaciones asociadas, o null si no existe ningún personaje con ese
     * identificador
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Kevin
     */
    public Personaje leerPersonaje(Integer idUsuario) throws ExcepcionGD {
        conectarBD();
        Personaje personaje = null;

        String dql = "SELECT PERSONAJE.*, "
                + "USUARIO.NOMBRE AS NOMBRE_USUARIO, USUARIO.CORREO, USUARIO.CONTRASENA, USUARIO.ID_ROL, "
                + "ROL.NOMBRE AS NOMBRE_ROL, "
                + "ARMA.ID_ARMA, ARMA.NOMBRE AS NOMBRE_ARMA, ARMA.DANO, ARMA.PRECIO AS PRECIO_ARMA, "
                + "ARMADURA.ID_ARMADURA, ARMADURA.NOMBRE AS NOMBRE_ARMADURA, ARMADURA.DEFENSA, ARMADURA.PRECIO AS PRECIO_ARMADURA, "
                + "PISO.ID_PISO, PISO.NUMERO_PISO AS NUMERO_PISO, PISO.FONDO AS FONDO_PISO "
                + "FROM PERSONAJE "
                + "JOIN USUARIO ON PERSONAJE.ID_USUARIO = USUARIO.ID_USUARIO "
                + "JOIN ROL ON USUARIO.ID_ROL = ROL.ID_ROL "
                + "LEFT JOIN ARMA ON PERSONAJE.ID_ARMA = ARMA.ID_ARMA "
                + "LEFT JOIN ARMADURA ON PERSONAJE.ID_ARMADURA = ARMADURA.ID_ARMADURA "
                + "JOIN PISO ON PERSONAJE.ID_PISO = PISO.ID_PISO "
                + "WHERE PERSONAJE.ID_USUARIO = " + idUsuario;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {

                personaje = new Personaje();

                // ROL
                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre(resultado.getString("NOMBRE_ROL"));

                // USUARIO
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(idUsuario);
                usuario.setUsuarioNombre(resultado.getString("NOMBRE_USUARIO"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));
                usuario.setUsuarioIdRol(rol);

                personaje.setPersonajeIdUsuario(usuario);

                // PERSONAJE
                personaje.setPersonajeNombre(resultado.getString("NOMBRE"));
                personaje.setPersonajeVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                personaje.setPersonajeDanoBase(resultado.getInt("DANO_BASE"));
                personaje.setPersonajeDefensaBase(resultado.getInt("DEFENSA_BASE"));
                personaje.setPersonajeDinero(resultado.getInt("DINERO"));

                // ARMA
                if (resultado.getObject("ID_ARMA") != null) {
                    Arma arma = new Arma();
                    arma.setArmaId(resultado.getInt("ID_ARMA"));
                    arma.setArmaNombre(resultado.getString("NOMBRE_ARMA"));
                    arma.setArmaDano(resultado.getInt("DANO"));
                    arma.setArmaPrecio(resultado.getInt("PRECIO_ARMA"));
                    personaje.setPersonajeArma(arma);
                }

                // ARMADURA
                if (resultado.getObject("ID_ARMADURA") != null) {
                    Armadura armadura = new Armadura();
                    armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                    armadura.setArmaduraNombre(resultado.getString("NOMBRE_ARMADURA"));
                    armadura.setArmaduraDefensa(resultado.getInt("DEFENSA"));
                    armadura.setArmaduraPrecio(resultado.getInt("PRECIO_ARMADURA"));
                    personaje.setPersonajeArmadura(armadura);
                }

                // PISO (último piso)
                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO_PISO"));
                personaje.setPersonajePiso(piso);

            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return personaje;
    }

    /**
     * Obtiene un único registro de la tabla ENEMIGO a partir de su
     * identificador.
     *
     * @param idEnemigo Identificador del enemigo que se desea consultar
     * @return Un objeto Enemigo con los datos obtenidos de la base de datos, o
     * null si no existe
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón
     */
    public Enemigo leerEnemigo(Integer idEnemigo) throws ExcepcionGD {
        conectarBD();
        Enemigo enemigo = null;

        String dml = "select * from ENEMIGO where ID_ENEMIGO = " + idEnemigo;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dml);

            if (resultado.next()) {
                enemigo = new Enemigo();
                enemigo.setEnemigoIdEnemigo(resultado.getInt("ID_ENEMIGO"));
                enemigo.setEnemigoNombre(resultado.getString("NOMBRE"));
                enemigo.setEnemigoVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                enemigo.setEnemigoDanoBase(resultado.getInt("DANO_BASE"));
                enemigo.setEnemigoDefensaBase(resultado.getInt("DEFENSA_BASE"));
                enemigo.setEnemigoDinero(resultado.getInt("DINERO"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exHR = new ExcepcionGD();
            exHR.setCodigoErrorBD(ex.getErrorCode());
            exHR.setMensajeErrorBD(ex.getMessage());
            exHR.setSentenciaSQL(dml);
            exHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw exHR;
        }

        return enemigo;
    }

    /**
     * Este método leerá una sola arma de la tabla "Arma" de la base de datos.
     *
     * @param idArma Identificador del arma que queremos buscar en la tabla
     * "Arma"
     * @return Devuelve un objeto de tipo "Arma"
     * @throws ExcepcionGD Se lanzará cuando ocurre una excepción SQL
     * @author Kevin
     */
    public Arma leerArma(Integer idArma) throws ExcepcionGD {
        conectarBD();
        Arma arma = null;
        String dql = "select * from ARMA where ID_ARMA = " + idArma;
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            if (resultado.next()) {
                arma = new Arma();
                arma.setArmaId((Integer) resultado.getInt("ID_ARMA"));
                arma.setArmaNombre(resultado.getString("NOMBRE"));
                arma.setArmaDano((Integer) resultado.getInt("DANO"));
                arma.setArmaPrecio((Integer) resultado.getInt("PRECIO"));
            }
            conexion.close();
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw exGD;
        }
        return arma;
    }

    /**
     * Este método leerá todas las armas de la tabla "Arma"
     *
     * @return Devuelve un ArrayList con objetos de tipo "Arma"
     * @throws ExcepcionGD Se lanzará cuando ocurre una excepción SQL
     * @author Iván
     */
    public ArrayList<Arma> leerArmas() throws ExcepcionGD {
        conectarBD();
        ArrayList<Arma> armas = new ArrayList<>();
        String dql = "select * from ARMA";
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                Arma arma = new Arma();
                arma.setArmaId((Integer) resultado.getInt("ID_ARMA"));
                arma.setArmaNombre(resultado.getString("NOMBRE"));
                arma.setArmaDano((Integer) resultado.getInt("DANO"));
                arma.setArmaPrecio((Integer) resultado.getInt("PRECIO"));

                armas.add(arma);
            }
            conexion.close();
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw exGD;
        }
        return armas;
    }

    /**
     * Este método
     *
     * @param idUsuario Identificador del usuario que tiene la habilidad
     * @param idHabilidad Identificador de la habilidad que tiene el usuario
     * @return Devuelve un objeto de tipo Personajehabilidad con los datos que
     * haya encontrado de la sentencia DQL
     * @throws ExcepcionGD ExcepcionGD Se lanzará cuando ocurre una excepción
     * SQL
     * @author Iván
     */
    public PersonajeHabilidad leerPersonajeHabilidad(Integer idUsuario, Integer idHabilidad) throws ExcepcionGD {
        conectarBD();
        PersonajeHabilidad personajeHabilidad = null;

        String dql = "SELECT PH.*, "
                + "H.NOMBRE AS NOMBRE_HABILIDAD, H.DESCRIPCION AS DESCRIPCION_HABILIDAD, H.PRECIO AS PRECIO_HABILIDAD, H.FUERZA, H.EFECTO, "
                + "P.NOMBRE AS NOMBRE_PERSONAJE, P.VIDA_MAXIMA, P.DANO_BASE, P.DEFENSA_BASE, P.DINERO, "
                + "U.NOMBRE AS NOMBRE_USUARIO, U.CORREO, U.CONTRASENA, U.ID_ROL, "
                + "R.NOMBRE AS NOMBRE_ROL, "
                + "A.ID_ARMA, A.NOMBRE AS NOMBRE_ARMA, A.DANO, A.PRECIO AS PRECIO_ARMA, "
                + "AR.ID_ARMADURA, AR.NOMBRE AS NOMBRE_ARMADURA, AR.DEFENSA, AR.PRECIO AS PRECIO_ARMADURA, "
                + "PI.ID_PISO, PI.NUMERO_PISO, PI.FONDO "
                + "FROM PERSONAJEHABILIDAD PH "
                + "JOIN HABILIDAD H ON PH.ID_HABILIDAD = H.ID_HABILIDAD "
                + "JOIN PERSONAJE P ON PH.ID_USUARIO = P.ID_USUARIO "
                + "JOIN USUARIO U ON P.ID_USUARIO = U.ID_USUARIO "
                + "JOIN ROL R ON U.ID_ROL = R.ID_ROL "
                + "LEFT JOIN ARMA A ON P.ID_ARMA = A.ID_ARMA "
                + "LEFT JOIN ARMADURA AR ON P.ID_ARMADURA = AR.ID_ARMADURA "
                + "JOIN PISO PI ON P.ID_PISO = PI.ID_PISO "
                + "WHERE PH.ID_USUARIO = " + idUsuario
                + " AND PH.ID_HABILIDAD = " + idHabilidad;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            if (resultado.next()) {
                personajeHabilidad = new PersonajeHabilidad();

                // ROL
                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre(resultado.getString("NOMBRE_ROL"));

                // USUARIO
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(idUsuario);
                usuario.setUsuarioNombre(resultado.getString("NOMBRE_USUARIO"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));
                usuario.setUsuarioIdRol(rol);

                // PERSONAJE
                Personaje personaje = new Personaje();
                personaje.setPersonajeIdUsuario(usuario);
                personaje.setPersonajeNombre(resultado.getString("NOMBRE_PERSONAJE"));
                personaje.setPersonajeVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                personaje.setPersonajeDanoBase(resultado.getInt("DANO_BASE"));
                personaje.setPersonajeDefensaBase(resultado.getInt("DEFENSA_BASE"));
                personaje.setPersonajeDinero(resultado.getInt("DINERO"));

                // ARMA
                if (resultado.getObject("ID_ARMA") != null) {
                    Arma arma = new Arma();
                    arma.setArmaId(resultado.getInt("ID_ARMA"));
                    arma.setArmaNombre(resultado.getString("NOMBRE_ARMA"));
                    arma.setArmaDano(resultado.getInt("DANO"));
                    arma.setArmaPrecio(resultado.getInt("PRECIO_ARMA"));
                    personaje.setPersonajeArma(arma);
                }

                // ARMADURA
                if (resultado.getObject("ID_ARMADURA") != null) {
                    Armadura armadura = new Armadura();
                    armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                    armadura.setArmaduraNombre(resultado.getString("NOMBRE_ARMADURA"));
                    armadura.setArmaduraDefensa(resultado.getInt("DEFENSA"));
                    armadura.setArmaduraPrecio(resultado.getInt("PRECIO_ARMADURA"));
                    personaje.setPersonajeArmadura(armadura);
                }

                // PISO
                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO"));
                personaje.setPersonajePiso(piso);

                // HABILIDAD (CORREGIDO)
                Habilidad habilidad = new Habilidad();
                habilidad.setHabilidadId(resultado.getInt("ID_HABILIDAD"));
                habilidad.setHabilidadNombre(resultado.getString("NOMBRE_HABILIDAD"));
                habilidad.setHabilidadDescripcion(resultado.getString("DESCRIPCION_HABILIDAD"));
                habilidad.setHabilidadPrecio(resultado.getInt("PRECIO_HABILIDAD"));
                habilidad.setHabilidadFuerza(resultado.getInt("FUERZA"));
                habilidad.setHabilidadEfecto(resultado.getInt("EFECTO"));

                // PERSONAJEHABILIDAD
                personajeHabilidad.setPersonajeHabilidadIdUsuario(personaje);
                personajeHabilidad.setPersonajeHabilidadIdHabilidad(habilidad);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema.");
            throw e;
        }

        return personajeHabilidad;
    }

    /**
     * Lee todas las habilidades de un usuario
     *
     * @param idUsuario Identificador del usuario
     * @return Devuelve un ArrayList con objetos de tipo "Personajehabilidad"
     * @throws ExcepcionGD ExcepcionGD Se lanzará cuando ocurre una excepción
     * SQL
     * @author Iván
     */
    public ArrayList<PersonajeHabilidad> leerPersonajeHabilidades(Integer idUsuario) throws ExcepcionGD {
        conectarBD();
        ArrayList<PersonajeHabilidad> personajeHabilidades = new ArrayList<>();

        String dql = "SELECT PH.*, "
                + "H.NOMBRE AS NOMBRE_HABILIDAD, H.DESCRIPCION AS DESCRIPCION_HABILIDAD, H.PRECIO AS PRECIO_HABILIDAD, H.FUERZA, H.EFECTO, "
                + "P.NOMBRE AS NOMBRE_PERSONAJE, P.VIDA_MAXIMA, P.DANO_BASE, P.DEFENSA_BASE, P.DINERO, "
                + "U.NOMBRE AS NOMBRE_USUARIO, U.CORREO, U.CONTRASENA, U.ID_ROL, "
                + "R.NOMBRE AS NOMBRE_ROL, "
                + "A.ID_ARMA, A.NOMBRE AS NOMBRE_ARMA, A.DANO, A.PRECIO AS PRECIO_ARMA, "
                + "AR.ID_ARMADURA, AR.NOMBRE AS NOMBRE_ARMADURA, AR.DEFENSA, AR.PRECIO AS PRECIO_ARMADURA, "
                + "PI.ID_PISO, PI.NUMERO_PISO, PI.FONDO "
                + "FROM PERSONAJEHABILIDAD PH "
                + "JOIN HABILIDAD H ON PH.ID_HABILIDAD = H.ID_HABILIDAD "
                + "JOIN PERSONAJE P ON PH.ID_USUARIO = P.ID_USUARIO "
                + "JOIN USUARIO U ON P.ID_USUARIO = U.ID_USUARIO "
                + "JOIN ROL R ON U.ID_ROL = R.ID_ROL "
                + "LEFT JOIN ARMA A ON P.ID_ARMA = A.ID_ARMA "
                + "LEFT JOIN ARMADURA AR ON P.ID_ARMADURA = AR.ID_ARMADURA "
                + "JOIN PISO PI ON P.ID_PISO = PI.ID_PISO "
                + "WHERE PH.ID_USUARIO = " + idUsuario;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                PersonajeHabilidad personajeHabilidad = new PersonajeHabilidad();

                // ROL
                Rol rol = new Rol();
                rol.setRolIdRol(resultado.getInt("ID_ROL"));
                rol.setRolNombre(resultado.getString("NOMBRE_ROL"));

                // USUARIO
                Usuario usuario = new Usuario();
                usuario.setUsuarioIdUsuario(idUsuario);
                usuario.setUsuarioNombre(resultado.getString("NOMBRE_USUARIO"));
                usuario.setUsuarioCorreo(resultado.getString("CORREO"));
                usuario.setUsuarioContrasena(resultado.getString("CONTRASENA"));
                usuario.setUsuarioIdRol(rol);

                // PERSONAJE
                Personaje personaje = new Personaje();
                personaje.setPersonajeIdUsuario(usuario);
                personaje.setPersonajeNombre(resultado.getString("NOMBRE_PERSONAJE"));
                personaje.setPersonajeVidaMaxima(resultado.getInt("VIDA_MAXIMA"));
                personaje.setPersonajeDanoBase(resultado.getInt("DANO_BASE"));
                personaje.setPersonajeDefensaBase(resultado.getInt("DEFENSA_BASE"));
                personaje.setPersonajeDinero(resultado.getInt("DINERO"));

                // ARMA
                if (resultado.getObject("ID_ARMA") != null) {
                    Arma arma = new Arma();
                    arma.setArmaId(resultado.getInt("ID_ARMA"));
                    arma.setArmaNombre(resultado.getString("NOMBRE_ARMA"));
                    arma.setArmaDano(resultado.getInt("DANO"));
                    arma.setArmaPrecio(resultado.getInt("PRECIO_ARMA"));
                    personaje.setPersonajeArma(arma);
                }

                // ARMADURA
                if (resultado.getObject("ID_ARMADURA") != null) {
                    Armadura armadura = new Armadura();
                    armadura.setArmaduraId(resultado.getInt("ID_ARMADURA"));
                    armadura.setArmaduraNombre(resultado.getString("NOMBRE_ARMADURA"));
                    armadura.setArmaduraDefensa(resultado.getInt("DEFENSA"));
                    armadura.setArmaduraPrecio(resultado.getInt("PRECIO_ARMADURA"));
                    personaje.setPersonajeArmadura(armadura);
                }

                // PISO
                Piso piso = new Piso();
                piso.setPisoId(resultado.getInt("ID_PISO"));
                piso.setPisoNumero(resultado.getInt("NUMERO_PISO"));
                piso.setPisoFondo(resultado.getString("FONDO"));
                personaje.setPersonajePiso(piso);

                // HABILIDAD (CORREGIDO)
                Habilidad habilidad = new Habilidad();
                habilidad.setHabilidadId(resultado.getInt("ID_HABILIDAD"));
                habilidad.setHabilidadNombre(resultado.getString("NOMBRE_HABILIDAD"));
                habilidad.setHabilidadDescripcion(resultado.getString("DESCRIPCION_HABILIDAD"));
                habilidad.setHabilidadPrecio(resultado.getInt("PRECIO_HABILIDAD"));
                habilidad.setHabilidadFuerza(resultado.getInt("FUERZA"));
                habilidad.setHabilidadEfecto(resultado.getInt("EFECTO"));

                // PERSONAJEHABILIDAD
                personajeHabilidad.setPersonajeHabilidadIdUsuario(personaje);
                personajeHabilidad.setPersonajeHabilidadIdHabilidad(habilidad);
                
                personajeHabilidades.add(personajeHabilidad);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();
            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw e;
        }
        return personajeHabilidades;
    }

    public Configuracion leerConfiguracion() throws ExcepcionGD {
        conectarBD();
        Configuracion configuracion = null;
        String dql = "select * from CONFIGURACION";
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            if (resultado.next()) {
                configuracion = new Configuracion();
                configuracion.setConfiguracionVidaMaximaPredeterminado((Integer) resultado.getInt("VIDA_MAXIMA_PREDETERMINADO"));
                configuracion.setConfiguracionDanoBasePredeterminado((Integer) resultado.getInt("DANO_BASE_PREDETERMINADO"));
                configuracion.setConfiguracionDefensaBasePredeterminado((Integer) resultado.getInt("DEFENSA_BASE_PREDETERMINADO"));
            }
            conexion.close();
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dql);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw exGD;
        }
        return configuracion;
    }

    //INSERTAR------------------------------------------------------------------
    /**
     * Inserta un nuevo registro en la tabla PERSONAJEOBJETO
     *
     * @param personajeObjeto Un objeto personajeObjeto con los datos a insertar
     * @return Un integer "registrosAfectados" con los registros que se han
     * insertado
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public boolean insertarPersonajeObjeto(PersonajeObjeto personajeObjeto) throws ExcepcionGD {
        conectarBD();
        String dml = "insert into PERSONAJEOBJETO (ID_USUARIO, ID_OBJETO, USOS_RESTANTES) values (?,?,?)";
        int registrosAfectados = 0;
        try {
            PreparedStatement sentencia = conexion.prepareStatement(dml);

            sentencia.setObject(1, personajeObjeto.getPersonajeObjetoIdUsuario().getPersonajeIdUsuario().getUsuarioIdUsuario(), java.sql.Types.INTEGER);
            sentencia.setObject(2, personajeObjeto.getPersonajeObjetoIdObjeto().getObjetoIdObjeto(), java.sql.Types.INTEGER);
            sentencia.setObject(3, personajeObjeto.getPersonajeObjetoUsosRestantes(), java.sql.Types.INTEGER);

            registrosAfectados = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {
                case 2290:
                    exGD.setMensajeErrorUsuario("Los usos restantes no pueden ser menores que 0.");
                    break;
                case 2291:
                    exGD.setMensajeErrorUsuario("El objeto o el usuario seleccionados no existen.");
                    break;
                case 1400:
                    exGD.setMensajeErrorUsuario("Todos los campos son obligatorios.");
                    break;
                case 1:
                    exGD.setMensajeErrorUsuario("El personaje que corresponde a este usuario ya tiene vinculado el mismo objeto, no se permite tener los objetos duplicados.");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw exGD;
        }
        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Inserta un único registro en la tabla PERSONAJE.
     *
     * @param personaje Un objeto que contiene los datos del personaje que se
     * desea insertar
     * @return Un integer con los registros que se han insertado correctamente
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón
     */
    public boolean insertarPersonaje(Personaje personaje) throws ExcepcionGD {
        conectarBD();
        int registrosAfectados = 0;

        String dml = "insert into PERSONAJE "
                + "(ID_USUARIO, NOMBRE, VIDA_MAXIMA, DANO_BASE, DEFENSA_BASE, DINERO, ID_ARMA, ID_ARMADURA, ID_PISO) "
                + "values (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement sentencia = conexion.prepareStatement(dml);

            sentencia.setObject(1, personaje.getPersonajeIdUsuario().getUsuarioIdUsuario(), java.sql.Types.INTEGER);
            sentencia.setObject(2, personaje.getPersonajeNombre(), java.sql.Types.VARCHAR);
            sentencia.setObject(3, personaje.getPersonajeVidaMaxima(), java.sql.Types.INTEGER);
            sentencia.setObject(4, personaje.getPersonajeDanoBase(), java.sql.Types.INTEGER);
            sentencia.setObject(5, personaje.getPersonajeDefensaBase(), java.sql.Types.INTEGER);
            sentencia.setObject(6, personaje.getPersonajeDinero(), java.sql.Types.INTEGER);
            sentencia.setObject(7, personaje.getPersonajeArma().getArmaId(), java.sql.Types.INTEGER);
            sentencia.setObject(8, personaje.getPersonajeArmadura().getArmaduraId(), java.sql.Types.INTEGER);
            sentencia.setObject(9, personaje.getPersonajePiso().getPisoId(), java.sql.Types.INTEGER);

            registrosAfectados = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD e = new ExcepcionGD();

            e.setCodigoErrorBD(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {
                case 1:
                    e.setMensajeErrorUsuario("Este usuario ya tiene un personaje");
                    break;
                case 2290:
                    e.setMensajeErrorUsuario("La vida máxima, el daño, la defensa y el dinero no pueden ser negativos.");
                    break;
                case 2291:
                    e.setMensajeErrorUsuario("El usuario, el arma, la armadura o el piso no existen.");
                    break;
                case 1400:
                    e.setMensajeErrorUsuario("Todos los campos son obligatorios excepto el arma y la armadura.");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }

            throw e;

        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Inserta un nuevo registro en la tabla Personajehabilidad de GodsBattle
     *
     * @param personajeHabilidad PersonajeHabilidad del que se obtendrá qué
     * habilidad pertenece a qué usuario
     * @return Devuelvo un Integer con la cantidad de registros añadidos
     * @throws ExcepcionGD Se lanzará cuando ocurre una expcepción SQL
     * @author Iván
     */
    public boolean insertarPersonajeHabilidad(PersonajeHabilidad personajeHabilidad) throws ExcepcionGD {
        conectarBD();
        String dml = "insert into PERSONAJEHABILIDAD (ID_USUARIO, ID_HABILIDAD) values (?, ?)";

        int registrosAfectados = 0;

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setObject(1, personajeHabilidad.getPersonajeHabilidadIdUsuario().getPersonajeIdUsuario().getUsuarioIdUsuario(), java.sql.Types.INTEGER);
            sentenciaPreparada.setObject(2, personajeHabilidad.getPersonajeHabilidadIdHabilidad().getHabilidadId(), java.sql.Types.INTEGER);
            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    exGD.setMensajeErrorUsuario("El personaje que corresponde a este usuario o la habilidad no existen");
                    break;
                case 1400:
                    exGD.setMensajeErrorUsuario("El personaje que corresponde a este usuario o la habilidad no se han definido.");
                    break;
                case 1:
                    exGD.setMensajeErrorUsuario("El personaje que corresponde a este usuario ya tiene vinculada esta habilidad. No se permiten tener las habilidades duplicadas.");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el admiinistrador.");
            }
            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Inserta un único registro en la tabla USUARIO.
     *
     * @param usuario Un objeto que contiene los datos del usuario que se desea
     * insertar
     * @return Un integer con los registros que se han insertado correctamente
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Kevin
     */
    public boolean insertarUsuario(Usuario usuario) throws ExcepcionGD {
        conectarBD();
        int registrosAfectados = 0;

        String dml = "insert into USUARIO (ID_USUARIO, NOMBRE, CORREO, CONTRASENA, ID_ROL) "
                + "values (SECUENCIA_USUARIO.nextval,?,?,?,?)";

        try {
            PreparedStatement sentencia = conexion.prepareStatement(dml);

            sentencia.setString(1, usuario.getUsuarioNombre());
            sentencia.setString(2, usuario.getUsuarioCorreo());
            sentencia.setString(3, usuario.getUsuarioContrasena());
            sentencia.setObject(4, usuario.getUsuarioIdRol().getRolIdRol(), java.sql.Types.INTEGER);

            registrosAfectados = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {
                case 1:
                    exGD.setMensajeErrorUsuario("El correo ya está registrado en el sistema.");
                    break;
                case 2291:
                    exGD.setMensajeErrorUsuario("El rol asignado no existe.");
                    break;
                case 1400:
                    exGD.setMensajeErrorUsuario("Todos los campos son obligatorios.");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    //MODIFICAR------------------------------------------------------------------
    /**
     * Modifica un único registro de la tabla USUARIO a partir de su
     * identificador.
     *
     * @param idUsuario Identificador del usuario que se desea modificar
     * @param usuario Un objeto que contiene los nuevos datos del usuario
     * @return Un integer con los registros que se han modificado correctamente
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón
     */
    public boolean modificarUsuario(Integer idUsuario, Usuario usuario) throws ExcepcionGD {
        conectarBD();
        int registrosAfectados = 0;
        String dml = "{ call MODIFICAR_USUARIO(?,?,?,?,?,?) }";

        try {
            CallableStatement sentencia = conexion.prepareCall(dml);

            sentencia.setObject(1, idUsuario, java.sql.Types.INTEGER);
            sentencia.setString(2, usuario.getUsuarioNombre());
            sentencia.setString(3, usuario.getUsuarioCorreo());
            sentencia.setString(4, usuario.getUsuarioContrasena());
            sentencia.setObject(5, usuario.getUsuarioIdRol().getRolIdRol(), java.sql.Types.INTEGER);

            sentencia.registerOutParameter(6, java.sql.Types.INTEGER);
            sentencia.executeUpdate();
            registrosAfectados = sentencia.getInt(6);

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 1:
                    exGD.setMensajeErrorUsuario("El correo ya está registrado en el sistema.");
                    break;
                case 2291:
                    exGD.setMensajeErrorUsuario("El rol asignado no existe.");
                    break;
                case 1407:
                    exGD.setMensajeErrorUsuario("Todos los campos son obligatorios.");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Modifica un unico personaje de la tabla PERSONAJE
     *
     * @param idUsuario Identificador del usuario el cual identifica el usuario
     * a modificar.
     * @param personaje Un objeto personaje con las modificaciones a realizar
     * @return Un integer "registrosAfectados" con los registros que se han
     * modificado
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public boolean modificarPersonaje(Integer idUsuario, Personaje personaje) throws ExcepcionGD {
        conectarBD();
        int registrosAfectados = 0;

        String procedimiento = "{ call MODIFICAR_PERSONAJE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try {
            CallableStatement sentencia = conexion.prepareCall(procedimiento);

            sentencia.setObject(1, idUsuario, java.sql.Types.INTEGER);
            sentencia.setString(2, personaje.getPersonajeNombre());
            sentencia.setObject(3, personaje.getPersonajeVidaMaxima(), java.sql.Types.INTEGER);
            sentencia.setObject(4, personaje.getPersonajeDanoBase(), java.sql.Types.INTEGER);
            sentencia.setObject(5, personaje.getPersonajeDefensaBase(), java.sql.Types.INTEGER);
            sentencia.setObject(6, personaje.getPersonajeDinero(), java.sql.Types.INTEGER);

            if (personaje.getPersonajeArma() != null) {
                sentencia.setObject(7, personaje.getPersonajeArma().getArmaId(), java.sql.Types.INTEGER);
            } else {
                sentencia.setNull(7, java.sql.Types.INTEGER);
            }
            if (personaje.getPersonajeArmadura() != null) {
                sentencia.setObject(8, personaje.getPersonajeArmadura().getArmaduraId(), java.sql.Types.INTEGER);
            } else {
                sentencia.setNull(8, java.sql.Types.INTEGER);
            }
            sentencia.setObject(9, personaje.getPersonajePiso().getPisoId(), java.sql.Types.INTEGER);

            sentencia.registerOutParameter(10, java.sql.Types.INTEGER);
            sentencia.executeUpdate();
            registrosAfectados = sentencia.getInt(10);
            conexion.close();

        } catch (SQLException ex) {

            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(procedimiento);

            switch (ex.getErrorCode()) {
                case 2290:
                    exGD.setMensajeErrorUsuario("La vida maxima, el daño base, la defensa base y el dinero no pueden ser negativos.");
                    break;
                case 2291:
                    exGD.setMensajeErrorUsuario("El usuario, el arma, la armadura o el piso no existen.");
                    break;
                case 1407:
                    exGD.setMensajeErrorUsuario("Todos los campos son obligatorios excepto el arma y la armadura.");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el admiinistrador.");
            }
            throw exGD;
        }
        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Actualiza los usos restantes de un objeto en la tabla Personajeobjeto de
     * GodsBattle
     *
     * @param idUsuario Integer que indicará el personaje del usuario que se
     * actualizará
     * @param idObjeto Integer que indicará el objeto
     * @param personajeObjeto Una instancia de Personajeobjeto de la que se
     * conseguirá la cantidad de usos restantes del objeto
     * @return Cantidad de registros afectados
     * @throws ExcepcionGD Se lanzará cuando se produzca una excepción SQL
     * @author Iván
     */
    public boolean modificarPersonajeObjeto(Integer idUsuario, Integer idObjeto, PersonajeObjeto personajeObjeto) throws ExcepcionGD {
        conectarBD();
        String procedimiento = "{ call MODIFICAR_PERSONAJEOBJETO(?, ?, ?, ?) }";
        int registrosAfectados = 0;
        try {
            CallableStatement sentencia = conexion.prepareCall(procedimiento);
            sentencia.setObject(1, idUsuario, java.sql.Types.INTEGER);
            sentencia.setObject(2, idObjeto, java.sql.Types.INTEGER);
            sentencia.setObject(3, personajeObjeto.getPersonajeObjetoUsosRestantes(), java.sql.Types.INTEGER);
            sentencia.registerOutParameter(4, java.sql.Types.INTEGER);

            sentencia.execute();

            registrosAfectados = sentencia.getInt(4);

            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(procedimiento);
            switch (ex.getErrorCode()) {
                case 2290:
                    exGD.setMensajeErrorUsuario("Los usos del objeto no pueden ser negativos.");
                    break;
                case 1407:
                    exGD.setMensajeErrorUsuario("Todos los campos son obligatorios");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            }
            // También deberíamos de utilizar un Trigger en el futuro que el registro se elimine cuando llega a 0
            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Modifica los valores de configuración generales del sistema.
     *
     * El método actualiza los valores predeterminados de vida máxima, daño base
     * y defensa base mediante la llamada al procedimiento almacenado
     * MODIFICAR_CONFIGURACION.
     *
     * @param configuracion Un objeto Configuracion que contiene los nuevos
     * valores de configuración que se desean establecer
     * @return Un integer con los registros que se han modificado
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Kevin
     */
    public boolean modificarConfiguracion(Configuracion configuracion) throws ExcepcionGD {
        conectarBD();
        boolean exito = false;

        String sql = "{ call MODIFICAR_CONFIGURACION(?, ?, ?, ?) }";

        try {
            CallableStatement sentencia = conexion.prepareCall(sql);

            sentencia.setObject(1, configuracion.getConfiguracionVidaMaximaPredeterminado(), java.sql.Types.INTEGER);
            sentencia.setObject(2, configuracion.getConfiguracionDanoBasePredeterminado(), java.sql.Types.INTEGER);
            sentencia.setObject(3, configuracion.getConfiguracionDefensaBasePredeterminado(), java.sql.Types.INTEGER);

            sentencia.registerOutParameter(4, java.sql.Types.INTEGER);

            sentencia.execute();
            exito = true;

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(sql);

            switch (ex.getErrorCode()) {
                case 2290:
                    exGD.setMensajeErrorUsuario("Los valores de configuración no pueden ser negativos.");
                    break;
                case 1407:
                    exGD.setMensajeErrorUsuario("Todos los campos de configuración son obligatorios.");
                    break;
                default:
                    exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw exGD;
        }
        return exito;
    }

    //ELIMINAR------------------------------------------------------------------
    /**
     * Elimina un único registro de la tabla PERSONAJEOBJETO.
     *
     * @param idUsuario que se corresponde con el identificador del usuario al
     * que pertenece el objeto
     * @param idObjeto que se corresponde con el identificador del objeto que se
     * desea eliminar del personaje
     * @return Un integer con los registros que se han eliminado
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Kevin
     */
    public boolean eliminarPersonajeObjeto(Integer idUsuario, Integer idObjeto) throws ExcepcionGD {
        conectarBD();
        String dml = "";
        int registrosAfectados = 0;
        try {

            Statement sentencia = conexion.createStatement();

            dml = "DELETE FROM PERSONAJEOBJETO WHERE ID_USUARIO = " + idUsuario
                    + " AND ID_OBJETO = " + idObjeto;

            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Elimina un único registro de la tabla PERSONAJEHABILIDAD.
     *
     * @param idUsuario Integer que hace referencia al ID_USUARIO en
     * Personajehabilidad
     * @param idHabilidad Integer que hace referencia al ID_HABILIDAD en
     * Personajehabilidad
     * @return Un integer con los registros que se han eliminado correctamente
     * @throws ExcepcionGD Se lanzará cuando se produzca un error de base de
     * datos
     * @author Simón
     */
    public boolean eliminarPersonajeHabilidad(Integer idUsuario, Integer idHabilidad) throws ExcepcionGD {
        String dml = "";
        int registrosAfectados = 0;
        try {
            conectarBD();
            Statement sentencia = conexion.createStatement();

            dml = "delete from PERSONAJEHABILIDAD where ID_USUARIO = " + idUsuario
                    + " and ID_HABILIDAD = " + idHabilidad;

            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Elimina un unico registro de la tabla PERSONAJE
     *
     * @param idUsuario Un idUsuario que se corresponde con el identificador del
     * personaje que se desea eliminar
     * @return Un integer "registrosAfectados" con los registros que se han
     * insertado
     * @throws ExcepcionGD se lanzará cuando se produzca un error de base de
     * datos
     * @author Marina
     */
    public boolean eliminarPersonaje(Integer idUsuario) throws ExcepcionGD {
        int registrosAfectados = 0;
        String dml = "";
        try {
            conectarBD();
            Statement sentencia = conexion.createStatement();
            dml = "delete PERSONAJE where ID_USUARIO = " + idUsuario;
            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {

            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw exGD;
        }

        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Este método se utilizará para eliminar un usuario de la base de datos
     * GodsBattle
     *
     * @param idUsuario Un integer que se relacionará con la clave primaria de
     * un registro de la tabla Usuario
     * @return Integer con la cantidad de filas eliminadas
     * @throws ExcepcionGD Se lanzará cuando ocurre una excepción SQL
     * @author Iván
     */
    public boolean eliminarUsuario(Integer idUsuario) throws ExcepcionGD {
        conectarBD();
        Integer registrosAfectados = 0;
        String dml = "delete USUARIO where ID_USUARIO = " + idUsuario;

        try {
            Statement sentencia = conexion.createStatement();
            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionGD exGD = new ExcepcionGD();
            exGD.setCodigoErrorBD(ex.getErrorCode());
            exGD.setMensajeErrorBD(ex.getMessage());
            exGD.setSentenciaSQL(dml);
            exGD.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw exGD;
        }
        if (registrosAfectados == 0) {
            return false;
        } else {
            return true;
        }
    }
}
