//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.controlador;

import inventario.modelo.Factura;
import inventario.modelo.Herramienta;
import inventario.modelo.Material;
import inventario.modelo.Modelo;
import inventario.modelo.Producto;
import java.util.Observer;

public class Control {

    private final Modelo modelo;
    private boolean continuar;

    public Control(Modelo modelo) {
        this.modelo = modelo;
        continuar = false;
    }

    public void agregar(Producto objeto) {
        continuar = true;
        this.modelo.agregar(objeto);
    }

    public Producto getElemento(int posicion) {

        return (this.modelo.getElemento(posicion));
    }

    public Producto buscarElementoCodigo(int codigo) throws Exception {

        return (this.modelo.buscarElementoCodigo(codigo));
    }

    public void eliminar(int codigo) {
        continuar = true;
        this.modelo.eliminar(codigo);
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

    public Herramienta getHerramienta(int indice) {

        return (this.modelo.getHerramienta(indice));
    }

    public void setElementoMaterial(int codigo, Material objeto) {
        continuar = true;
        this.modelo.setElementoMaterial(codigo, objeto);
    }

    public void setElementoHerramienta(int codigo, Herramienta objeto) {
        continuar = true;
        this.modelo.setElementoHerramienta(codigo, objeto);
    }

    public String mostrarModeloMaterial() {
        return (modelo.mostrarModelo(1));
    }

    public String mostrarModeloHerramienta() {
        return (modelo.mostrarModelo(2));
    }

    public String mostrarInformacionElementoEspecifico(int codigo, int i) {
        continuar = true;
        return (modelo.mostrarInformacionEspecifico(codigo, i));
    }

    public void addObs(Observer obs) {
        modelo.addObserver(obs);
    }

    public void removeObs(Observer obs) {
        modelo.deleteObserver(obs);
    }

    public void agregarFactura(Factura objeto) {
        continuar = true;

        modelo.agregarFactura(objeto);
    }

    public Factura getFactura(int indice) {

        return (modelo.getFacturaIndice(indice));
    }

    public String mostrarFacturas() {
        return (modelo.mostrarFacturas());
    }

    public void setTX() {
        if (continuar) {
            modelo.setTX();
        }
        continuar = false;
    }

    public int getTX() {
        return modelo.getTX();
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public boolean isContinuar() {
        return continuar;
    }

} //LLAVE CLASS
