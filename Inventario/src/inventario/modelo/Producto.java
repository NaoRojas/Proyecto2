//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.modelo;

public abstract class Producto {

    protected int codigo;
    protected String nombreProducto;
    protected double precio;
    protected int cantidad;

    public Producto(int codigo, String nombreProducto, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%4.2f\t%d", getCodigo(), getNombreProducto(), getPrecio(), getCantidad());
        
    }
} //LLAVE CLASS
