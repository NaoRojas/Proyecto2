//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.consultar;

import inventario.controlador.Control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;

public abstract class VentanaConsultar extends JFrame implements Observer {

    protected final Control gestorPrincipal;
    protected JLabel etiquetaEstado;

    protected JLabel etiqueta1;
    protected JTextField espacioTexto1;
    protected JButton botonConsultar;
    protected JButton botonRetornar;
    protected JButton botonSalir;
    protected JTextArea areaTexto1;

    private void inicializar() {
        ajustarComponentes(getContentPane());

        setResizable(true);
        setSize(800, 400);
        setMaximumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        ajustarBarraEstado(c);
        ajustarPanelCentro(c);
        ajustarPanelEncabezado(c);
    }

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    private void ajustarPanelEncabezado(Container c) {
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setBackground(new Color(172, 172, 172));
        panelEncabezado.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelEncabezado.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelEncabezado.add(etiqueta1 = new JLabel("Digite el código a consultar: "));
        panelEncabezado.add(espacioTexto1 = new JTextField(5));
        panelEncabezado.add(botonConsultar = new JButton("CONSULTAR"));
        panelEncabezado.add(botonRetornar = new JButton("RECONSULTAR"));
        panelEncabezado.add(botonSalir = new JButton("SALIR"));

        botonConsultar.addActionListener((ae) -> {
            mostrarInformacion();
        });

        botonRetornar.addActionListener((ae) -> {
            reconsultarInformacion();
        });

        botonSalir.addActionListener((ae) -> {
            cerrarVentana();
        });

        c.add(BorderLayout.PAGE_START, panelEncabezado);
    }

    private void cerrarVentana() {
        this.dispose();
        mostrarMensaje("FINALIZADA LA CONSULTA.");
    }

    public VentanaConsultar(String titulo, Control control) {
        super(titulo);
        gestorPrincipal = control;
        inicializar();
    }

    public void mostrarMensaje(String mensaje) {
        etiquetaEstado.setText(String.format("ESTADO: %s", mensaje));
    }

    public int obtenerCodigoConsulta() {
        int dato = 0;
        try {
            dato = Integer.parseInt(espacioTexto1.getText());
            return dato;
        } catch (NumberFormatException exception) {
            this.mostrarMensaje("FALLO LA CONSULTA. VERIFIQUE SU INFORMACIÓN. INTENTE NUEVAMENTE.");
        }

        return dato;
    }

    public void init() {
        gestorPrincipal.addObs(this);
        setVisible(true);
        mostrarMensaje("VENTANA INICIALIZADA");

    }

    @Override
    public void update(Observable objeto, Object evento) {
        repaint();
        mostrarMensaje("ACTUALIZACIÓN DE MODELO.");
    }
    
    public abstract void mostrarInformacion();

    public abstract void reconsultarInformacion();

    public abstract void ajustarPanelCentro(Container c);

} //LLAVE CLASS
