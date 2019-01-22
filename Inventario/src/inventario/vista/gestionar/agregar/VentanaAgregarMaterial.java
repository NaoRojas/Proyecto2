package inventario.vista.gestionar.agregar;

import inventario.controlador.Control;
import inventario.modelo.Herramienta;
import inventario.modelo.Material;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaAgregarMaterial extends VentanaAgregar {

    private JLabel etiquetaTamaño;
    private JTextField campoTamaño;
    private JLabel etiquetaMedida;
    private JTextField campoMedida;

    private double captarTamaño() {
        double dato = 0.0;
        try {
            dato = Double.parseDouble(campoTamaño.getText());
            return dato;
        } catch (NumberFormatException exception) {
            mostrarMensaje("FALLÓ LA MODIFICACIÓN. ERROR DE MEDIDA.");
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

    public VentanaAgregarMaterial(String titulo, Control control) throws HeadlessException {
        super(titulo, control);
    }

    @Override
    public void ajustarPanelCentro(Container c) {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentral.add(etiquetaNombre = new JLabel("NOMBRE DEL PRODUCTO: "));
        panelCentral.add(campoNombre = new JTextField(50));
        panelCentral.add(etiquetaTamaño = new JLabel("TAMAÑO (en mm): "));
        panelCentral.add(campoTamaño = new JTextField(10));
        panelCentral.add(etiquetaMedida = new JLabel("                      MEDIDA (en mm): "));
        panelCentral.add(campoMedida = new JTextField(10));
        panelCentral.add(etiquetaPrecio = new JLabel("        PRECIO (por kilogramo): "));
        panelCentral.add(campoPrecio = new JTextField(10));
        panelCentral.add(etiquetaCantidad = new JLabel("        CANTIDAD (en kilogramos): "));
        panelCentral.add(campoCantidad = new JTextField(10));

        panelCentral.add(boton1 = new JButton("REGISTRAR"));

        boton1.addActionListener((ae) -> {
            registrar();
        });

        c.add(BorderLayout.CENTER, panelCentral);
    }

    private void registrar() {
        if (("".equals(captarNombreProducto()) || captarMedida() == 0.0) || captarTamaño() == 0.0
                || (captarPrecio() == 0.0) || (captarCantidad() == 0.0)) {
            mostrarMensaje("FALLO LA AGREGACIÓN. VERIFIQUE LA INFORMACIÓN. INTENTE NUEVAMENTE.");
        } else {
            Material m = new Material(captarNombreProducto(), captarTamaño(), captarMedida(), captarPrecio(), captarCantidad());
            this.gestorPrincipal.agregar(m);
        }
    }

} //LLAVE CLASS
