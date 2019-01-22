//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.modificar;

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

public abstract class VentanaModificar extends JFrame implements Observer {

    private JLabel etiquetaEstado;

    protected final Control gestorPrincipal;
    protected final int codigoSeleccion;

    protected JLabel etiquetaNombre;
    protected JLabel etiquetaPrecio;
    protected JLabel etiquetaCantidad;

    protected JTextField campoNombre;
    protected JTextField campoPrecio;
    protected JTextField campoCantidad;

    protected JButton boton1;

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

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    public VentanaModificar(String titulo, Control control, int codigo) throws HeadlessException {
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

    public String captarNombreProducto() {
        return (campoNombre.getText());
    }

    public double captarPrecio() {
        double dato = 0.0;
        try {
            dato = Double.parseDouble(campoPrecio.getText());
            return dato;
        } catch (NumberFormatException exception) {
            mostrarMensaje("FALLÓ LA MODIFICACIÓN. ERROR DE MEDIDA.");
        }
        return dato;
    }

    public int captarCantidad() {
        int dato = 0;
        try {
            dato = Integer.parseInt(campoCantidad.getText());
            return dato;
        } catch (NumberFormatException exception) {
            mostrarMensaje("FALLÓ LA MODIFICACIÓN. ERROR DE PRECIO.");
        }
        return dato;
    }

    public void mostrarMensaje(String mensaje) {
        etiquetaEstado.setText(String.format("ESTADO: %s", mensaje));
    }

    @Override
    public void update(Observable objeto, Object evento) {
        repaint();
        mostrarMensaje("ACTUALIZACIÓN DE MODELO.");
    }

    public abstract void ajustarPanelCentro(Container c);

    public abstract void llenarInformacion();

    public abstract void modificarDatos();

} //LLAVE CLASS
