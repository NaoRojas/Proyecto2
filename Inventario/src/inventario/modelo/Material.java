/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.modelo;

import java.text.SimpleDateFormat;

/**
 *
 * @author Nao Rojas
 */
public class Material extends Producto {
private double tamanio;
private double medida;

    public Material(double tamanio, double medida, String nombreProducto) {
        super(nombreProducto);
        this.tamanio = tamanio;
        this.medida = medida;
        
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }
     @Override
    public String toString() {
        return String.format(
                "{Material- Codigo: %d,Nombre: %s, Tamanio: %4.2f mm, Medida: %4.2f mm}",
               codigo,
                nombreProducto,
               tamanio,
               medida
        );
    }
    
    
}
