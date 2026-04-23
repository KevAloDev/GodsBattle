package com.marina.godsbattle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marina
 */
public class ServerConfig {

//    public static final Environment ENTORNO_ACTUAL = Environment.CLASE;
    public static final Environment ENTORNO_ACTUAL = Environment.CASA;

    public enum Environment {
        CASA, CLASE
    }

    //IPs MARINA
    public static String getDbIp() {
        switch (ENTORNO_ACTUAL) {
            case CASA:
                return "192.168.0.100";
                
                //Simón 
                //return "192.168.1.40";
                
                //return "192.168.18.210";
            case CLASE:
                return "172.16.210.1";
            default:
                return "172.16.210.1";
        }
    }
    
    

    //Configuración fija de la bd
    
    //CLASE MARINA
    //public static final String DB_SID = "TEST";
    //public static final String DB_PORT = "1521";
    //public static final String DB_USER = "GODSBATTLE";
    //public static final String DB_PASS = "kk";

      //CASA MARINA
//    public static final String DB_PORT = "1521";
//    public static final String DB_SID = "test";
//    public static final String DB_USER = "GD";
//    public static final String DB_PASS = "kk";
    
     //CASA IVÁN y SIMÓN
    public static final String DB_PORT = "1521";
    public static final String DB_SID = "test";
    public static final String DB_USER = "GodsBattle";
    public static final String DB_PASS = "kk";
    
    public static final int SERVER_PORT = 5000;
}
