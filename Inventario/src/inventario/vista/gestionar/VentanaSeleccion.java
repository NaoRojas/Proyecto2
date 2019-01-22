//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.gestionar;

import inventario.controlador.Control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public abstract class VentanaSeleccion extends JFrame implements Observer {

    protected final Control gestorPrincipal;
    protected final int codigoSeleccion;
    private JLabel etiquetaTexto1;
    private JButton botonModificar;
    private JButton botonEliminar;

    private void inicializar() {
        ajustarComponentes(getContentPane());

        setResizable(false);
        setSize(300, 100);
        setLocationRelativeTo(null);
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        ajustarBarraMensaje(c);
        ajustarPanelBotones(c);
    }

    private void ajustarPanelBotones(Container c) {
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout((new FlowLayout(FlowLayout.CENTER)));
        panelBotones.add(botonModificar = new JButton("MODIFICAR"));

        panelBotones.add(botonEliminar = new JButton("ELIMINAR"));

        botonEliminar.addActionListener((ae) -> {
            eliminar(codigoSeleccion);
            cerrarVentana();
        });

        botonModificar.addActionListener((ae) -> {
            modificar(codigoSeleccion);
            cerrarVentana();
        });

        c.add(BorderLayout.PAGE_END, panelBotones);
    }
    
    private void eliminar(int codigo) {
        this.gestorPrincipal.eliminar(codigo);
        cerrarVentana();
    }

    private void cerrarVentana() {
        this.gestorPrincipal.removeObs(this);
        this.dispose();
    }

    private void ajustarBarraMensaje(Container c) {
        JPanel barraMensaje = new JPanel();
        barraMensaje.setLayout(new FlowLayout(FlowLayout.CENTER));
        barraMensaje.setBackground(Color.DARK_GRAY);
        barraMensaje.add(etiquetaTexto1 = new JLabel());
        etiquetaTexto1.setForeground(Color.WHITE);
        c.add(BorderLayout.PAGE_START, barraMensaje);
    }

    private void mostrarMensaje(String mensaje) {
        etiquetaTexto1.setText(String.format(" %s", mensaje));
    }

    public VentanaSeleccion(String titulo, Control control, int codigo) throws HeadlessException {
        super(titulo);
        this.gestorPrincipal = control;
        this.codigoSeleccion = codigo;
        inicializar();
    }

    public void init() {
        this.gestorPrincipal.addObs(this);
        setVisible(true);
        mostrarMensaje("Seleccione la operación a realizar:");
    }

    @Override
    public void update(Observable objeto, Object evento) {
        repaint();
    }

    public abstract void modificar(int codigo);
} //LLAVE CLASS

