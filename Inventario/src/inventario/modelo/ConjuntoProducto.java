//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.modelo;

import java.util.ArrayList;

public class ConjuntoProducto {

    private ArrayList<Producto> inventario;
    private static int codigo;

    public ConjuntoProducto() {
        ConjuntoProducto.codigo = 0;
        inventario = new ArrayList<>();
    }

    public void agregar(Producto m) {
        m.setCodigo(m.getCodigo() + (++codigo));
        inventario.add(m);
    }

    public Producto getElemento(int posicion) {
        return (inventario.get(posicion));
    }

    public Producto buscarElementoCodigo(int codigo) {
        for (Producto p : inventario) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    /*
    public Herramienta getHerramienta(int cod) {
        for (Producto p : inventario) {
            if (p.getCodigo() == cod) {
                return ((Herramienta) p);
            }
        }
        return null;
    }
    */

    public Material getMaterial(int indice) {
        if (getElemento(indice).getCodigo() < 1999 && getElemento(indice).getCodigo() > 1000) {
            return (Material) getElemento(indice);
        }
        
        else {
            getMaterial(++indice);
        }
        return null;
    }
     
    
    public void eliminar(Producto p) {
        inventario.remove(p);
    }

    public boolean eliminarCod(int cod) {
        for (Producto p : inventario) {
            if (p.getCodigo() == cod) {
                inventario.remove(p);
                return true;
            }
        }
        return false;
    }

    public int obtenerCantidad(int i) {
        int cantidad = 0;
        switch (i) {
            case 1: { //MATERIAL
                for (Producto objeto : inventario) {
                    if (objeto.getCodigo() < 1999 && objeto.getCodigo() > 1000) {
                        cantidad++;
                    }
                }
                return cantidad;
            }

            case 2: { //HERRAMIENTAS
                for (Producto objeto : inventario) {
                    if (objeto.getCodigo() < 2999 && objeto.getCodigo() > 2000) {
                        cantidad++;
                    }
                }
                return cantidad;
            }

            default:
                return 0;
        }
    }

    public String[] obtenerArregloDatos(int i) {
        switch (i) {
            case 1: { //MATERIAL
                for (Producto objeto : inventario) {
                    if (objeto.getCodigo() < 1999 && objeto.getCodigo() > 1000) {
                        Material m = (Material) objeto;
                        return m.obtenerArregloDatos();
                    }
                }
            }

            case 2: { //HERRAMIENTA
                for (Producto objeto : inventario) {
                    if (objeto.getCodigo() < 2999 && objeto.getCodigo() > 2000) {
                        Material m = (Material) objeto;
                        m.obtenerArregloDatos();
                    }
                }
            }
            
            default: {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();

        r.append(" TIPO\t\tCÓDIGO\t NOMBRE\n");
        inventario.forEach((p) -> {
            r.append(String.format("%s%n", p));
        });

        return r.toString();
    }

    public int obtenerCantidadProductos() {
        return (this.inventario.size());
    }
}
