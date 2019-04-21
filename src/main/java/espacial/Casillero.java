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

    /**
     * pre : el Casillero no está ocupado. 'pieza' no está 
     *       colocada en otro Casillero.
     *       
     * @param pieza
     */
    public void colocar(Pieza pieza) {
        
        this.pieza = pieza;
        this.pieza.posicionar(coordenada);
    }

    public void moverPiezaA(Casillero destino) {

        Pieza piezaMovida = this.pieza;
        desocupar();
        destino.colocar(piezaMovida);
    }

    public void desocupar() {
        
        this.pieza = null;
    }

}
