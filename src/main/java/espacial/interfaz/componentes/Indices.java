package espacial.interfaz.componentes;

import espacial.Coordenadas;
import espacial.Tablero;

public class Indices {

    private final Tablero tablero;
    private final Coordenadas coordenadas;

    public Indices(Coordenadas deCoordenadas, Tablero enTablero) {

        tablero = enTablero;
        coordenadas = deCoordenadas;
    }

    public static Indices para(Coordenadas coordenadas, Tablero enTablero) {

        return new Indices(coordenadas, enTablero);
    }

    public int deFila() {

        return tablero.contarFilas() - (coordenadas.obtenerFila() - tablero.obtenerFilaMinima() + 1);
    }

    public int deColumna() {

        return coordenadas.obtenerColumna() - tablero.obtenerColumnaMinima();
    }
}
