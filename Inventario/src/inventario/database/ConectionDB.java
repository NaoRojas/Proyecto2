/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Enibeth
 */
public class ConectionDB {
    
    private static ConectionDB instancia = null;

    private static final String PROTOCOLO = "jdbc:mysql:";
    private static final String DIRECCION_SERVIDOR = "localhost";
    private static final String BASE_DATOS = "Ferreteria";

    private static final String USUARIO = "root";
    private static final String CLAVE = "root";
    
    public ConectionDB() throws ClassNotFoundException, 
            InstantiationException, 
            IllegalAccessException{
        String nombreClase = "com.mysql.jdbc.Driver";
        Class.forName(nombreClase).newInstance();
    }
    
    public static ConectionDB getInstancia() throws ClassNotFoundException, 
            InstantiationException,
            IllegalAccessException{
        if(instancia == null)
            instancia = new ConectionDB();
        return instancia;
    }
    
    public Connection obtenerConexion() throws SQLException {

        String hileraConexion = String.format(
                "%s//%s/%s",
                PROTOCOLO, DIRECCION_SERVIDOR, BASE_DATOS);
        System.out.printf("Hilera de conexión: '%s'%n", hileraConexion);

        System.out.println("Abriendo conexión..");
        Connection cnx = DriverManager.getConnection(hileraConexion, USUARIO, CLAVE);
        return cnx;
    }
    
}
