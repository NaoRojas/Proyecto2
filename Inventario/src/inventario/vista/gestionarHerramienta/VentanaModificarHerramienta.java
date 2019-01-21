//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.gestionarHerramienta;

import inventario.controlador.ControlHerramienta;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;

public class VentanaModificarHerramienta extends JFrame implements Observer {

    private final ControlHerramienta gestorPrincipal;
    private JLabel etiquetaEstado;
    private final int codigoSeleccion;

    private JLabel etiquetaNombre;
    private JLabel etiquetaCapacidad;

    private JTextField campoNombre;
    private JTextField campoCapacidad;

    private JButton boton1;

    private void inicializar() {
        ajustarComponentes(getContentPane());

        setResizable(true);
        setSize(600, 200);
        setMaximumSize(new Dimension(700, 250));
        setLocationRelativeTo(null);
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        ajustarBarraEstado(c);
        ajustarPanelCentro(c);
    }

    private void ajustarPanelCentro(Container c) {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentral.add(etiquetaNombre = new JLabel("NOMBRE DEL PRODUCTO: "));
        panelCentral.add(campoNombre = new JTextField(50));
        panelCentral.add(etiquetaCapacidad = new JLabel("CAPACIDAD:             "));
        panelCentral.add(campoCapacidad = new JTextField(20));
        llenarInformacion();

        panelCentral.add(boton1 = new JButton("HACER CAMBIOS"));

        boton1.addActionListener((ae) -> {
            modificarDatos();
        });

        c.add(BorderLayout.CENTER, panelCentral);
    }

    private void llenarInformacion() {
        campoNombre.setText(this.gestorPrincipal.buscar(codigoSeleccion).getNombreProducto());
        campoCapacidad.setText(this.gestorPrincipal.buscar(codigoSeleccion).getCapacidad());
    }

    private String captarNombreProducto() {
        return (campoNombre.getText());
    }

    private String captarCapacidad() {
        return (campoCapacidad.getText());
    }

    private void modificarDatos() {
        if (("".equals(captarNombreProducto()) || "".equals(captarCapacidad()))) {
            mostrarMensaje("FALLO LA MODIFICACIÓN. VERIFIQUE LA INFORMACIÓN. INTENTE NUEVAMENTE.");
        } else {
            this.gestorPrincipal.setElemento(codigoSeleccion, captarCapacidad(), captarNombreProducto());
        }
    }

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    public VentanaModificarHerramienta(String titulo, ControlHerramienta control, int codigo) throws HeadlessException {
        super(titulo);
        this.gestorPrincipal = control;
        this.codigoSeleccion = codigo;
        inicializar();
    }

    public void init() {
        gestorPrincipal.addObs(this);
        setVisible(true);
        mostrarMensaje("VENTANA INICIALIZADA");

    }

    private void mostrarMensaje(String mensaje) {
        etiquetaEstado.setText(String.format("ESTADO: %s", mensaje));
    }

    @Override
    public void update(Observable objeto, Object evento) {
        repaint();
        mostrarMensaje("ACTUALIZACIÓN DE MODELO.");
    }
} //LLAVE CLASS
