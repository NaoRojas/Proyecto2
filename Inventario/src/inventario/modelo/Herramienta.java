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
public class Herramienta extends Producto {
    private String capacidad;

    public Herramienta(String capacidad, String nombreProducto) {
        super(nombreProducto);
        this.capacidad = capacidad;
    }
   
}
