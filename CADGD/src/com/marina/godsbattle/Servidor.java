/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.godsbattle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosgd.ExcepcionGD;



/**
 *
 * @author Marina
 * 
 * 
 * CLASE PRINCIPAL DEL SERVIDOR
 * ============================
 * Esta clase es el punto de entrada (main) ed nuestro servidor
 * Su función es:
 * 1.Abrir un puerto para escuchar peticiones.
 * 2. Verificar que la base de datos es accesible.
 * 3. Quedarse en un bucle infinito esperando a 
 * que los clientes de Android se conecten.
 * 4. Cuando un cliente se conecta, le asigna un "HiloCliente"  
 * para atenderle de forma exclusiva.
 */
public class Servidor {
    
    public static void main (String [] args) throws ExcepcionGD{
        //1. Definimos el puerto
        //El puerto 5000 (es arbitrario, pero tiene que ser el mismo
        //que pongamos en el cliente)
        
        int port = ServerConfig.SERVER_PORT;
        
        //2. INICIALIZAMOS EL SERVIDOR (ServerSocket)
        
        try(ServerSocket serverSocket = new ServerSocket(port)){
            
            System.out.println("=====================================");
            System.out.println("SERVIDOR INICIALIZADO CORRECTAMENTE");
            System.out.println("escuchando en puerto "+port);
            System.out.println("=====================================");
            
            //3. VERIFICAMOS LA CONEXION CON ORACLE
            
            String ipDb = ServerConfig.getDbIp();
            System.out.println("--> Verificamos conexión en: "+ ipDb);
            
            
            CADGD cadTest = new CADGD();
            
            if(cadTest.conectarBD()){
                System.out.println("[OK] Conexión exitosa con la base de datos.");
            }else{
                System.out.println("ERROR. No se pudo conectar a la base de datos.");
                System.out.println("AYUDA. Revisar ServerConfig.java y asegurar que: \n"
                        + "1. Que la VM esta encendida\n"
                        + "2. Que la IP es correcta en la VM."
                        + "3. Que hay ping entre PC y la VM.");
            }
            
            
            // 4. BUCLE INFINITO DE ESCUCHA
            //El nunca termina por si mismo. Siempre queda a la espera
            //de clientes.
            System.out.println("Esperando clientes...");
            
            while(true){
                // 4.1 EL BLOQUEO: accept() 
                //Esra linea congela el programa  hasta que un cliente intente conectarse
                Socket socket = serverSocket.accept();
               
                System.out.println("!Nuevo cliente conectado¡ Desde la ip: "+socket.getInetAddress().getHostAddress());
                
                // 4.2 DELEGAMOS EN UN HILO
                //Si atendiaramos al cliente aqui, nadie mas podria conectarse mientras tanto
                //Por eso, crear un trabajador (HiloCliente) dedicado solo a este usuario
                HiloCliente hilo = new HiloCliente(socket);
                
                //4.3 ARRANCAR EL HILO
                hilo.start();
                
            }
            
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
