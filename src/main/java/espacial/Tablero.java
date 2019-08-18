package espacial;

import java.util.List;

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

    EnCasilleros enCasilleros(Coordenadas.Lista listaDeCoordenadas);

    void registrar(Observador unObservador);

    @FunctionalInterface
    interface Observador {

        void fueAgregadaEn(Casillero casillero, Pieza unaPieza);
    }

    interface EnCasilleros {

        List<Pieza> colocarAsteroide();

        List<Pieza> colocarContenedorDeAntimateria();

        List<Pieza> colocarContenedorDeMetal();

        List<Pieza> colocarContenedorDeCristal();

        List<Pieza> colocarAgujeroNegro();

        List<Pieza> colocarBaseDesconocida();

    }

    interface EnCasillero extends EnCasilleros {

        BaseEspacial colocarBase();

        BaseEspacial colocarBaseRival();

        NaveEspacial colocarNave();

        NaveEspacial colocarNaveRival();
    }

    interface EnBase {

        NaveEspacial amarrarNave();

        NaveEspacial amarrarNaveRival();
    }
}
