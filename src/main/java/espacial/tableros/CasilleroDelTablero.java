package espacial.tableros;

import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Tablero;

public abstract class CasilleroDelTablero implements Casillero {

    protected final Tablero tablero;
    protected final Coordenadas coordenadas;

    public CasilleroDelTablero(Tablero contenedor, int fila, int columna) {

        tablero = contenedor;
        coordenadas = Coordenadas.con(fila, columna);
    }

    @Override
    public Tablero obtenerTablero() {

        return tablero;
    }

    @Override
    public Coordenadas obtenerCoordenadas() {

        return coordenadas;
    }
}
