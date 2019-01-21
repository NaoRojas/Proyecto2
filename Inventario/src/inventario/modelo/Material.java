//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.modelo;

public class Material extends Producto {

    private double tamanio;
    private double medida;

    public static final String[] ATRIBUTOS = {"CODIGO", "NOMBRE",
        "TAMAÑO", "MEDIDA", "PRECIO", "CANTIDAD"};

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
    

    public Material(String nombreProducto, double tamanio, double medida, double precio, int cantidad) {
        super(1000, nombreProducto, precio, cantidad);
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
        switch (evaluarLongitudCadena(nombreProducto)) {

            case 1: {
                return String.format("[Material] \t%d\t %s\t\t tamaño: %4.2fmm\t medida: %4.2fmm\t precio: ₡%4.2f cantidad: %d kg",
                        codigo, nombreProducto, tamanio, medida, precio, cantidad);
            }

            case 2: {
                return String.format("[Material] \t%d\t %s\t tamaño: %4.2fmm\t medida: %4.2fmm\t precio: ₡%4.2f cantidad: %d kg",
                        codigo, nombreProducto, tamanio, medida, precio, cantidad);
            }

            default: {
                return String.format("[Material] \t%d\t %s tamaño: %4.2fmm\t medida: %4.2fmm\t precio: ₡%4.2f cantidad: %d kg",
                        codigo, nombreProducto, tamanio, medida, precio, cantidad);
            }
        }
    }

    public String[] obtenerArregloDatos() {
        String[] datos = {String.format("%d", codigo), nombreProducto,
            String.format("%4.2fmm", tamanio), String.format("%4.2fmm", medida), String.format("%4.2f", precio), String.format("%d", cantidad)};
        return datos;
    }

} //LLAVE CLASS
