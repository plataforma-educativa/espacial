package espacial;

import espacial.piezas.Pieza;

/**
 * El Tablero del Juego está compuesto por un conjunto de Casilleros.
 * Cada Casillero podrá tener a lo sumo una Pieza del Juego.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Casillero {

    private final Coordenada coordenada;
    private Pieza pieza = null;
    
    public Casillero(int fila, int columna) {
        coordenada = new Coordenada(fila, columna);
    }
    
    public EspectroEspacial escanear() {
        
        return pieza != null ? pieza.escanear() : EspectroEspacial.VACIO;
    }

    public void colocar(Pieza pieza) {
        
        this.pieza = pieza;
        if (this.pieza != null) {
            this.pieza.posicionar(coordenada);
        }
    }

}
