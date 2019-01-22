package inventario.vista.tabla;

import inventario.controlador.Control;
import inventario.modelo.Material;
import javax.swing.table.AbstractTableModel;

public class TablaHerramienta extends AbstractTableModel {

    private final Control gestorPrincipal;

    public TablaHerramienta(Control control) {
        this.gestorPrincipal = control;
        actualizar();
    }

    public final void actualizar() {
        /*
        try {
            this.gestor = gestor.listar();
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            this.gestor = null;
            System.err.println(ex.getMessage());
        }
         */
    }

    @Override
    public int getRowCount() {
        return (gestorPrincipal.obtenerCantidadHerramienta());
    }

    @Override
    public int getColumnCount() {
        return (Material.ATRIBUTOS.length);
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int indiceFila, int indiceColumna) {
        String[] d = gestorPrincipal.getHerramienta(indiceFila).obtenerArregloDatos();
        return d[indiceColumna];
    }

}
