//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.vista.gestionar;

import inventario.controlador.Control;
import inventario.vista.modificar.VentanaModificarHerramienta;
import inventario.vista.gestionar.VentanaSeleccion;

public class VentanaSeleccionHerramienta extends VentanaSeleccion {
    
    VentanaSeleccionHerramienta(String titulo, Control control, int codigo) {
        super(titulo, control, codigo);
    }
    
    @Override
    public void modificar(int codigo) {
        new VentanaModificarHerramienta("MODIFICACIÓN", gestorPrincipal, codigo).init();
    }
}