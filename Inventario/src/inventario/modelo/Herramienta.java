//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.modelo;

public class Herramienta extends Producto {
    
    private String capacidad;

    public static final String[] ATRIBUTOS = {"CODIGO", "NOMBRE", "CAPACIDAD", "PRECIO", "CANTIDAD"};

    private int evaluarLongitudCadena(String cadena) {
        if (cadena.length() <= 7) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public Herramienta(String nombreProducto, String capacidad, double precio, int cantidad) {
        super(2000, nombreProducto, precio, cantidad);
        this.capacidad = capacidad;
    }
    
    public Herramienta(int codi, String nombreProducto, String capacidad, double precio, int cantidad) {
        super(codi, nombreProducto, precio, cantidad);
        this.capacidad = capacidad;
    }

    public Herramienta(){
        this(0, "", "", 0, 0);
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        switch (evaluarLongitudCadena(nombreProducto)) {

            case 1: {
                return String.format("%d\t%s\t\t%s\t\t₡%4.2f\t%d unidades", codigo, nombreProducto, capacidad, precio, cantidad);
            }

            case 2: {
                return String.format("%d\t%s\t%s\t\t₡%4.2f\t%d unidades", codigo, nombreProducto, capacidad, precio, cantidad);
            }

            default: {
                return null;
            }

        }
    }
    
    public String mostrarDatos() {
        return String.format("%d\t%s\t%s", codigo, nombreProducto, capacidad);
    }

    public String[] obtenerArregloDatos() {
        String[] datos = {String.format("%d", codigo), nombreProducto, capacidad, String.format("₡%4.2f", precio), String.format("%d uds.", cantidad)};
        return datos;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getNombreProducto() {
        return nombreProducto;
    }

    @Override
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    
    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
} //LLAVE CLASS
