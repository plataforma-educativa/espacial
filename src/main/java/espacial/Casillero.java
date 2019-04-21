package espacial;

import espacial.piezas.Pieza;

/**
 * El Tablero del Juego está compuesto por un conjunto de Casilleros. Cada
 * Casillero podrá estar ocupado por una Pieza del Juego.
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

    /**
     * @return EspectroEspacial de la Pieza que ocupa el Casillero, o
     *         EspectroEspacial.VACIO en caso de estar desocupado.
     */
    public EspectroEspacial escanear() {

        return pieza != null ? pieza.escanear() : EspectroEspacial.VACIO;
    }

    /**
     * @pre el Casillero no está ocupado.
     * @pre {@code unaPieza} no está asociada a otro Casillero.
     * @post {@code unPieza} queda asociada al Casillero actual.
     * 
     * @param unaPieza
     */
    public void ocuparCon(Pieza unaPieza) {

        this.pieza = unaPieza;
        this.pieza.posicionar(coordenada);
    }

    /**
     * @pre el Casillero está ocupado.
     * @pre {@code destino} no está ocupado.
     * @post desocupa el Casillero y utiliza esa Pieza para ocupar el Casillero
     *       {@code destino}.
     * 
     * @param destino
     */
    public void moverPiezaA(Casillero destino) {

        Pieza piezaMovida = this.pieza;
        desocupar();
        destino.ocuparCon(piezaMovida);
    }

    /**
     * @pre el Casillero está ocupado por una Pieza.
     * @post remueve la Pieza del Casillero.
     */
    public void desocupar() {

        this.pieza = null;
    }
}
