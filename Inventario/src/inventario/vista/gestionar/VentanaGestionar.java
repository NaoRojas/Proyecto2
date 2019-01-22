//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.gestionar;

import inventario.controlador.Control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;

public abstract class VentanaGestionar extends JFrame implements Observer {

    protected final Control gestorPrincipal;
    private JLabel etiquetaEstado;
    protected JTable tablaDatos;

    private JMenu barra;
    private JMenuBar barraOpciones;
    private JMenuItem agregar;

    private void inicializar() {
        ajustarComponentes(getContentPane());

        setResizable(true);
        setSize(600, 400);
        setMaximumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        ajustarBarraEstado(c);
        ajustarTabla(c);
        ajustarMenuBarra();
    }

    private void ajustarMenuBarra() {
        agregar = new JMenuItem("Agregar");

        agregar.addActionListener((ae) -> {
            registrar();
        });

        barra = new JMenu("Opciones");
        barra.add(agregar);
        barraOpciones = new JMenuBar();
        barraOpciones.add(barra);
        barraOpciones.setBackground(Color.BLUE);
        setJMenuBar(barraOpciones);
    }

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    public VentanaGestionar(String titulo, Control control) throws HeadlessException {
        super(titulo);
        this.gestorPrincipal = control;
        inicializar();
    }
    
    public void mostrarMensaje(String mensaje) {
        etiquetaEstado.setText(String.format("ESTADO: %s", mensaje));
    }

    public void init() {
        gestorPrincipal.addObs(this);
        setVisible(true);
        mostrarMensaje("VENTANA INICIALIZADA");
    }

    @Override
    public void update(Observable objeto, Object evento) {
        //repaint();
        actualizando();
        mostrarMensaje("ACTUALIZACIÓN DE MODELO");
    }

    public abstract void ajustarTabla(Container c);

    public abstract void ajustarEncabezadoTabla();

    public abstract void registrar();
    
    public abstract void actualizando();

} //LLAVE CLASS
