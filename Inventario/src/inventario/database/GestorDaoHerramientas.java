package inventario.database;

import inventario.modelo.ConjuntoProducto;
import inventario.modelo.Herramienta;
import inventario.modelo.Material;
import inventario.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enibeth
 */
public class GestorDaoHerramientas {

    private static GestorDaoHerramientas instancia = null;

    private static final String COMANDO_AGREGAR = "INSERT INTO Ferreteria.herramientas (codigo, nombreProducto,"
            + " precio, cantidad, capasidad)"
            + "VALUES (?, ?, ?, ?, ?); ";

    private static final String COMANDO_TODOS = "SELECT * FROM Ferreteria.herramientas;";

    private static final String COMANDO_ELIMINAR = "DELETE FROM Ferreteria.herramientas WHERE codigo = ?;";

    private static final String COMANDO_TODOS_MATERIAL = "SELECT * FROM Ferreteria.materiales;";

    private static final String COMANDO_CONSULTA_CODIGO = "SELECT * FROM Ferreteria.herramientas WHERE codigo = ?; ";

    private static final String COMANDO_MODIFICAR = "UPDATE Ferreteria.herramientas SET nombreProducto = ?, "
            + "precio = ?, cantidad = ?, capasidad = ? WHERE codigo = ?; ";
    
    private static final String COMANDO_ELIMINA_DATOS_TABLA = "TRUNCATE TABLE Ferreteria.herramientas;";
    
    
    public GestorDaoHerramientas() {
    }

    public static GestorDaoHerramientas getInstancia() {
        if (instancia == null) {
            instancia = new GestorDaoHerramientas();
        }
        return instancia;
    }

    public void agregarHerramienta(Herramienta p) throws SQLException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_AGREGAR)) {

            stm.clearParameters();

            stm.setInt(1, p.getCodigo());
            stm.setString(2, p.getNombreProducto());
            stm.setDouble(3, p.getPrecio());
            stm.setInt(4, p.getCantidad());
            stm.setString(5, p.getCapacidad());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            }

        }

    }

    public void eliminar(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINAR)) {

            stm.clearParameters();
            stm.setInt(1, codigo);

            if (stm.executeUpdate() != 1) {
                System.err.println(String.format("No se puede eliminar el registro: '%s'", codigo));

            }
        }
    }

    public List<Producto> recuperaHerramientas() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        List<Producto> lista = new ArrayList<>();

        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                Statement stm = cnx.createStatement()) {
            ResultSet rs = stm.executeQuery(COMANDO_TODOS_MATERIAL);

            while (rs.next()) {
                Producto a = new Material(
                        rs.getInt("codigo"),
                        rs.getString("nombreProducto"),
                        rs.getDouble("tamanio"),
                        rs.getDouble("medida"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"));

                lista.add((Material) a);
            }
        }
        
        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                Statement stm = cnx.createStatement()) {
            ResultSet rs = stm.executeQuery(COMANDO_TODOS);

            while (rs.next()) {
                Producto p = new Herramienta(
                        rs.getInt("codigo"),
                        rs.getString("nombreProducto"),
                        rs.getString("capasidad"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"));
                lista.add((Herramienta) p);
            }
        }
        return lista;
    }

    public Producto buscaPorCodigo(int codigo) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException,
            Exception {
        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_CONSULTA_CODIGO)) {

            stm.clearParameters();
            stm.setInt(1, codigo);

            try (ResultSet rs = stm.executeQuery()) {  //updates son los execure Updates los void por asi decirlo
                if (rs.next()) {
                    return new Herramienta(
                            rs.getInt("codigo"),
                            rs.getString("nombreProducto"),
                            rs.getString("capasidad"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad"));

                }
                else{
                    throw new Exception(String.format("No se encuentra la herramienta: %d", codigo));
                }
            }
        }
    }

    public void modificaHerramienta(int codigo, Herramienta o) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException,
            Exception {
                 try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_MODIFICAR)) {

            stm.clearParameters();
            stm.setString(1, o.getNombreProducto());
            stm.setDouble(2, o.getPrecio());
            stm.setInt(3, o.getCantidad());
            stm.setString(4, o.getCapacidad());
            stm.setInt(5, codigo);
            
            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            }
            
        }
    }

    public void eliminarTodos() throws SQLException, 
            ClassNotFoundException, 
            InstantiationException, 
            IllegalAccessException {
         try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINA_DATOS_TABLA)) {

//            if (stm.executeUpdate() != 1) {
//                throw new SQLException();
//            }
 
        }
    }

}
