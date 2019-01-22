//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.modificar;

import inventario.controlador.Control;
import inventario.modelo.Herramienta;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaModificarHerramienta extends VentanaModificar {
    
    private JLabel etiquetaCapacidad;
    private JTextField campoCapacidad;

    public VentanaModificarHerramienta(String titulo, Control control, int codigo) throws HeadlessException {
        super(titulo, control, codigo);
    }
    
    private String captarCapacidad() {
        return campoCapacidad.getText();
    }
    
    @Override
    public void ajustarPanelCentro(Container c) {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentral.add(etiquetaNombre = new JLabel("NOMBRE DEL PRODUCTO: "));
        panelCentral.add(campoNombre = new JTextField(50));
        panelCentral.add(etiquetaCapacidad = new JLabel("CAPACIDAD: "));
        panelCentral.add(campoCapacidad = new JTextField(8));
        panelCentral.add(etiquetaPrecio = new JLabel("  PRECIO (en colones): "));
        panelCentral.add(campoPrecio = new JTextField(8));
        panelCentral.add(etiquetaCantidad = new JLabel("  CANTIDAD (unidades): "));
        panelCentral.add(campoCantidad = new JTextField(8));
        llenarInformacion();

        panelCentral.add(boton1 = new JButton("HACER CAMBIOS"));

        boton1.addActionListener((ae) -> {
            modificarDatos();
        });

        c.add(BorderLayout.CENTER, panelCentral);
    }

    @Override
    public void llenarInformacion() {
        Herramienta h = (Herramienta) this.gestorPrincipal.buscarElementoCodigo(codigoSeleccion);
        campoNombre.setText(h.getNombreProducto());
        campoCapacidad.setText(h.getCapacidad());
        campoPrecio.setText(String.format("%4.2f", h.getPrecio()));
        campoCantidad.setText(String.format("%d", h.getCantidad()));
    }

    @Override
    public void modificarDatos() {
        if ("".equals(captarNombreProducto()) || "".equals(captarCapacidad()) || captarPrecio() == 0.0 || captarCantidad() == 0) {
            mostrarMensaje("FALLO LA MODIFICACIÓN. VERIFIQUE LA INFORMACIÓN. INTENTE NUEVAMENTE.");
        } else {
            Herramienta h = new Herramienta(captarNombreProducto(), captarCapacidad(), captarPrecio(), captarCantidad());
            this.gestorPrincipal.setElementoHerramienta(codigoSeleccion, h);
        }
    } 
} //LLAVE CLASS