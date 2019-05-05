package espacial.tableros;

import espacial.Casillero;
import espacial.Coordenada;
import espacial.Direccion;
import espacial.Tablero;

public abstract class CasilleroDelTablero implements Casillero {

    protected final Tablero tablero;
    private final Coordenada coordenada;
    
    public CasilleroDelTablero(Tablero contenedor, int fila, int columna) {
        
        tablero = contenedor;
        coordenada = Coordenada.con(fila, columna);
    }
    
    /**
     * @return Tablero al que pertenece el Casillero
     */
    public Tablero obtenerTablero() {

        return tablero;
    }

    @Override
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {

        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenada));
    }
}
