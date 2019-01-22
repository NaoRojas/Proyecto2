package inventario.vista.tabla;

import inventario.modelo.Material;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ColumnasTablaMaterial extends DefaultTableColumnModel {

    private static final int[] ANCHO = {50, 100, 80, 70, 70, 70};
    
    private void inicializar() {
        int n = 0;
        for(String objeto: Material.ATRIBUTOS) {
            TableColumn columna = new TableColumn();
            columna.setModelIndex(n);
            columna.setHeaderValue(objeto);
            columna.setPreferredWidth(ANCHO[n++]);
            addColumn(columna);
        }
    }
    
    public ColumnasTablaMaterial() {
        inicializar();
    }  
} //LLAVE CLASS
