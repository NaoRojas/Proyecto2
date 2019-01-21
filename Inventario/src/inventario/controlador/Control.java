package inventario.controlador;

import inventario.modelo.Material;
import inventario.modelo.Modelo;
import inventario.modelo.Producto;
import java.util.Observer;

public class Control {

    private final Modelo modelo;

    public Control(Modelo modelo) {
        this.modelo = modelo;
    }

    public void agregar(Producto objeto) {
        this.modelo.agregar(objeto);
    }

    public Producto getElemento(int posicion) {
        return (this.modelo.getElemento(posicion));
    }

    public void eliminar(Producto objeto) {
        this.modelo.eliminar(objeto);
    }

    public int obtenerCantidadMaterial() {
        return (this.modelo.obtenerCantidadMaterial());
    }

    public int obtenerCantidadHerramienta() {
        return (this.modelo.obtenerCantidadHerramienta());
    }
    
    public Material getMaterial(int indice) {
        return (this.modelo.getMaterial(indice));
    }

    public String mostrarModelo() {
        return (modelo.mostrarModelo());
    }

    public void addObs(Observer obs) {
        modelo.addObserver(obs);
    }

    public void removeObs(Observer obs) {
        modelo.deleteObserver(obs);
    }

} //LLAVE CLASS
