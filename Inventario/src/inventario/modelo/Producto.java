/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

/**
 *
 * @author Nao Rojas
 */
public abstract class Producto {
    public int codigo;
    public String nombreProducto;

    public Producto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
     
}
