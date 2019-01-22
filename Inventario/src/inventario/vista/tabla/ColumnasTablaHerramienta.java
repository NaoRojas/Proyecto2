package inventario.vista.tabla;

import inventario.modelo.Herramienta;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ColumnasTablaHerramienta extends DefaultTableColumnModel {

    private static final int[] ANCHO = {50, 100, 60, 60, 90};
    
    private void inicializar() {
        int n = 0;
        for(String objeto: Herramienta.ATRIBUTOS) {
            TableColumn columna = new TableColumn();
            columna.setModelIndex(n);
            columna.setHeaderValue(objeto);
            columna.setPreferredWidth(ANCHO[n++]);
            addColumn(columna);
        }
    }
    
    public ColumnasTablaHerramienta() {
        inicializar();
    }  
} //LLAVE CLASS
