//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.
package inventario.vista.modificar;

import inventario.controlador.Control;
import inventario.modelo.Material;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaModificarMaterial extends VentanaModificar {

    private JLabel etiquetaMedida;
    private JLabel etiquetaTamaño;

    private JTextField campoMedida;
    private JTextField campoTamaño;

    public VentanaModificarMaterial(String titulo, Control control, int codigo) throws HeadlessException {
        super(titulo, control, codigo);
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
        llenarInformacion();

        panelCentral.add(boton1 = new JButton("HACER CAMBIOS"));

        boton1.addActionListener((ae) -> {
            modificarDatos();
        });

        c.add(BorderLayout.CENTER, panelCentral);
    }

    @Override
    public void llenarInformacion() {
        Material m = (Material) this.gestorPrincipal.buscarElementoCodigo(codigoSeleccion);
        campoNombre.setText(m.getNombreProducto());
        campoTamaño.setText(String.format("%4.2f", m.getTamanio()));
        campoMedida.setText(String.format("%4.2f", m.getMedida()));
        campoPrecio.setText(String.format("%4.2f", m.getPrecio()));
        campoCantidad.setText(String.format("%d", m.getCantidad()));
    }

    @Override
    public void modificarDatos() {
        if (("".equals(captarNombreProducto()) || captarMedida() == 0.0) || captarTamaño() == 0.0
                || (captarPrecio() == 0.0) || (captarCantidad() == 0.0)) {
            mostrarMensaje("FALLO LA MODIFICACIÓN. VERIFIQUE LA INFORMACIÓN. INTENTE NUEVAMENTE.");
        } else {
            Material m = new Material(captarNombreProducto(), captarTamaño(), captarMedida(), captarPrecio(), captarCantidad());
            this.gestorPrincipal.setElementoMaterial(codigoSeleccion, m);
        }
    }

    private double captarTamaño() {
        double dato = 0.0;
        try {
            dato = Double.parseDouble(campoTamaño.getText());
            return dato;
        } catch (NumberFormatException exception) {
            mostrarMensaje("FALLÓ LA MODIFICACIÓN. ERROR DE TAMAÑO.");
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

} //LLAVE CLASS
