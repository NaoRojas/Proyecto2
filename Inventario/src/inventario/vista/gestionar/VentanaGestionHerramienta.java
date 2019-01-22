//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.gestionar;

import inventario.controlador.Control;
import inventario.vista.gestionar.agregar.VentanaAgregarHerramienta;
import inventario.vista.tabla.ColumnasTablaHerramienta;
import inventario.vista.tabla.TablaHerramienta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class VentanaGestionHerramienta extends VentanaGestionar {

    @Override
    public void ajustarTabla(Container c) {
        c.add(BorderLayout.CENTER,
                new JScrollPane(
                        tablaDatos = new JTable(
                                new TablaHerramienta(gestorPrincipal),
                                new ColumnasTablaHerramienta()),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        tablaDatos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {

                    int codigo = gestorPrincipal.getHerramienta(tablaDatos.getSelectedRow()).getCodigo();
                    new VentanaSeleccionHerramienta("OPERACION", gestorPrincipal, codigo).init();

                }
            }
        });

        tablaDatos.setRowHeight(25);
        tablaDatos.setIntercellSpacing(new Dimension(24, 8));
        tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        ajustarEncabezadoTabla();
    }
    
    @Override
    public void ajustarEncabezadoTabla() {
        JTableHeader encabezado = tablaDatos.getTableHeader();
        encabezado.setForeground(Color.BLUE);
        encabezado.setBackground(Color.BLUE);
    }

    public VentanaGestionHerramienta(String titulo, Control control) throws HeadlessException {
        super(titulo, control);
    }

    @Override
    public void registrar() {
        new VentanaAgregarHerramienta("REGISTRAR HERRAMIENTA", gestorPrincipal).init();
        this.mostrarMensaje("REGISTRANDO HERRAMIENTA");
    }

} //LLAVE CLASS
