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
        if (cadena.length() <= 7) {
            return 1;
        }
        if (cadena.length() <= 14) {
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

    public Material(int codigo, String nombreProducto, double tamanio, double medida, double precio, int cantidad) {
        super(codigo, nombreProducto, precio, cantidad);
        this.tamanio = tamanio;
        this.medida = medida;
    }
    
    public Material(){
        this(0,"",0,0,0,0);
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
                return String.format("%d\t%s\t\t%4.2fmm\t\t%4.2fmm\t\t₡%4.2f\t\t%dkg",
                        codigo, nombreProducto, tamanio, medida, precio, cantidad);
            }

            case 2: {
                return String.format("%d\t%s\t%4.2fmm\t\t%4.2fmm\t\t₡%4.2f\t\t%dkg",
                        codigo, nombreProducto, tamanio, medida, precio, cantidad);
            }

            default: {
                return null;
            }
        }
    }
    
    public String mostrarDatos() {
        return String.format("%d\t%s\t%4.2fmm\t\t%4.2fmm", codigo, nombreProducto, tamanio, medida);
    }

    public String[] obtenerArregloDatos() {
        String[] datos = {String.format("%d", codigo), nombreProducto,
            String.format("%4.2fmm", tamanio), String.format("%4.2fmm", medida), String.format("₡%4.2f", precio), String.format("%dkg.", cantidad)};
        return datos;
    }

    public int getCodigo() {
        return codigo;
    }

    //@XmlElementRef
    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    @Override
    public String getNombreProducto() {
        return nombreProducto;
    }

    //@XmlElementRef
    @Override
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    
    @Override
    public double getPrecio() {
        return precio;
    }

    //@XmlElementRef
    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    //@XmlElementRef
    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
} //LLAVE CLASS

