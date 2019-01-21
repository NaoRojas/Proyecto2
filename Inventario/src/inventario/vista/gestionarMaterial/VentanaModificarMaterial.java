//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.gestionarMaterial;


import inventario.controlador.Control;
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
import javax.swing.border.BevelBorder;

public class VentanaModificarMaterial extends JFrame implements Observer {

    private final Control gestorPrincipal;
    private JLabel etiquetaEstado;
    private final int codigoSeleccion;

    private JLabel etiquetaNombre;
    private JLabel etiquetaMedida;
    private JLabel etiquetaTamaño;
    private JLabel etiquetaEspacio1;

    private JTextField campoNombre;
    private JTextField campoMedida;
    private JTextField campoTamaño;

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
        panelCentral.add(etiquetaTamaño = new JLabel("TAMAÑO (en mm):             "));
        panelCentral.add(campoTamaño = new JTextField(8));
        panelCentral.add(etiquetaEspacio1 = new JLabel("                                                                                                                   "));
        panelCentral.add(etiquetaMedida = new JLabel("MEDIDA (en mm):              "));
        panelCentral.add(campoMedida = new JTextField(8));
        llenarInformacion();
        
        panelCentral.add(boton1 = new JButton("HACER CAMBIOS"));
        
        boton1.addActionListener((ae) -> {
            modificarDatos();
        });
        
        c.add(BorderLayout.CENTER, panelCentral);
    }

    private void llenarInformacion() {
        campoNombre.setText(this.gestorPrincipal.buscar(codigoSeleccion).getNombreProducto());
        campoTamaño.setText(String.format("%4.2f", this.gestorPrincipal.buscar(codigoSeleccion).getTamanio()));
        campoMedida.setText(String.format("%4.2f", this.gestorPrincipal.buscar(codigoSeleccion).getMedida()));
    }

    private String captarNombreProducto() {
        return (campoNombre.getText());
    }

    private double captarTamaño() {
        double dato = 0.0;
        try {
            dato = Double.parseDouble(campoTamaño.getText());
            return dato;
        } catch (NumberFormatException exception) {
            mostrarMensaje("FALLÓ LA MODIFICACIÓN. ERROR DE TAMAÑO.");
        }
        return dato;
    }

    private double captarMedida() {
        double dato = 0.0;
        try {
            dato = Double.parseDouble(campoMedida.getText());
            return dato;
        } catch (NumberFormatException exception) {
            mostrarMensaje("FALLÓ LA MODIFICACIÓN. ERROR DE MEDIDA.");
        }
        return dato;
    }

    private void modificarDatos() {
        if (("".equals(captarNombreProducto()) || captarMedida() == 0.0) || captarTamaño() == 0.0)
            mostrarMensaje("FALLO LA MODIFICACIÓN. VERIFIQUE LA INFORMACIÓN. INTENTE NUEVAMENTE.");
        else
            this.gestorPrincipal.setElemento(codigoSeleccion, captarTamaño(), captarMedida(), captarNombreProducto());
    }

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    public VentanaModificarMaterial(String titulo, Control control, int codigo) throws HeadlessException {
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
