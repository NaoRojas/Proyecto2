
package inventario.database;

/**
 *
 * @author Enibeth
 */
public class GestorDaoFactura {
 
    private static GestorDaoFactura instancia = null;
    
    public GestorDaoFactura(){
    }
    
    public static GestorDaoFactura getInstancia(){
        if(instancia == null)
            instancia = new GestorDaoFactura();
        return instancia;
    }
    
    
    
}
