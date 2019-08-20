import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.excepciones.ErrorEspacial;
import espacial.partidas.PartidaEspacial;
import espacial.tableros.CambiarReferenciaAlSerAgregadaUnaBase;
import espacial.tableros.TableroEspacial;
import espacial.utiles.Referencia;

/**
 * Partida Espacial que le permite al usuario definir y cambiar el Tablero según sus preferencias,
 * tanto sus dimensiones como las Piezas que participan.
 *
 * @author Mariano Tugnarelli
 * @prioridad 9
 */
public class EscenarioEspacial extends PartidaEspacial {

    private Referencia<BaseEspacial> base = Referencia.conValorNulo();

    private EscenarioEspacial(TableroEspacial tablero) {

        super(tablero);
        base.siEsNuloAlObtener(this::lanzarExcepcionPorqueNoExisteUnaBase);
        tablero.registrar(new CambiarReferenciaAlSerAgregadaUnaBase(base));
    }

    /**
     * @param filas    cantidad de filas que tendrá el Tablero.
     * @param columnas cantidad de columanas que tendrá el Tablero.
     * @pre filas y columnas es un valor mayor a 0.
     * @post Partida Espacial iniciada con el Tablero vacío con Casilleros en el rango [1..filas][1..columnas].
     */
    public EscenarioEspacial(int filas, int columnas) {

        this(new TableroEspacial(filas, columnas));
    }

    /**
     * @param filaDesde    coordenada de fila mínima que tendrá el Tablero.
     * @param filaHasta    coordenada de fila máxima que tendrá el Tablero.
     * @param columnaDesde coordenada de columna mínima que tendrá el Tablero.
     * @param columnaHasta coordenada de columan máxima que tendrá el Tablero.
     * @pre filaDesde es menor o igual a filaHasta y columnaDesde es menor o igual a columnaHasta.
     * @post Partida Espacial iniciada con el Tablero vacío con Casilleros en el rango
     * [filaDesde..filaHasta][columnaDesde..columnaHasta].
     */
    public EscenarioEspacial(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        this(new TableroEspacial(filaDesde, filaHasta, columnaDesde, columnaHasta));
    }

    private BaseEspacial lanzarExcepcionPorqueNoExisteUnaBase() {

        throw new ErrorEspacial("No existe ninguna BASE en el EscenarioEspacial");
    }

    /**
     * @return nombre de la Partida Espacial.
     * @post devuelve el nombre que identifica el juego.
     */
    @Override
    public String obtenerNombre() {

        return "Escenario Espacial";
    }

    @Override
    protected NaveEspacial crearNave() {

        return tablero.enBase(base.obtener()).amarrarNave();
    }

    /**
     * @return número de fila mínima.
     * @post coordenada de fila mínima de los Casilleros del Tablero.
     */
    public int obtenerFilaMinima() {

        return tablero.obtenerFilaMinima();
    }

    /**
     * @return número de fila máxima.
     * @post coordenada de fila máxima de los Casilleros del Tablero.
     */
    public int obtenerFilaMaxima() {

        return tablero.obtenerFilaMaxima();
    }

    /**
     * @return número de columna mínima.
     * @post coordenada de columna mínima de los Casilleros del Tablero.
     */
    public int obtenerColumnaMinima() {

        return tablero.obtenerColumnaMinima();
    }

    /**
     * @return número de columna máxima.
     * @post coordenada de columna máxima de los Casilleros del Tablero.
     */
    public int obtenerColumnaMaxima() {

        return tablero.obtenerColumnaMaxima();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca una Base en el Casillero indicado del Tablero. Las próximas Naves creadas
     * despegarán de esa Base.
     */
    public void colocarBaseEn(int fila, int columna) {

        base.cambiar(tablero.enCasillero(fila, columna).colocarBase());
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca un Asteroide en el Casillero indicado del Tablero.
     */
    public void colocarAsteroideEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarAsteroide();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca un Contenedor con ANTIMATERIA en el Casillero indicado del Tablero.
     */
    public void colocarContenedorDeAntimateriaEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarContenedorDeAntimateria();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca un Contenedor con CRISTAL en el Casillero indicado del Tablero.
     */
    public void colocarContenedorDeCristalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarContenedorDeCristal();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca un Contenedor con METAL en el Casillero indicado del Tablero.
     */
    public void colocarContenedorDeMetalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarContenedorDeMetal();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca un Agujero Negro en el Casillero indicado del Tablero.
     */
    public void colocarAgujeroNegroEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarAgujeroNegro();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca una Base Desconocida en el Casillero indicado del Tablero.
     */
    public void colocarBaseDesconocidaEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarBaseDesconocida();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca una Base Rival en el Casillero indicado del Tablero.
     */
    public void colocarBaseRivalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarBaseRival();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca una Nave en el Casillero indicado del Tablero.
     */
    public void colocarNaveEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarNave();
    }

    /**
     * @param fila    coordenada vertical del Casillero en el Tablero.
     * @param columna coordenada horizontal del Casillero en el Tablero.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca una Nave Rival en el Casillero indicado del Tablero.
     */
    public void colocarNaveRivalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarNaveRival();
    }

    /**
     * @param fila     coordenada vertical del Casillero en el Tablero.
     * @param columna  coordenada horizontal del Casillero en el Tablero.
     * @param espacial tipo de Pieza a colocar.
     * @pre fila y columna identifican las coordenadas un Casillero vacío en el Tablero.
     * @post coloca una Pieza en el Casillero indicado del Tablero.
     */
    public void colocar(Espacial espacial, int fila, int columna) {

        espacial.colocarEn(tablero, fila, columna);
    }
}
