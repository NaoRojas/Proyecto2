package inventario.modelo;

import inventario.database.GestorDaoHerramientas;
import inventario.database.GestorDaoMateriales;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

public class Modelo extends Observable {

    private ConjuntoProducto contenedorProductos;
    private final ConjuntoFactura contenedorFacturas;
    //DAO
    private GestorDaoHerramientas daoHerra = GestorDaoHerramientas.getInstancia();
    private GestorDaoMateriales daoMate = GestorDaoMateriales.getInstancia();
    private final String RUTA = "./Ferreteria.xml";
     private int TX;

    
     
    private void setear() {
        try {
            this.contenedorProductos.setInventario(daoHerra.recuperaHerramientas());
            actualizar();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Modelo() {
        this.contenedorProductos = new ConjuntoProducto();
        this.contenedorFacturas = new ConjuntoFactura();
        setear();
        //recuperar();
    }
    
    public void recuperar() {
        try {
            if (this.contenedorProductos.getInventario().isEmpty()) {
                this.contenedorProductos.setInventario(ConjuntoProducto.recuperar(new FileInputStream(RUTA)).getInventario());
                for (Producto p : contenedorProductos.getInventario()) {
                    if (p.getCodigo() >= 2000) {
                        daoHerra.agregarHerramienta((Herramienta) p);
                    } else {
                        if (p.getCodigo() < 2000) {
                            daoMate.agregarMaterial((Material) p);
                        }
                    }
                }
            } else {
                //this.daoHerra.eliminarTodos();
                //this.daoMate.eliminarTodos();
                //List<Producto> li = ConjuntoProducto.recuperar(new FileInputStream(RUTA)).getInventario();
                //this.contenedorProductos.setInventario(li);
                for (Producto p : contenedorProductos.getInventario()) {
                    if (p.getCodigo() < 2000) {
                        daoMate.eliminar(p.getCodigo());
                    } else {
                        if (p.getCodigo() >= 2000) {
                            daoHerra.eliminar(p.getCodigo());
                        }
                    }
                }
                this.contenedorProductos = new ConjuntoProducto();
                //this.contenedorProductos.setInventario(ConjuntoProducto.recuperar(new FileInputStream(RUTA)).getInventario());
                this.contenedorProductos = ConjuntoProducto.recuperar(new FileInputStream(RUTA));
                for (Producto p : contenedorProductos.getInventario()) {
                    if (p.getCodigo() < 2000) {
                        daoMate.agregarMaterial((Material) p);
                    } else {
                        if (p.getCodigo() >= 2000) {
                            daoHerra.agregarHerramienta((Herramienta) p);
                        }
                    }
                }

            }
            actualizar();

        } catch (FileNotFoundException | JAXBException | SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregar(Producto objeto) {
        try {
            this.contenedorProductos.agregar(objeto);
            if (objeto.getCodigo() >= 2000) {
                this.daoHerra.agregarHerramienta((Herramienta) objeto);
            } else {
                this.daoMate.agregarMaterial((Material) objeto);
            }
            actualizar();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Producto getElemento(int indice) {
        return (this.contenedorProductos.getElemento(indice));
    }

    public Producto buscarElementoCodigo(int codigo) throws Exception {
        if (codigo >= 2000) {
            return this.daoHerra.buscaPorCodigo(codigo);
        }
        if (codigo < 2000) {
            return this.daoMate.buscaPorCodigo(codigo);
        }
        return null;
    }

    public Material getMaterialPosicion(int indice) {
        return (Material) this.contenedorProductos.getElemento(indice);
    }

    public void setElementoMaterial(int codigo, Material objeto) {
        //this.contenedorProductos.setElementoMaterial(codigo, objeto);
        
        if(codigo < 2000){
            try {
                this.daoMate.modificaMaterial(codigo, objeto);
                setear();
            } catch (InstantiationException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException | SQLException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        actualizar();
    }

    public void setElementoHerramienta(int codigo, Herramienta objeto) {
        //this.contenedorProductos.setElementoHerramienta(codigo, objeto);
        
        if(codigo>= 2000){
            try {
                this.daoHerra.modificaHerramienta(codigo, objeto);
                setear();
            } catch (InstantiationException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException | SQLException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        actualizar();
    }

    public void eliminar(int codigo) {
        if (codigo >= 2000) {
            try {
                this.daoHerra.eliminar(codigo);
                setear();
                actualizar();
            } catch (InstantiationException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException | SQLException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (codigo < 2000) {
                try {
                    this.daoMate.eliminar(codigo);
                    setear();
                    actualizar();
                } catch (InstantiationException ex) {
                    Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException | SQLException ex) {
                    Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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

    public int getTX() {
        return TX;
    }
    public void setTX(int i) {
        this.TX=i;
    notifyObservers();
    setChanged();
    }

    
} //LLAVE CLASS
