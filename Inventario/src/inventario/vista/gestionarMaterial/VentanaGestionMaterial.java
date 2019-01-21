//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.gestionarMaterial;

import inventario.controlador.Control;
import inventario.vista.tabla.ColumnasTablaMaterial;
import inventario.vista.tabla.TablaMaterial;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.MenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;

public class VentanaGestionMaterial extends JFrame implements Observer {

    private final Control gestorPrincipal;
    private JLabel etiquetaEstado;
    private JTable tablaDatosMaterial;

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
        ajustarTablaMaterial(c);
        ajustarMenuBarra();
    }

    private void ajustarMenuBarra() {
        agregar = new JMenuItem("AGREGAR");
        barra = new JMenu("Opciones adicionales");
        barra.add(agregar);
        barraOpciones = new JMenuBar();
        barraOpciones.add(barra);
        setJMenuBar(barraOpciones);
    }

    private void ajustarTablaMaterial(Container c) {
        c.add(BorderLayout.CENTER,
                new JScrollPane(
                        tablaDatosMaterial = new JTable(
                                new TablaMaterial(gestorPrincipal),
                                new ColumnasTablaMaterial()),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        tablaDatosMaterial.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    int registro = gestorPrincipal.getElemento(tablaDatosMaterial.getSelectedRow()).getCodigo();
                    new VentanaSeleccionMaterial("OPERACION", gestorPrincipal, registro).init();

                }
            }
        });

        tablaDatosMaterial.setRowHeight(25);
        tablaDatosMaterial.setIntercellSpacing(new Dimension(24, 8));
        tablaDatosMaterial.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        ajustarEncabezadoTablaMaterial();
    }

    private void ajustarEncabezadoTablaMaterial() {
        JTableHeader encabezado = tablaDatosMaterial.getTableHeader();
        encabezado.setForeground(Color.BLUE);
        encabezado.setBackground(Color.BLUE);
    }

    private void ajustarBarraEstado(Container c) {
        JPanel barraEstado = new JPanel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        barraEstado.add(etiquetaEstado = new JLabel());
        c.add(BorderLayout.PAGE_END, barraEstado);
    }

    public VentanaGestionMaterial(String titulo, Control control) throws HeadlessException {
        super(titulo);
        this.gestorPrincipal = control;
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
        mostrarMensaje("ACTUALIZACIÓN DE MODELO");
    }
} //LLAVE CLASS
