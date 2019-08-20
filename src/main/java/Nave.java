import espacial.Coordenadas;
import espacial.Direccion;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;
import espacial.partidas.Participante;

/**
 * Vehículo espacial que puede ser pilotado para explorar el tablero, atacar otras piezas, recolectar y
 * trasladar Sustancias.
 *
 * @author Mariano Tugnarelli
 * @prioridad 2
 */
public class Nave extends Participante {

    private final NaveEspacial pieza;
    private final Radar radar;
    private final Monitor monitor;

    /**
     * @pre fue creada una instancia de Partida Espacial, como  por ejemplo BatallaEspacial,
     * DesiertoEspacial o EscenarioEspacial.
     * @post Nave queda amarrada en la Base lista para despegar.
     */
    public Nave() {

        pieza = crearNaveEspacial();
        radar = new Radar(pieza);
        monitor = new Monitor(pieza);
    }

    /**
     * @param fila    coordenada vertical en el Tablero de la Partida actual.
     * @param columna coordenada horizontal en el Tablero de la Partida actual.
     * @pre fila y columna refieren a un Casillero disponible, no ocupado,
     * en el Tablero de la Partida actual.
     * @post la Nave queda posicionada en el Casillero indicado.
     */
    public Nave(int fila, int columna) {

        pieza = crearNaveEspacial(Coordenadas.con(fila, columna));
        radar = new Radar(pieza);
        monitor = new Monitor(pieza);
    }

    /**
     * @pre la Nave está amarrada en una Base. No existe otra Nave en el Casillero de la Base.
     * @post la Nave queda liberada y posicionada en el Casillero de la Base.
     */
    public void despegar() {

        pieza.despegar();
    }

    /**
     * @pre la Nave despegó y no existe otra Pieza ocupando el Casillero contiguo en dirección NORTE.
     * @post mueve la Nave al Casillero contiguo en dirección NORTE.
     */
    public void avanzarAlNorte() {

        pieza.moverEn(Direccion.NORTE);
    }

    /**
     * @pre la Nave despegó y no existe otra Pieza ocupando el Casillero contiguo en dirección SUR.
     * @post mueve la Nave al Casillero contiguo en dirección SUR.
     */
    public void avanzarAlSur() {

        pieza.moverEn(Direccion.SUR);
    }

    /**
     * @pre la Nave despegó y no existe otra Pieza ocupando el Casillero contiguo en dirección ESTE.
     * @post mueve la Nave al Casillero contiguo en dirección ESTE.
     */
    public void avanzarAlEste() {

        pieza.moverEn(Direccion.ESTE);
    }

    /**
     * @pre la Nave despegó y no existe otra Pieza ocupando el Casillero contiguo en dirección OESTE.
     * @post mueve la Nave al Casillero contiguo en dirección OESTE.
     */
    public void avanzarAlOeste() {

        pieza.moverEn(Direccion.OESTE);
    }

    /**
     * @return porcentaje comprendido entre [0..100]
     * @post nivel de defensa que tiene la Nave. La Nave se destruye cuando llega a 0.
     */
    public int consultarNivelDeEscudos() {

        return pieza.obtenerNivelDeEscudos();
    }

    /**
     * @return Radar asociado a la Nave.
     * @post devuelve el Radar que permite conocer los Casilleros contiguos a la Nave en el Tablero.
     */
    public Radar obtenerRadar() {

        return radar;
    }

    /**
     * @return Monitor asociado a la Nave.
     * @post devuelve el Monitor de la Nave que permite conocer el estado general del la Nave.
     */
    public Monitor obtenerMonitor() {

        return monitor;
    }

    /**
     * @pre existe otra Pieza ocupando el Casillero destinatario del ataque.
     * @post ataca a la Pieza en el Casillero contiguo en dirección NORTE.
     */
    public void atacarAlNorte() {

        pieza.atacarEn(Direccion.NORTE);
    }

    /**
     * @pre existe otra Pieza ocupando el Casillero destinatario del ataque.
     * @post ataca a la Pieza en el Casillero contiguo en dirección SUR.
     */
    public void atacarAlSur() {

        pieza.atacarEn(Direccion.SUR);
    }

    /**
     * @pre existe otra Pieza ocupando el Casillero destinatario del ataque.
     * @post ataca a la Pieza en el Casillero contiguo en dirección ESTE.
     */
    public void atacarAlEste() {

        pieza.atacarEn(Direccion.ESTE);
    }

    /**
     * @pre existe otra Pieza ocupando el Casillero destinatario del ataque.
     * @post ataca a la Pieza en el Casillero contiguo en dirección OESTE.
     */
    public void atacarAlOeste() {

        pieza.atacarEn(Direccion.OESTE);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre existe otra Pieza ocupando el Casillero contiguo al NORTE de la Nave con tanta Sustancia como la cantidad
     * indicada y existe capacidad disponible en la bodega de la Nave para almacenarla.
     * @post traslada la cantidad de sustancia desde el Casillero contiguo en dirección NORTE hasta la Nave.
     */
    public void cargarDesdeNorte(Sustancia sustancia, int cantidad) {

        cargarDesde(Direccion.NORTE, sustancia, cantidad);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre existe otra Pieza ocupando el Casillero contiguo al ESTE de la Nave con tanta Sustancia como la cantidad
     * indicada y existe capacidad disponible en la bodega de la Nave para almacenarla.
     * @post traslada la cantidad de sustancia desde el Casillero contiguo en dirección ESTE hasta la Nave.
     */
    public void cargarDesdeEste(Sustancia sustancia, int cantidad) {

        cargarDesde(Direccion.ESTE, sustancia, cantidad);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre existe otra Pieza ocupando el Casillero contiguo al SUR de la Nave con tanta Sustancia como la cantidad
     * indicada y existe capacidad disponible en la bodega de la Nave para almacenarla.
     * @post traslada la cantidad de sustancia desde el Casillero contiguo en dirección SUR hasta la Nave.
     */
    public void cargarDesdeSur(Sustancia sustancia, int cantidad) {

        cargarDesde(Direccion.SUR, sustancia, cantidad);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre existe otra Pieza ocupando el Casillero contiguo al OESTE de la Nave con tanta Sustancia como la cantidad
     * indicada y existe capacidad disponible en la bodega de la Nave para almacenarla.
     * @post traslada la cantidad de sustancia desde el Casillero contiguo en dirección OESTE hasta la Nave.
     */
    public void cargarDesdeOeste(Sustancia sustancia, int cantidad) {

        cargarDesde(Direccion.OESTE, sustancia, cantidad);
    }

    /**
     * @param direccion origen de la carga.
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre existe otra Pieza ocupando el Casillero contiguo de la Nave con tanta Sustancia como la cantidad
     * indicada y existe capacidad disponible en la bodega de la Nave para almacenarla.
     * @post traslada la cantidad de sustancia desde el Casillero contiguo en dirección indicada hasta la Nave.
     */
    private void cargarDesde(Direccion direccion, Sustancia sustancia, int cantidad) {

        SustanciaEspacial sustanciaEspacial = Traductor.DE_SUSTANCIAS.interpretar(sustancia);

        pieza.cargarDesde(direccion, sustanciaEspacial.por(cantidad));
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre la bodega de la Nave tiene tanta Sustancia como la cantidad indicada y existe otra Pieza ocupando el
     * Casillero contiguo al NORTE de la Nave con capacidad disponible para almacenarla.
     * @post traslada la cantidad de sustancia desde la Nave hasta la Pieza en el Casillero contiguo en dirección NORTE.
     */
    public void descargarEnNorte(Sustancia sustancia, int cantidad) {

        descargarEn(Direccion.NORTE, sustancia, cantidad);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre la bodega de la Nave tiene tanta Sustancia como la cantidad indicada y existe otra Pieza ocupando el
     * Casillero contiguo al SUR de la Nave con capacidad disponible para almacenarla.
     * @post traslada la cantidad de sustancia desde la Nave hasta la Pieza en el Casillero contiguo en dirección SUR.
     */
    public void descargarEnSur(Sustancia sustancia, int cantidad) {

        descargarEn(Direccion.SUR, sustancia, cantidad);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre la bodega de la Nave tiene tanta Sustancia como la cantidad indicada y existe otra Pieza ocupando el
     * Casillero contiguo al ESTE de la Nave con capacidad disponible para almacenarla.
     * @post traslada la cantidad de sustancia desde la Nave hasta la Pieza en el Casillero contiguo en dirección ESTE.
     */
    public void descargarEnEste(Sustancia sustancia, int cantidad) {

        descargarEn(Direccion.ESTE, sustancia, cantidad);
    }

    /**
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre la bodega de la Nave tiene tanta Sustancia como la cantidad indicada y existe otra Pieza ocupando el
     * Casillero contiguo al OESTE de la Nave con capacidad disponible para almacenarla.
     * @post traslada la cantidad de sustancia desde la Nave hasta la Pieza en el Casillero contiguo en dirección OESTE.
     */
    public void descargarEnOeste(Sustancia sustancia, int cantidad) {

        descargarEn(Direccion.OESTE, sustancia, cantidad);
    }

    /**
     * @param direccion destino de la carga.
     * @param sustancia tipifica la carga.
     * @param cantidad  cuantifica la carga.
     * @pre la bodega de la Nave tiene tanta Sustancia como la cantidad indicada y existe otra Pieza ocupando el
     * Casillero contiguo en dirección de la Nave con capacidad disponible para almacenarla.
     * @post traslada la cantidad de sustancia desde la Nave hasta la Pieza en el Casillero contiguo en dirección.
     */
    private void descargarEn(Direccion direccion, Sustancia sustancia, int cantidad) {

        SustanciaEspacial sustanciaEspacial = Traductor.DE_SUSTANCIAS.interpretar(sustancia);

        pieza.descargarEn(direccion, sustanciaEspacial.por(cantidad));
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Nave " + pieza.nombrar() + " a la espera de comandos";
    }
}
