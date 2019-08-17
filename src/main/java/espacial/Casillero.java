package espacial;

/**
 * El Tablero del Juego está compuesto por un conjunto de Casilleros. Cada
 * Casillero podrá estar ocupado por una Pieza del Juego.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface Casillero extends Objetivo, Partidario {

    Tablero obtenerTablero();

    Coordenadas obtenerCoordenadas();

    /**
     * @return EspectroEspacial de la Pieza que ocupa el Casillero, o
     *         EspectroEspacial.VACIO en caso de estar desocupado.
     */
    EspectroEspacial escanear();

    /**
     * @param unaSustancia SustanciaEspacial buscada.
     * @return cantidad de unaSustancia encontrada en el Casillero.
     */
    int buscar(SustanciaEspacial unaSustancia);

    /**
     * @post devuelve la Pieza que ocupa el Casillero o null está desocupado.
     * @return
     */
    Pieza obtenerPieza();

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
     * @pre {@code origen} está ocupado.
     * @post si está desocupado toma la Pieza del Casillero {@code origen}, lo desocupa y ocupa el Casillero;
     *       si está ocupado hace chocar la Pieza del Casillero con la Pieza del Casillero {@code origen}.
     *
     * @param origen
     */
    void recibirPiezaDesde(Casillero origen);

    /**
     * @pre el Casillero está ocupado por una Pieza.
     * @post remueve la Pieza del Casillero.
     */
    void desocupar();

    /**
     * @param direccionElegida
     * @return Casillero contiguo en Dirección {@code direccion} al Casillero.
     */
    Casillero obtenerContiguoEn(Direccion direccionElegida);

    void entregar(Carga unaCarga);

    void recibir(Carga una_carga);

    void aceptar(Casillero.Consumidor unConsumidor);

    void aceptar(Visitante unVisitante);

    @FunctionalInterface
    interface Consumidor {

        void aceptar(Casillero casillero, Pieza... piezas);
    }

    interface Visitante {

        default void siEsBorde(Casillero casillero) {

        }

        default void siEsInterior(Casillero casillero) {

        }
    }
}
