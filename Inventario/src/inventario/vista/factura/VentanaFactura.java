//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.factura;

import inventario.controlador.Control;
import inventario.modelo.Factura;
import inventario.modelo.Herramienta;
import inventario.modelo.Material;
import inventario.modelo.Producto;
import inventario.vista.tabla.ColumnasTablaHerramienta;
import inventario.vista.tabla.ColumnasTablaMaterial;
import inventario.vista.tabla.TablaHerramienta;
import inventario.vista.tabla.TablaMaterial;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;

public class VentanaFactura extends JFrame implements Observer {
    
    private final Control gestorPrincipal;
    private JLabel etiquetaEstado;
    
    private JTable tablaMaterial;
    private JTable tablaHerramienta;
    
    private JLabel etiquetaDatos;
    private JLabel etiquetaCantidad;
    private JLabel etiquetaAgregar;
    private JLabel etiquetaFacturar;
    private JLabel etiquetaEspacio1;
    private JLabel etiquetaEspacio2;
    
    private JTextArea espacioTexto;
    private JTextArea espacioTextoFactura;
    private JTextField espacioCantidad;
    
    private JButton botonAgregar;
    private JButton botonEliminar;
    private JButton botonFacturar;
    private JButton botonVer;
    
    private Producto taza;
    private Factura auxiliar;
    
    public VentanaFactura(String titulo, Control gestorPrincipal) {
        super(titulo);
        this.gestorPrincipal = gestorPrincipal;
        inicializar();
        this.taza = null;
        this.auxiliar = new Factura();
    }
    
    private void inicializar() {
        ajustarComponentes(getContentPane());
        
        setResizable(false);
        setSize(1300, 650);
        setLocationRelativeTo(null);
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());
        
        ajustarBarraEstado(c);
        ajustarTablaHerramienta(c);
        ajustarTablaMaterial(c);
        ajustarPanelCentral(c);
        
    }
    
    private void ajustarPanelCentral(Container c) {
        JPanel panelCentroSuperior = new JPanel();
        panelCentroSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentroSuperior.add(espacioTexto = new JTextArea(1, 47));
        espacioTexto.setBackground(Color.YELLOW);
        
        JPanel panelCentroCentro = new JPanel();
        panelCentroCentro.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentroCentro.add(etiquetaCantidad = new JLabel("CANTIDAD:          "));
        panelCentroCentro.add(espacioCantidad = new JTextField(10));
        panelCentroCentro.add(etiquetaEspacio1 = new JLabel("                                     "));
        panelCentroCentro.add(botonAgregar = new JButton("INGRESAR"));
        panelCentroCentro.add(botonEliminar = new JButton("ELIMINAR/DISMINUIR"));
        panelCentroCentro.add(etiquetaDatos = new JLabel("DATOS DE LA FACTURA:                                                                                         "));
        
        JPanel subPanel = new JPanel();
        panelCentroCentro.setLayout(new FlowLayout(FlowLayout.LEFT));
        subPanel.add(espacioTextoFactura = new JTextArea(23, 45));
        panelCentroCentro.add(BorderLayout.CENTER, new JScrollPane(subPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
        botonAgregar.addActionListener((ae) -> {
            agregarProducto(taza, captarCantidad());
        });
        
        botonEliminar.addActionListener((ae) -> {
            eiminaProducto(taza, captarCantidad());
        });
        
        JPanel panelCentroInferior = new JPanel();
        panelCentroInferior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentroInferior.add(etiquetaFacturar = new JLabel("FINALIZAR:       "));
        panelCentroInferior.add(botonFacturar = new JButton("FACTURAR"));
        panelCentroInferior.add(botonVer = new JButton("VER OTRAS FACTURAS"));
        
        botonFacturar.addActionListener((ae) -> {
            finalizar();
        });
        
        botonVer.addActionListener((ae) -> {
            mostrarFacturas();
        });
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(BorderLayout.PAGE_START, panelCentroSuperior);
        panelCentral.add(BorderLayout.CENTER, panelCentroCentro);
        
        panelCentral.add(BorderLayout.PAGE_END, panelCentroInferior);
        
        c.add(BorderLayout.CENTER, panelCentral);
    }
    
    private void ajustarTablaMaterial(Container c) {
        c.add(BorderLayout.EAST,
                new JScrollPane(
                        tablaMaterial = new JTable(
                                new TablaMaterial(gestorPrincipal),
                                new ColumnasTablaMaterial()),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
        tablaMaterial.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    Material m = gestorPrincipal.getMaterial(tablaMaterial.getSelectedRow());
                    setTextoEspacioTexto(m.mostrarDatos());
                    taza = (Producto) m;
                }
            }
        });
        
        tablaMaterial.setRowHeight(25);
        tablaMaterial.setIntercellSpacing(new Dimension(24, 8));
        tablaMaterial.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        ajustarEncabezadoTablaMaterial();
    }
    
    private void ajustarEncabezadoTablaMaterial() {
        JTableHeader encabezado = tablaMaterial.getTableHeader();
        encabezado.setForeground(Color.BLUE);
        encabezado.setBackground(Color.BLUE);
    }
    
    private void ajustarTablaHerramienta(Container c) {
        c.add(BorderLayout.WEST,
                new JScrollPane(
                        tablaHerramienta = new JTable(
                                new TablaHerramienta(gestorPrincipal),
                                new ColumnasTablaHerramienta()),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
        tablaHerramienta.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    Herramienta m = gestorPrincipal.getHerramienta(tablaHerramienta.getSelectedRow());
                    setTextoEspacioTexto(m.mostrarDatos());
                    taza = (Producto) m;
                }
            }
        });
        
        tablaHerramienta.setRowHeight(25);
        tablaHerramienta.setIntercellSpacing(new Dimension(24, 8));
        tablaHerramienta.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        ajustarEncabezadoTablaHerramienta();
    }
    
    private void ajustarEncabezadoTablaHerramienta() {
        JTableHeader encabezado = tablaHerramienta.getTableHeader();
        encabezado.setForeground(Color.BLUE);
        encabezado.setBackground(Color.BLUE);
    }
    
    private void setTextoEspacioTexto(String texto) {
        espacioTexto.setText(texto);
    }
    
    private void finalizar() {
        this.gestorPrincipal.agregarFactura(auxiliar);
        setTextoEspacioFactura("");
        setTextoEspacioTexto("");
        auxiliar = new Factura();
    }
    
    private void mostrarFacturas() {
        auxiliar = new Factura();
        String texto = this.gestorPrincipal.mostrarFacturas();
        setTextoEspacioTexto("");
        setTextoEspacioFactura(texto);
    }
    
    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.RIGHT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }
    
    private int captarCantidad() {
        int dato = 0;
        try {
            dato = Integer.parseInt(espacioCantidad.getText());
            return dato;
        } catch (NumberFormatException exception) {
        }
        return dato;
    }
    
    private void agregarProducto(Producto objeto, int cantidad) {
        if (cantidad != 0 && objeto != null) {
            this.auxiliar.agregarProducto(objeto, cantidad);
            setTextoEspacioFactura(auxiliar.toString());
        } else {
            mostrarMensaje("ERROR EN LA FACTURACIÓN DEL PRODUCTO.");
    
        }
    }
    
    private void eiminaProducto(Producto objeto, int cantidad) {
        if (cantidad == 0) {
            auxiliar.eliminarProducto(objeto);
        } else {
            auxiliar.eliminarCantidad(objeto, cantidad);
        }
        setTextoEspacioFactura(auxiliar.toString());
    }
    
    private void setTextoEspacioFactura(String texto) {
        espacioTextoFactura.setText(texto);
    }
    
    private void mostrarMensaje(String mensaje) {
        etiquetaEstado.setText(String.format("ESTADO: %s", mensaje));
    }
    
    public void init() {
        this.gestorPrincipal.addObs(this);
        //cargar(RUTA_DATOS);
        setVisible(true);
        mostrarMensaje("VENTANA INICIALIZADA");
        
    }
    
    @Override
    public void update(Observable objeto, Object evento) {
        repaint();
        mostrarMensaje("ACTUALIZACIÓN DE MODELO");
    }
} //
