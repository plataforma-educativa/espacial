package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Tablero;

public class Indices {

    private final Tablero tablero;
    private final Coordenadas coordenadas;

    private Indices(Casillero casillero) {

        tablero = casillero.obtenerTablero();
        coordenadas = casillero.obtenerCoordenadas();
    }

    public static Indices para(Casillero casillero) {

        return new Indices(casillero);
    }

    public int deFila() {

        return tablero.contarFilas() + 1 - (coordenadas.obtenerFila() - tablero.obtenerFilaMinima() + 1);
    }

    public int deColumna() {

        return coordenadas.obtenerColumna() - tablero.obtenerColumnaMinima() + 1;
    }
}
