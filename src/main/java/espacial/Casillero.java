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

    private Pieza pieza = null;
    
    public EspectroEspacial escanear() {
        
        return pieza != null ? pieza.escanear() : EspectroEspacial.VACIO;
    }

    public void colocar(Pieza pieza) {
        
        this.pieza = pieza;
    }

}
