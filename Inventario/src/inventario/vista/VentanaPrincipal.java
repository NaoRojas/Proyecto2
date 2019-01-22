//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista;

import inventario.vista.factura.VentanaFactura;
import inventario.controlador.Control;
import inventario.vista.consultar.VentanaConsultarMaterial;
import inventario.vista.consultar.VentanaConsultarHerramienta;
import inventario.vista.gestionar.VentanaGestionHerramienta;
import inventario.vista.gestionar.VentanaGestionMaterial;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.BevelBorder;

public class VentanaPrincipal extends JFrame implements Observer {

    private final Control gestorPrincipal;
    private JLabel etiquetaEstado;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaHerramienta;
    private JLabel etiquetaMaterial;
    private JLabel etiquetaEspacio;
    private JButton botonGestionarHerramienta;
    private JButton botonConsultarHerramienta;
    private JButton botonGestionarMaterial;
    private JButton botonConsultarMaterial;
    private JButton botonFacturas;

    private void inicializar() {
        ajustarComponentes(getContentPane());

        setResizable(false);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        ajustarBarraEstado(c);
        ajustarPanelEncabezado(c);
        ajustarPanelBotones(c);
    }

    private void ajustarPanelBotones(Container c) {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        //AJUSTAR BOTONES

        //HERRAMIENTAS
        panelCentral.add(etiquetaHerramienta = new JLabel("HERRAMIENTAS: "));
        panelCentral.add(botonGestionarHerramienta = new JButton("GESTIONAR"));
        panelCentral.add(botonConsultarHerramienta = new JButton("CONSULTAR"));

        botonGestionarHerramienta.addActionListener((ae) -> {
            ventanaGestionarHerramienta();
        });

        botonConsultarHerramienta.addActionListener((ae) -> {
            ventanaConsultarHerramienta();
        });

        //MATERIALES
        panelCentral.add(etiquetaMaterial = new JLabel("MATERIALES:      "));
        panelCentral.add(botonGestionarMaterial = new JButton("GESTIONAR"));
        panelCentral.add(botonConsultarMaterial = new JButton("CONSULTAR"));

        botonGestionarMaterial.addActionListener((ae) -> {
            ventanaGestionarMaterial();
        });

        botonConsultarMaterial.addActionListener((ae) -> {
            ventanaConsultarMaterial();
        });
        
        panelCentral.add(etiquetaEspacio = new JLabel("ADICIONAL:        "));
        panelCentral.add(botonFacturas = new JButton("FACTURAS"));
        
        botonFacturas.addActionListener((ae) -> {
            ventanaFactura();
        });

        c.add(BorderLayout.CENTER, panelCentral);
    }
    
    private void ventanaConsultarHerramienta() {
        new VentanaConsultarHerramienta("CONSULTA HERRAMIENTA", gestorPrincipal).init();
        mostrarMensaje("CONSULTANDO INFORMACIÓN HERRAMIENTAS.");
    }

    private void ventanaGestionarHerramienta() {
        new VentanaGestionHerramienta("GESTIONAR HERRAMIENTA", gestorPrincipal).init();
        mostrarMensaje("GESTIONANDO INFORMACIÓN HERRAMIENTA");
    }

    private void ventanaConsultarMaterial() {
        new VentanaConsultarMaterial("CONSULTA MATERIAL", gestorPrincipal).init();
        mostrarMensaje("CONSULTANDO INFORMACIÓN MATERIAL.");
    }

    private void ventanaGestionarMaterial() {
        new VentanaGestionMaterial("GESTIONAR MATERIAL", gestorPrincipal).init();
        mostrarMensaje("GESTIONANDO INFORMACIÓN MATERIAL");
    }
    
    private void ventanaFactura() {
        new VentanaFactura("FACTURACIÓN", gestorPrincipal).init();
        mostrarMensaje("FACTURANDO");
    }

    private void ajustarPanelEncabezado(Container c) {
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout(FlowLayout.CENTER));
        //panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panelEncabezado.setBackground(Color.DARK_GRAY);
        panelEncabezado.add(etiquetaTitulo = new JLabel("GESTIÓN DE INVENTARIO"));
        etiquetaTitulo.setForeground(Color.WHITE);
        c.add(BorderLayout.PAGE_START, panelEncabezado);
    }

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.RIGHT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    private void mostrarMensaje(String mensaje) {
        etiquetaEstado.setText(String.format("ESTADO: %s", mensaje));
    }

    public VentanaPrincipal(String titulo, Control control) throws HeadlessException {
        super(titulo);
        this.gestorPrincipal = control;
        inicializar();
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

} //LLAVE CLASS

