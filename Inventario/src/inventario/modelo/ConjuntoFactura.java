//PROYECTO 2. PROGRAMACIÓN 3.
//ENIBETH SÁNCHEZ CHÁVEZ 402310886.
//LUIS JOSÉ BRAVO ZÚÑIGA 402380339.
//NAOMI ROJAS HERNÁNDEZ  116920756.

package inventario.modelo;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoFactura {
    
    private final List<Factura> contenedorFacturas;
    
    public ConjuntoFactura() {
        this.contenedorFacturas = new ArrayList<>();
    }
    
    public void agregar(Factura objeto) {
        objeto.setCodigo(this.contenedorFacturas.size() + 1);
        this.contenedorFacturas.add(objeto);
    }
    
    public Factura getElemento(int i) {
        return (this.contenedorFacturas.get(i));
    }
    
    @Override
    public String toString() {
        StringBuilder l = new StringBuilder();
        for(Factura objeto: contenedorFacturas) {
            l.append(String.format("%s%n",objeto.toString()));
        }
        return l.toString();
    }
} //LLAVE CLASS
