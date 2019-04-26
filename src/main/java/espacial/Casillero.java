package espacial;

/**
 * El Tablero del Juego está compuesto por un conjunto de Casilleros. Cada
 * Casillero podrá estar ocupado por una Pieza del Juego.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Casillero {

    /**
     * @return EspectroEspacial de la Pieza que ocupa el Casillero, o
     *         EspectroEspacial.VACIO en caso de estar desocupado.
     */
    EspectroEspacial escanear();
    
    /**
     * @pre el Casillero no está ocupado.
     * @pre {@code unaPieza} no está asociada a otro Casillero.
     * @post {@code unPieza} queda asociada al Casillero actual.
     * 
     * @param unaPieza
     */
    void ocuparCon(Pieza unaPieza);

    /**
     * @pre el Casillero está ocupado.
     * @pre {@code destino} no está ocupado.
     * @post desocupa el Casillero y utiliza esa Pieza para ocupar el Casillero
     *       {@code destino}.
     * 
     * @param destino
     */
    void moverPiezaA(Casillero destino);

    /**
     * @pre el Casillero está ocupado por una Pieza.
     * @post remueve la Pieza del Casillero.
     */
    void desocupar();

    /**
     * @return si una Pieza está asociada a este Casillero.
     */
    boolean estaOcupado();

    /**
     * @return si no hay una Pieza asociada a este Casillero.
     */
    default boolean estaDesocupado() {
        return ! estaOcupado();
    }
    
    /**
     * @param direccionElegida
     * @return Casillero contiguo en Dirección {@code direccion} al Casillero
     */
    Casillero obtenerContiguoEn(Direccion direccionElegida);

    /**
     * @pre el Casillero está ocupado y sufio un choque con {@code piezaMovida}
     * @param piezaMovida
     */
    void chocarPiezaCon(Pieza piezaMovida);
}
