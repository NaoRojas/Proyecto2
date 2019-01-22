//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.modelo;

import java.util.ArrayList;

public class ConjuntoProducto {

    private final ArrayList<Producto> inventario;
    private static int codigoHerramienta;
    private static int codigoMaterial;

    private void ordenar() {
        inventario.sort((t, t1) -> {
            return (t.compareTo(t1));
        });
    }

    public ConjuntoProducto() {
        ConjuntoProducto.codigoHerramienta = 0;
        ConjuntoProducto.codigoMaterial = 0;
        inventario = new ArrayList<>();
    }

    public void agregar(Producto m) {
        if (m.esMaterial()) {
            m.setCodigo(m.getCodigo() + (++codigoMaterial));
        } else {
            m.setCodigo(m.getCodigo() + (++codigoHerramienta));
        }
        inventario.add(m);
        ordenar();
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

    public Material getMaterial(int indice) {
        return (Material) getElemento(indice);
    }

    public Herramienta getHerramienta(int indice) {
        int i = indice + obtenerCantidad(1);
        return (Herramienta) getElemento(i);
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
                    if (objeto.esMaterial()) {
                        cantidad++;
                    }
                }
                return cantidad;
            }

            case 2: { //HERRAMIENTAS
                for (Producto objeto : inventario) {
                    if (objeto.esHerramienta()) {
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
                    if (objeto.esMaterial()) {
                        Material m = (Material) objeto;
                        return m.obtenerArregloDatos();
                    }
                }
            }

            case 2: { //HERRAMIENTA
                for (Producto objeto : inventario) {
                    if (objeto.esHerramienta()) {
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

    public void setElementoMaterial(int codigo, Material objeto) {
        for (Producto producto : inventario) {
            if (producto.getCodigo() == codigo) {
                ((Material) producto).setNombreProducto(objeto.getNombreProducto());
                ((Material) producto).setTamanio(objeto.getTamanio());
                ((Material) producto).setMedida(objeto.getMedida());
                ((Material) producto).setPrecio(objeto.getPrecio());
                ((Material) producto).setCantidad(objeto.getCantidad());
            }
        }
    }

    public void setElementoHerramienta(int codigo, Herramienta objeto) {
        for (Producto producto : inventario) {
            if (producto.getCodigo() == codigo) {
                ((Herramienta) producto).setNombreProducto(objeto.getNombreProducto());
                ((Herramienta) producto).setCapacidad(objeto.getCapacidad());
                ((Herramienta) producto).setPrecio(objeto.getPrecio());
                ((Herramienta) producto).setCantidad(objeto.getCantidad());
            }
        }
    }

    public String mostrarModeloMaterial() {
        StringBuilder l = new StringBuilder();
        for (Producto objeto : inventario) {
            if (objeto.esMaterial()) {
                l.append(String.format("%s%n", objeto.toString()));
            }
        }
        return l.toString();
    }

    public String mostrarModeloHerramienta() {
        StringBuilder l = new StringBuilder();
        for (Producto objeto : inventario) {
            if (objeto.esHerramienta()) {
                l.append(String.format("%s%n", objeto.toString()));
            }
        }
        return l.toString();
    }

    public String mostrarInformacionEspecifico(int codigo, int i) {
        switch (i) {
            case 1: {
                return ((Material) buscarElementoCodigo(codigo)).toString();
            }

            case 2: {
                return ((Herramienta) buscarElementoCodigo(codigo)).toString();
            }

            default:
                return null;
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
