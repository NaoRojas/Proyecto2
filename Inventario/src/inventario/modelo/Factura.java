//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.modelo;

import java.util.ArrayList;
import java.util.List;

public class Factura {

    private final List<Integer> contenedorCantidad;
    private final List<Producto> contenedorProducto;
    private int codigo;

    private double calcularTotal() {
        double resultado = 0;
        for (int i = 0; i < contenedorProducto.size(); i++) {
            resultado += (contenedorCantidad.get(i) * contenedorProducto.get(i).getPrecio());
        }
        return resultado;
    }

    private void reducirCantidad(Producto objeto, int n) {
        int a = objeto.getCantidad() - n;
        objeto.setCantidad(a);
    }

    private boolean validarCantidad(Producto objeto, int n) {
        if (objeto.getCantidad() >= n) {
            reducirCantidad(objeto, n);
            return true;
        }
        return false;
    }
    
    private int obtenerIndice(int codigo) {
        for (int i = 0; i < contenedorProducto.size(); i++) {
            if (contenedorProducto.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    public Factura() {
        this.contenedorCantidad = new ArrayList<>();
        this.contenedorProducto = new ArrayList<>();
        this.codigo = 0;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void agregarProducto(Producto objeto, int n) {
        if (validarCantidad(objeto, n)) {
            this.contenedorProducto.add(objeto);
            this.contenedorCantidad.add(n);
        }
    }

    public void eliminarProducto(Producto objeto) {
        int i = obtenerIndice(objeto.codigo);
        int j = objeto.getCantidad();
        objeto.setCantidad(j + contenedorCantidad.get(i));
        this.contenedorCantidad.remove(i);
        this.contenedorProducto.remove(i);
    }

    public void eliminarCantidad(Producto objeto, int n) {
        int i = obtenerIndice(objeto.getCodigo());
        int j = this.contenedorCantidad.get(i) - n;
        int l = objeto.getCantidad();
        boolean calculo = (j > 0);
        if (calculo) {
            this.contenedorCantidad.set(i, j);
            objeto.setCantidad(l + n);
        }
    }

    @Override
    public String toString() {
        StringBuilder l = new StringBuilder();
        Producto p;
        l.append(String.format("FACTURA #%d%n", codigo));
        l.append("[COD]\t[PRODUCTO]\t[CANTIDAD]\t[PRECIO]\n");
        for (int i = 0; i < this.contenedorCantidad.size(); i++) {
            p = contenedorProducto.get(i);
            if(p.getNombreProducto().length() <= 7)
            l.append(String.format("%d\t%s\t\t%d\t\t₡%4.2f%n", p.getCodigo(), p.getNombreProducto(),
                    contenedorCantidad.get(i), p.getPrecio()));
            else
                l.append(String.format("%d\t%s\t%d\t\t₡%4.2f%n", p.getCodigo(), p.getNombreProducto(),
                    contenedorCantidad.get(i), p.getPrecio()));
        }
        l.append(String.format("TOTAL:\t₡%4.2f%n", calcularTotal()));
        return l.toString();
    }

} //LLAVE CLASS
