package inventario.modelo;

import java.util.Observable;

public class Modelo extends Observable {

    private final ConjuntoProducto contenedorProductos;
    private final ConjuntoFactura contenedorFacturas;
    //DAO

    public Modelo() {
        this.contenedorProductos = new ConjuntoProducto();
        this.contenedorFacturas = new ConjuntoFactura();
    }

    public void agregar(Producto objeto) {
        this.contenedorProductos.agregar(objeto);
        actualizar();
    }

    public Producto getElemento(int indice) {
        return (this.contenedorProductos.getElemento(indice));
    }

    public Producto buscarElementoCodigo(int codigo) {
        return (this.contenedorProductos.buscarElementoCodigo(codigo));
    }

    public Material getMaterialPosicion(int indice) {
        return (Material) this.contenedorProductos.getElemento(indice);
    }

    public void setElementoMaterial(int codigo, Material objeto) {
        this.contenedorProductos.setElementoMaterial(codigo, objeto);
        actualizar();
    }

    public void setElementoHerramienta(int codigo, Herramienta objeto) {
        this.contenedorProductos.setElementoHerramienta(codigo, objeto);
        actualizar();
    }

    public void eliminar(int codigo) {
        this.contenedorProductos.eliminarCod(codigo);
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

    public Herramienta getHerramienta(int indice) {
        return (this.contenedorProductos.getHerramienta(indice));
    }

    public String mostrarModelo(int i) {
        switch (i) {
            case 1: { //MATERIAL
                return (this.contenedorProductos.mostrarModeloMaterial());
            }

            case 2: { //HERRAMIENTA
                return (this.contenedorProductos.mostrarModeloHerramienta());
            }

            default: {
                return null;
            }
        }
    }

    public String mostrarInformacionEspecifico(int codigo, int i) {
        return (this.contenedorProductos.mostrarInformacionEspecifico(codigo, i));
    }

    public void actualizar() {
        setChanged();
        notifyObservers();
    }

    public void agregarFactura(Factura objeto) {
        this.contenedorFacturas.agregar(objeto);
        actualizar();
    }

    public Factura getFacturaIndice(int i) {
        return (this.contenedorFacturas.getElemento(i));
    }

    public String mostrarFacturas() {
        return (this.contenedorFacturas.toString());
    }
} //LLAVE CLASS
