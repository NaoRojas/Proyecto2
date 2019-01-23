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
public class GestorDaoMateriales {
    private static GestorDaoMateriales instancia = null;
    
    public GestorDaoMateriales(){
    }
    
    public static GestorDaoMateriales getInstancia(){
        if(instancia == null)
            instancia = new GestorDaoMateriales();
        return instancia;
    }
    
    
    
}
