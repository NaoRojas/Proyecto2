//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.modelo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConjuntoProducto {

    private List<Producto> inventario;
    private int codigoHerramienta;
    private int codigoMaterial;

    private void ordenar() {
        inventario.sort((t, t1) -> {
            return (t.compareTo(t1));
        });
    }

    public ConjuntoProducto() {
        this.codigoHerramienta = 0;
        this.codigoMaterial = 0;
        inventario = new ArrayList<>();
    }

    public void agregar(Producto m) {
        if (m.esMaterial()) {
            m.setCodigo(m.getCodigo() + (codigoMaterial++));//++codigoMaterial
        } else {
            m.setCodigo(m.getCodigo() + (codigoHerramienta++));
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

//    public Material getMaterial(int indice) {
////        int i = indice + obtenerCantidad(2);
////        return (Material) getElemento(i);
//        ordenar();
//        return (Material) getElemento(indice);
//    }
//
//    public Herramienta getHerramienta(int indice) {
//        int i = indice + obtenerCantidad(1);
//        return (Herramienta) getElemento(i);
//    }
    public Material getMaterial(int indice) {
        //int i = indice + obtenerCantidad(1);
        ordenar();
        return (Material) getElemento(indice);
    }

    public Herramienta getHerramienta(int indice) {
        ordenar();
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
                this.ordenar();
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

    void setInventario(List<Producto> r) {
        this.inventario = (ArrayList<Producto>) r;
        setearConsecutivosCodigos();
    }

    private void setearConsecutivosCodigos() {
        ordenar();
        int a = obtenerCantidad(1);
//        this.codigoMaterial = a;
//        this.codigoHerramienta = b;
        int conMate = inventario.get(a - 1).getCodigo() % 1000;
        int conHerra = inventario.get(inventario.size() - 1).getCodigo() % 2000;
        this.codigoMaterial = conMate + 1;
        this.codigoHerramienta = conHerra + 1;
    }

//    public void setearMedioInsertar(ArrayList<Material> lista, ArrayList<Herramienta> lista2) {
//        int n = lista.size();
//        for (int i = 0; i < n; i++) {
//            inventario.add((Producto) lista.get(i)); //INSERTA PRIMERO MATERIALES
//        }
//
//        for (int i = n; i < n + lista2.size(); i++) { //INSERTA, LUEGO, HERRAMIENTAS.
//            inventario.add((Producto) lista2.get(i - n));
//        }
//
//        this.setearConsecutivosCodigos();
//    }
    public int getCodigoHerramienta() {
        return codigoHerramienta;
    }

    //@XmlElement
    public void setCodigoHerramienta(int codigoHerramienta) {
        this.codigoHerramienta = codigoHerramienta;
    }

    public int getCodigoMaterial() {
        return codigoMaterial;
    }

    //@XmlElement
    public void setCodigoMaterial(int codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

//    public void setearMedioInsertar(ArrayList<Material> lista, ArrayList<Herramienta> lista2) {
//        int n = lista.size();
//        for (int i = 0; i < n; i++) {
//            inventario.add((Producto) lista.get(i)); //INSERTA PRIMERO MATERIALES
//        }
//
//        for (int i = n; i < n + lista2.size(); i++) { //INSERTA, LUEGO, HERRAMIENTAS.
//            inventario.add((Producto) lista2.get(i - n));
//        }
//
//        this.setearConsecutivosCodigos();
//    }
    public void guardar(OutputStream salida) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance(this.getClass());
        Marshaller marshaller = contexto.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(this, salida);
    }

    public static ConjuntoProducto recuperar(InputStream entrada) throws JAXBException {
        //throw new UnsupportedOperationException();
        JAXBContext context = JAXBContext.newInstance(ConjuntoProducto.class);
        Unmarshaller mar = context.createUnmarshaller();
        return (ConjuntoProducto) mar.unmarshal(entrada);
    }

    public List<Producto> getInventario() {
        return inventario;
    }

}
