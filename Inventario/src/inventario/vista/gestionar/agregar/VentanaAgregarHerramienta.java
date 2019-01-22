package inventario.vista.gestionar.agregar;

import inventario.controlador.Control;
import inventario.modelo.Herramienta;
import inventario.modelo.Producto;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaAgregarHerramienta extends VentanaAgregar {

    private JLabel etiquetaCapacidad;
    private JTextField campoCapacidad;

    private String captarCapacidad() {
        return (campoCapacidad.getText());
    }

    public VentanaAgregarHerramienta(String titulo, Control control) throws HeadlessException {
        super(titulo, control);
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

        panelCentral.add(boton1 = new JButton("REGISTRAR"));

        boton1.addActionListener((ae) -> {
            registrar();
        });

        c.add(BorderLayout.CENTER, panelCentral);
    }

    private void registrar() {
        if ("".equals(captarNombreProducto()) || "".equals(captarCapacidad()) || captarPrecio() == 0.0 || captarCantidad() == 0) {
            mostrarMensaje("FALLO LA AGREGACIÓN. VERIFIQUE LA INFORMACIÓN. INTENTE NUEVAMENTE.");
        } else {
            Herramienta h = new Herramienta(captarNombreProducto(), captarCapacidad(), captarPrecio(), captarCantidad());
            this.gestorPrincipal.agregar(h);
        }
    }
    
} //LLAVE CLASS
