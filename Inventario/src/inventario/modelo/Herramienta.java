/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

import java.util.Random;

/**
 *
 * @author Nao Rojas
 */
public class Herramienta extends Producto {
    private int capacidad;

    public Herramienta(String nombreProducto) {
        super(nombreProducto);
        Random r = new Random();
        
        this.capacidad=0;
    }
    private void setCapacidad(){
    }
}
