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

    EnCasillero enCasillero(int fila, int columna);

    EnBase enBase(BaseEspacial base);

    EnCasilleros enCasilleros(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal);

    void registrar(Observador unObservador);

    @FunctionalInterface
    interface Observador {

        void fueAgregadaEn(Casillero casillero, Pieza unaPieza);
    }

    interface EnCasilleros {

        void colocarAsteroide();

        void colocarContenedorDeAntimateria();

        void colocarContenedorDeMetal();

        void colocarContenedorDeCristal();

        void colocarAgujeroNegro();

        void colocarBaseDesconocida();
    }

    interface EnCasillero extends EnCasilleros {

        BaseEspacial colocarBase();

        NaveEspacial colocarNave();
    }

    interface EnBase {

        NaveEspacial amarrarNave();
    }
}
