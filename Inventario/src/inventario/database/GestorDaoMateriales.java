/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.database;

import inventario.modelo.Material;
import inventario.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Enibeth
 */
public class GestorDaoMateriales {

    private static GestorDaoMateriales instancia = null;

    private static final String COMANDO_AGREGAR = "INSERT INTO Ferreteria.materiales (codigo, nombreProducto,"
            + " precio, cantidad, tamanio, medida)"
            + "VALUES (?, ?, ?, ?, ?, ?); ";

    private static final String COMANDO_ELIMINAR = "DELETE FROM Ferreteria.materiales WHERE codigo = ?;";

    private static final String COMANDO_CONSULTA_CODIGO = "SELECT * FROM Ferreteria.materiales WHERE codigo = ?; ";

    private static final String COMANDO_MODIFICAR = "UPDATE Ferreteria.materiales SET nombreProducto = ?, "
            + "precio = ?, cantidad = ?, tamanio = ?, medida = ? WHERE codigo = ?; ";

    private static final String COMANDO_ELIMINA_DATOS_TABLA = "TRUNCATE TABLE Ferreteria.materiales;";

    public GestorDaoMateriales() {
    }

    public static GestorDaoMateriales getInstancia() {
        if (instancia == null) {
            instancia = new GestorDaoMateriales();
        }
        return instancia;
    }

    public void agregarMaterial(Material p) throws SQLException,
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
            stm.setDouble(5, p.getTamanio());
            stm.setDouble(6, p.getMedida());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            }

        }

    }

    public void eliminarMaterial(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINAR)) {

            stm.clearParameters();
            stm.setLong(1, codigo);

            if (stm.executeUpdate() != 1) {
                System.err.println(String.format("No se puede eliminar el registro: '%s'", codigo));

            }
        }
    }

    public Material buscaPorCodigo(int codigo) throws SQLException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            Exception {
        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_CONSULTA_CODIGO)) {

            stm.clearParameters();
            stm.setInt(1, codigo);

            try (ResultSet rs = stm.executeQuery()) {  //updates son los execure Updates los void por asi decirlo
                if (rs.next()) {
                    return new Material(
                            rs.getInt("codigo"),
                            rs.getString("nombreProducto"),
                            rs.getDouble("tamanio"),
                            rs.getDouble("medida"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad"));
                } else {
                    throw new Exception(String.format("No se encuentra el material: %d", codigo));
                }
            }
        }
    }

    public void eliminar(int codigo) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINAR)) {

            stm.clearParameters();
            stm.setInt(1, codigo);

            if (stm.executeUpdate() != 1) {
                System.err.println(String.format("No se puede eliminar el registro: '%s'", codigo));

            }
        }
    }

    public void modificaMaterial(int codigo, Material o) throws ClassNotFoundException,
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
            stm.setDouble(4, o.getTamanio());
            stm.setDouble(5, o.getMedida());
            stm.setInt(6, codigo);

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            }

        }
    }

    public void agregarTodosMateriales(List<Producto> lista) throws SQLException,
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {

        try (Connection cnx = ConectionDB.getInstancia().obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINA_DATOS_TABLA)) {
            for (Producto p : lista) {
                if (p.getCodigo() < 2000) {
                    this.agregarMaterial((Material) p);
                }
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
