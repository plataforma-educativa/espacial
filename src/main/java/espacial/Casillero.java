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
    private Tablero tablero;
    private Pieza pieza = null;

    public Casillero(Tablero contenedor, int fila, int columna) {
        coordenada = Coordenada.en(fila, columna);
        tablero = contenedor;
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

        pieza = unaPieza;
        pieza.posicionar(coordenada);
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

        Pieza piezaMovida = pieza;
        desocupar();
        destino.ocuparCon(piezaMovida);
    }

    /**
     * @pre el Casillero está ocupado por una Pieza.
     * @post remueve la Pieza del Casillero.
     */
    public void desocupar() {

        pieza = null;
    }

    /**
     * @return si una Pieza está asociada a este Casillero.
     */
    public boolean estaOcupado() {
        
        return pieza != null;
    }

    /**
     * @return si no hay una Pieza asociada a este Casillero.
     */
    public boolean estaDesocupado() {

        return ! estaOcupado();
    }

    public Casillero obtenerContiguoAl(Direccion direccion) {
        
        Coordenada contiguo = direccion.trasladar(coordenada);
        
        return tablero.obtenerCasillero(contiguo.obtenerFila(), contiguo.obtenerColumna());
    }
}
