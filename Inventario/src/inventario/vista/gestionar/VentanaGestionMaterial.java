//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.gestionar;

import inventario.controlador.Control;
import inventario.vista.agregar.VentanaAgregarMaterial;
import inventario.vista.tabla.ColumnasTablaMaterial;
import inventario.vista.tabla.TablaMaterial;
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

public class VentanaGestionMaterial extends VentanaGestionar {

    
    @Override
    public void ajustarTabla(Container c) {
        c.add(BorderLayout.CENTER,
                new JScrollPane(
                        tablaDatos = new JTable(
                                new TablaMaterial(gestorPrincipal),
                                new ColumnasTablaMaterial()),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        tablaDatos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    int codigo = gestorPrincipal.getMaterial(tablaDatos.getSelectedRow()).getCodigo();
                    new VentanaSeleccionMaterial("OPERACION", gestorPrincipal, codigo).init();

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
    
    public VentanaGestionMaterial(String titulo, Control control) throws HeadlessException {
        super(titulo, control);
    }

    @Override
    public void registrar() {
        new VentanaAgregarMaterial("REGISTRAR MATERIAL", gestorPrincipal).init();
        this.mostrarMensaje("REGISTRANDO MATERIAL");
    }
    
} //LLAVE CLASS