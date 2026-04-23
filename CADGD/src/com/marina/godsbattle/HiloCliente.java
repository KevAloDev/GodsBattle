package com.marina.godsbattle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import peticionesrespuestasgd.*;
import static peticionesrespuestasgd.PeticionUsuario.TipoOperacion.PING;
import pojosgd.*;

public class HiloCliente extends Thread {

    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    @Override

    public void run() {

        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

            Object peticion = ois.readObject();

            if (peticion instanceof PeticionUsuario) {

                manejarUsuario((PeticionUsuario) peticion, oos);

            } else if (peticion instanceof PeticionPersonaje) {

                manejarPersonaje((PeticionPersonaje) peticion, oos);

            } else if (peticion instanceof PeticionArma) {

                manejarArma((PeticionArma) peticion, oos);

            } else if (peticion instanceof PeticionArmadura) {

                manejarArmadura((PeticionArmadura) peticion, oos);

            } else if (peticion instanceof PeticionHabilidad) {

                manejarHabilidad((PeticionHabilidad) peticion, oos);

            } else if (peticion instanceof PeticionEnemigo) {

                manejarEnemigo((PeticionEnemigo) peticion, oos);

            } else if (peticion instanceof PeticionEnemigoPiso) {

                manejarEnemigoPiso((PeticionEnemigoPiso) peticion, oos);

            } else if (peticion instanceof PeticionEnemigoHabilidad) {

                manejarEnemigoHabilidad((PeticionEnemigoHabilidad) peticion, oos);

            } else if (peticion instanceof PeticionObjeto) {

                manejarObjeto((PeticionObjeto) peticion, oos);

            } else if (peticion instanceof PeticionPersonajeObjeto) {

                manejarPersonajeObjeto((PeticionPersonajeObjeto) peticion, oos);

            } else if (peticion instanceof PeticionPersonajeHabilidad) {

                manejarPersonajeHabilidad((PeticionPersonajeHabilidad) peticion, oos);

            } else if (peticion instanceof PeticionPiso) {

                manejarPiso((PeticionPiso) peticion, oos);

            } else if (peticion instanceof PeticionConfiguracion) {

                manejarConfiguracion((PeticionConfiguracion) peticion, oos);

            } else {

                System.out.println("Petición desconocida recibida.");
            }

        } catch (Exception e) {
            System.out.println("Error en HiloCliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Error cerrando socket: " + e.getMessage());
            }
        }
    }

    private void manejarUsuario(PeticionUsuario peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaUsuario respuesta = new RespuestaUsuario();
        boolean exito;
        try {
            switch (peticion.getTipoOperacion()) {

                case PING:
                    respuesta.setExito(true);
                    respuesta.setMensaje("PONG Usuario");
                    break;

                case CREATE:
                    exito = cad.insertarUsuario(peticion.getUsuario());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Usuario creado correctamente");
                    break;

                case READ:
                    Usuario usuario = cad.leerUsuario(peticion.getIdUsuario());
                    
                    if (usuario != null ) {
                        respuesta.setExito(true);
                        respuesta.setUsuario(usuario);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Usuario no encontrado");
                    }
                    break;

                case LOGIN:
                    usuario = cad.leerUsuarioLogin(peticion.getUsuario().getUsuarioCorreo());
                    
                    if (usuario != null && usuario.getUsuarioContrasena().equals(peticion.getUsuario().getUsuarioContrasena())) {
                        respuesta.setExito(true);
                        respuesta.setUsuario(usuario);
                        respuesta.setMensaje("Login exitoso");
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Usuario no encontrado");
                    }
                    break;

                case READ_ALL:

                    ArrayList<Usuario> lista = cad.leerUsuarios();

                    if (!lista.isEmpty()) {
                        respuesta.setUsuarios(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                case UPDATE:
                    exito = cad.modificarUsuario(
                            peticion.getUsuario().getUsuarioIdUsuario(),
                            peticion.getUsuario()
                    );
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Usuario actualizado");
                    break;

                case DELETE:
                    exito = cad.eliminarUsuario(
                            peticion.getUsuario().getUsuarioIdUsuario()
                    );
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Usuario eliminado");
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Usuario desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

           
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarPersonaje(PeticionPersonaje peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaPersonaje respuesta = new RespuestaPersonaje();
        boolean exito;
        try {
            switch (peticion.getTipoOperacion()) {
                case PING:
                    respuesta.setExito(true);
                    respuesta.setMensaje("PONG personaje");
                    break;

                case CREATE:
                    exito = cad.insertarPersonaje(peticion.getPersonaje());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Personaje creado correctamente");
                    break;

                case READ:
                    Personaje personaje = cad.leerPersonaje(peticion.getIdPersonaje());
                    if (personaje != null) {
                        respuesta.setExito(true);
                        respuesta.setPersonaje(personaje);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Personaje no encontrado");
                    }
                    break;

                case READ_ALL:
                    ArrayList<Personaje> personajes = cad.leerPersonajes();

                    if (!personajes.isEmpty()) {
                        respuesta.setPersonajes(personajes);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + personajes.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                case UPDATE:
                   
                    exito = cad.modificarPersonaje(
                            peticion.getIdPersonaje(),
                            peticion.getPersonaje()
                    );
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Personaje actualizado");
                    break;

                case DELETE:
                    exito = cad.eliminarPersonaje(peticion.getIdPersonaje());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Personaje eliminado");
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Personaje desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarArmadura(PeticionArmadura peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaArmadura respuesta = new RespuestaArmadura();
        try {
            switch (peticion.getTipoOperacion()) {

                case READ:
                    Armadura armadura = cad.leerArmadura(peticion.getIdArmadura());
                    if (armadura != null) {
                        respuesta.setExito(true);
                        respuesta.setArmadura(armadura);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Armadura no encontrada");
                    }
                    break;

                case READ_ALL:
                    ArrayList<Armadura> lista = cad.leerArmaduras();

                    if (!lista.isEmpty()) {
                        respuesta.setArmaduras(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Armadura desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarHabilidad(PeticionHabilidad peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaHabilidad respuesta = new RespuestaHabilidad();
        try {
            switch (peticion.getTipoOperacion()) {

                case READ:
                    Habilidad habilidad = cad.leerHabilidad(peticion.getIdHabilidad());
                    if (habilidad != null) {
                        respuesta.setExito(true);
                        respuesta.setHabilidad(habilidad);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Habilidad no encontrada");
                    }
                    break;

                case READ_ALL:

                    ArrayList<Habilidad> lista = cad.leerHabilidades();

                    if (!lista.isEmpty()) {
                        respuesta.setHabilidades(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Habilidad desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarArma(PeticionArma peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaArma respuesta = new RespuestaArma();
        try {
            switch (peticion.getTipoOperacion()) {

                case READ:
                    Arma arma = cad.leerArma(peticion.getIdArma());
                    if (arma != null) {
                        respuesta.setExito(true);
                        respuesta.setArma(arma);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Arma no encontrada");
                    }
                    break;

                case READ_ALL:
                    ArrayList<Arma> lista = cad.leerArmas();

                    if (!lista.isEmpty()) {
                        respuesta.setArmas(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Arma desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarEnemigo(PeticionEnemigo peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaEnemigo respuesta = new RespuestaEnemigo();
        try {
            switch (peticion.getTipoOperacion()) {

                case READ:
                    Enemigo enemigo = cad.leerEnemigo(peticion.getIdEnemigo());
                    if (enemigo != null) {
                        respuesta.setExito(true);
                        respuesta.setEnemigo(enemigo);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Enemigo no encontrado");
                    }
                    break;

                case READ_ALL:
                    ArrayList<Enemigo> lista = cad.leerEnemigos();

                    if (!lista.isEmpty()) {
                        respuesta.setEnemigos(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Enemigo desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarEnemigoPiso(PeticionEnemigoPiso peticion, ObjectOutputStream oos) throws Exception {
        CADGD cad = new CADGD();
        RespuestaEnemigoPiso respuesta = new RespuestaEnemigoPiso();
        try {
            switch (peticion.getTipoOperacion()) {

                case READ:
                    EnemigoPiso ep = cad.leerEnemigoPiso(
                            peticion.getIdEnemigo(),
                            peticion.getIdPiso()
                    );
                    if (ep != null) {
                        respuesta.setExito(true);
                        respuesta.setEnemigoPiso(ep);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("EnemigoPiso no encontrado");
                    }
                    break;

                case READ_ALL:
                    ArrayList<EnemigoPiso> lista = cad.leerEnemigoPisos(peticion.getIdPiso());

                    if (!lista.isEmpty()) {
                        respuesta.setEnemigoPisos(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación EnemigoPiso desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarEnemigoHabilidad(PeticionEnemigoHabilidad peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaEnemigoHabilidad respuesta = new RespuestaEnemigoHabilidad();
        try {
            switch (peticion.getTipoOperacion()) {

                case READ_ALL:
                    ArrayList<EnemigoHabilidad> lista = cad.leerEnemigoHabilidades(peticion.getIdEnemigo());

                    if (!lista.isEmpty()) {
                        respuesta.setEnemigoHabilidades(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación EnemigoHabilidad desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarPersonajeObjeto(PeticionPersonajeObjeto peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaPersonajeObjeto respuesta = new RespuestaPersonajeObjeto();
        boolean exito;
        try {
            switch (peticion.getTipoOperacion()) {

                case CREATE:
                    exito = cad.insertarPersonajeObjeto(peticion.getPersonajeObjeto());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Añadido al inventario");
                    break;

                case DELETE:
                    exito = cad.eliminarPersonajeObjeto(
                            peticion.getIdUsuario(),
                            peticion.getIdObjeto()
                    );
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Elmininado del inventario");
                    break;

                case READ:
                    PersonajeObjeto po = cad.leerPersonajeObjeto(
                            peticion.getIdUsuario(),
                            peticion.getIdObjeto()
                    );
                    if (po != null) {
                        respuesta.setExito(true);
                        respuesta.setPersonajeObjeto(po);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("No encontrado");
                    }
                    break;

                case READ_ALL:
                    ArrayList<PersonajeObjeto> lista = cad.leerPersonajeObjetos(peticion.getIdUsuario());

                    if (!lista.isEmpty()) {
                        respuesta.setPersonajeObjetos(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                case UPDATE:
                    exito = cad.modificarPersonajeObjeto(peticion.getIdUsuario(), peticion.getIdObjeto(), peticion.getPersonajeObjeto());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Inventario actualizado");
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación PersonajeObjeto desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");   
        }

        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarPersonajeHabilidad(PeticionPersonajeHabilidad peticion, ObjectOutputStream oos) throws Exception {

        CADGD cad = new CADGD();
        RespuestaPersonajeHabilidad respuesta = new RespuestaPersonajeHabilidad();
        boolean exito;
        try {
            switch (peticion.getTipoOperacion()) {
                case READ:
                    PersonajeHabilidad ph = cad.leerPersonajeHabilidad(peticion.getIdUsuario(), peticion.getIdHabilidad());
                    if (ph != null) {
                        respuesta.setExito(true);
                        respuesta.setPersonajeHabilidad(ph);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("EnemigoHabilidad no encontrado");
                    }
                    break;
                case READ_ALL:
                    ArrayList<PersonajeHabilidad> lista = cad.leerPersonajeHabilidades(peticion.getIdUsuario());

                    if (!lista.isEmpty()) {
                        respuesta.setPersonajeHabilidades(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                case CREATE:
                    exito = cad.insertarPersonajeHabilidad(peticion.getPersonajeHabilidad());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("PersonajeHabilidad creada correctamente");
                    break;

                case DELETE:
                    exito = cad.eliminarPersonajeHabilidad(
                            peticion.getIdUsuario(),
                            peticion.getIdHabilidad()
                    );
                    respuesta.setExito(exito);
                    respuesta.setMensaje("PersonajeHabilidad eliminada");
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación PersonajeHabilidad desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }

        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarObjeto(PeticionObjeto peticion, ObjectOutputStream oos) throws Exception {
        CADGD cad = new CADGD();
        RespuestaObjeto respuesta = new RespuestaObjeto();
        try {
            switch (peticion.getTipoOperacion()) {
                case READ:
                    Objeto objeto = cad.leerObjeto(peticion.getIdObjeto());
                    if (objeto != null) {
                        respuesta.setExito(true);
                        respuesta.setObjeto(objeto);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Objeto no encontrado");
                    }
                    break;

                case READ_ALL:
                    ArrayList<Objeto> lista = cad.leerObjetos();

                    if (!lista.isEmpty()) {
                        respuesta.setObjetos(lista);
                        respuesta.setExito(true);
                        respuesta.setMensaje("Listado recuperado con " + lista.size() + "");

                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("La base de datos parece vacia");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación Objeto desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }

        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarPiso(PeticionPiso peticion, ObjectOutputStream oos) throws Exception {
        CADGD cad = new CADGD();
        RespuestaPiso respuesta = new RespuestaPiso();
        try {
            switch (peticion.getTipoOperacion()) {
                case READ:
                    Piso piso = cad.leerPiso(peticion.getIdPiso());
                    if (piso != null) {
                        respuesta.setExito(true);
                        respuesta.setPiso(piso);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Piso no encontrado");
                    }
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación piso desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

            
        }
        oos.writeObject(respuesta);
        oos.flush();
    }

    private void manejarConfiguracion(PeticionConfiguracion peticion, ObjectOutputStream oos) throws Exception {
        CADGD cad = new CADGD();
        RespuestaConfiguracion respuesta = new RespuestaConfiguracion();
        boolean exito;
        try {
            switch (peticion.getTipoOperacion()) {
                case READ:
                    Configuracion configuracion = cad.leerConfiguracion();

                    if (configuracion != null) {
                        respuesta.setExito(true);
                        respuesta.setConfiguracion(configuracion);
                    } else {
                        respuesta.setExito(false);
                        respuesta.setMensaje("Configuración no encontrado");
                    }
                    break;

                case UPDATE:
                    exito = cad.modificarConfiguracion(peticion.getConfiguracion());
                    respuesta.setExito(exito);
                    respuesta.setMensaje("Configuración actualizada");
                    break;

                default:
                    respuesta.setExito(false);
                    respuesta.setMensaje("Operación configuración desconocida");
                    break;
            }
        } catch (ExcepcionGD e) {

            //AQUÍ CAPTURAS TU EXCEPCIÓNGD
            respuesta.setExito(false);
            respuesta.setMensaje(e.getMensajeErrorUsuario());

        } catch (Exception e) {

            // ERROR NO CONTROLADO
            respuesta.setExito(false);
            respuesta.setMensaje("Error interno del servidor");

        }
        oos.writeObject(respuesta);
        oos.flush();
    }
}
