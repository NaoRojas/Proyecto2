//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.modelo;

public class Herramienta extends Producto {

    private String capacidad;

    public static final String[] ATRIBUTOS = {"CODIGO", "NOMBRE", "CAPACIDAD", "PRECIO", "CANTIDAD"};

    private int evaluarLongitudCadena(String cadena) {
        if (cadena.length() <= 5) {
            return 1;
        }
        if (cadena.length() <= 10) {
            return 2;
        } else {
            return 3;
        }
    }

    public Herramienta(String nombreProducto, String capacidad, double precio, int cantidad) {
        super(2000, nombreProducto, precio, cantidad);
        this.capacidad = capacidad;
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
                return String.format("[Herramienta] \t%d\t %s\t\t\tcapacidad: %s\t precio: ₡%4.2f cantidad: %d unidad", codigo, nombreProducto, capacidad, precio, cantidad);
            }

            case 2: {
                return String.format("[Herramienta] \t%d\t %s\t\tcapacidad: %s\t precio: ₡%4.2f cantidad: %d unidad", codigo, nombreProducto, capacidad, precio, cantidad);
            }

            default: {
                return String.format("[Herramienta] \t%d\t %s \tcapacidad: %s\t precio: ₡%4.2f cantidad: %d unidad", codigo, nombreProducto, capacidad, precio, cantidad);
            }

        }
    }

    public String[] obtenerArregloDatos() {
        String[] datos = {String.format("%d", codigo), nombreProducto, capacidad, String.format("%4.2f", precio), String.format("%d", cantidad)};
        return datos;
    }

} //LLAVE CLASS
