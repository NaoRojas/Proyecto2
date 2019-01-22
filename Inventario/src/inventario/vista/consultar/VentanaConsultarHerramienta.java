//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.consultar;

import inventario.controlador.Control;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaConsultarHerramienta extends VentanaConsultar {

    public VentanaConsultarHerramienta(String titulo, Control control) throws HeadlessException {
        super(titulo, control);
    }

    @Override
    public void mostrarInformacion() {
        int codigo = obtenerCodigoConsulta();
        areaTexto1.setText(this.gestorPrincipal.mostrarInformacionElementoEspecifico(codigo, 2));
        this.mostrarMensaje("CONSULTA REALIZADA");
    }

    @Override
    public void ajustarPanelCentro(Container c) {
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCentro.add(areaTexto1 = new JTextArea(this.gestorPrincipal.mostrarModeloHerramienta()));
        panelCentro.setBackground(Color.WHITE);
        c.add(new JScrollPane(panelCentro, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
    }

    @Override
    public void reconsultarInformacion() {
        espacioTexto1.setText("");
        areaTexto1.setText(this.gestorPrincipal.mostrarModeloHerramienta());
        this.mostrarMensaje("REALIZANDO NUEVA CONSULTA");
    }
    
} //LLAVE CLASS

