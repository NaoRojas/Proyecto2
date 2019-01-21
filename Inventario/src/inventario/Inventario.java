//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario;

import inventario.modelo.ConjuntoProducto;
import inventario.modelo.Herramienta;
import inventario.modelo.Material;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Inventario {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | UnsupportedLookAndFeelException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            mostrarInterfaz();
        });
    }

    private static void cargarHerramienta(ConjuntoProducto CH) {
        CH.agregar(new Herramienta("Martillo", "Pesado", 3400, 15));
        
        /*
        CH.agregar(new Herramienta("Liviano", "Llave"));
        CH.agregar(new Herramienta("Mediano", "Destornillador"));
        CH.agregar(new Herramienta("Liviano", "Llave"));
        CH.agregar(new Herramienta("Pesado", "Martillo"));
        CH.agregar(new Herramienta("Pesado", "Taladro"));
        */
    }

    private static void cargarMaterial(ConjuntoProducto CM) {
        CM.agregar(new Material("Tornillo", 5.666, 1.222, 150, 40));
        CM.agregar(new Material("Clavo", 8.1, 2.5, 175, 80));
        CM.agregar(new Material("Tornillo", 2.5, 3.1, 120, 160));
        
    }

    public static void mostrarInterfaz() {
        
        ConjuntoProducto CH = new ConjuntoProducto();
        cargarHerramienta(CH);

        ConjuntoProducto CM = new ConjuntoProducto();
        cargarMaterial(CM);
        System.out.println(CH);
        
        //new VentanaPrincipal("INVENTARIO", new ControlHerramienta(CH), new ControlMaterial(CM)).init();
    }

} //LLAVE CLASS
