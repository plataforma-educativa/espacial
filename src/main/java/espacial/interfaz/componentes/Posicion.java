package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Tablero;
import espacial.interfaz.componentes.dibujos.DibujoEspacial;

public class Posicion {

    private final Tablero tablero;
    private final Coordenadas coordenadas;

    private Posicion(Casillero casillero) {

        tablero = casillero.obtenerTablero();
        coordenadas = casillero.obtenerCoordenadas();
    }

    public static Posicion de(Casillero casillero) {

        return new Posicion(casillero);
    }

    public int enFila() {

        return tablero.contarFilas() + 1 - (coordenadas.obtenerFila() - tablero.obtenerFilaMinima() + 1);
    }

    public int enColumna() {

        return coordenadas.obtenerColumna() - tablero.obtenerColumnaMinima() + 1;
    }

    public double enX() {

        return DibujoEspacial.DIMENSION * enColumna();
    }

    public double enY() {

        return DibujoEspacial.DIMENSION * enFila();
    }

    @Override
    public String toString() {

        return "Posicion[" + enFila() + "][" + enColumna() + "] -> Casillero" + coordenadas;
    }
}
