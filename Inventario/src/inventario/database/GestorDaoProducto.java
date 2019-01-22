/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.database;

/**
 *
 * @author Enibeth
 */
public class GestorDaoProducto {
    private static GestorDaoProducto instancia = null;
    
    public GestorDaoProducto(){
    }
    
    public static GestorDaoProducto getInstancia(){
        if(instancia == null)
            instancia = new GestorDaoProducto();
        return instancia;
    }
    
    
    
}
