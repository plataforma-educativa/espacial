package espacial;

/**
 * El Tablero mantiene el ordenamiento relativo de las Piezas en una Partida.
 * Cada Casillero está ocupado exclusivamente por una Pieza.
 *
 * @author Mariano Tugnarelli
 */
public interface Tablero {

    int contarFilas();

    int contarColumnas();

    int obtenerFilaMinima();

    int obtenerColumnaMinima();

    int obtenerFilaMaxima();

    int obtenerColumnaMaxima();

    Casillero obtenerCasilleroEn(int fila, int columna);

    default Casillero obtenerCasilleroEn(Coordenadas coordenadas) {

        return obtenerCasilleroEn(coordenadas.obtenerFila(), coordenadas.obtenerColumna());
    }

    void conCadaCasillero(Casillero.Consumidor unConsumidor);

    AccionSingular enCasillero(int fila, int columna);

    Accion enCasilleros(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal);

    NaveEspacial crearNave();

    void registrar(Observador unObservador);

    @FunctionalInterface
    interface Observador {

        void fueAgregadaEn(Casillero casillero, Pieza unaPieza);
    }

    interface Accion {

        void crearAsteroide();

        void crearContenedorDeAntimateria();

        void crearContenedorDeMetal();

        void crearContenedorDeCristal();

        void crearAgujeroNegro();

        void crearBaseDesconocida();
    }

    interface AccionSingular extends Accion {

        BaseEspacial crearBase();

        NaveEspacial crearNave();
    }

}
