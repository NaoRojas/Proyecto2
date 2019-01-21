package inventario.modelo;

import java.util.Observable;

public class Modelo extends Observable {
    
    private ConjuntoProducto contenedorProductos;
    //DAO

    public Modelo() {
        this.contenedorProductos = new ConjuntoProducto();
    }
    
    public void agregar(Producto objeto) {
        this.contenedorProductos.agregar(objeto);
        actualizar();
    }
    
    public Producto getElemento(int posicion) {
        return (this.contenedorProductos.getElemento(posicion));
    }
    
    public Material getMaterialPosicion(int posicion) {
        return (Material) this.contenedorProductos.getElemento(posicion);
    }
    
    public void eliminar(Producto objeto) {
        this.contenedorProductos.eliminar(objeto);
        actualizar();
    }
    
    public int obtenerCantidadMaterial() {
        return (this.contenedorProductos.obtenerCantidad(1));
    }
    
    public int obtenerCantidadHerramienta() {
        return (this.contenedorProductos.obtenerCantidad(2));
    }
    
    /*
    public String[] obtenerArregloDatosMaterial() {
        return (this.contenedorProductos.obtenerArregloDatos(1));
    }
    
    public String[] obtenerArregloDatosHerramienta() {
        return (this.contenedorProductos.obtenerArregloDatos(2));
    }
    */
    
    public Material getMaterial(int indice) {
        return (this.contenedorProductos.getMaterial(indice));
    }
    
    
    public String mostrarModelo() {
        return (this.contenedorProductos.toString());
    }
    
    public String mostrarInformacionEspecifico(int codigo) {
        return (this.contenedorProductos.buscarElementoCodigo(codigo).toString());
    }
    
    public void actualizar() {
        setChanged();
        notifyObservers();
    }
    
}
