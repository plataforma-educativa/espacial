package espacial.tableros;

/**
 * Implementación del Tablero que no tiene ninguna Pieza.
 *
 * @author Mariano Tugnarelli
 */
public class TableroDesierto extends TableroEspacial {

    @Override
    protected void inicializarPiezas() {

    }

    @Override
    public int obtenerFilaMaxima() {

        return 10;
    }

    @Override
    public int obtenerFilaMinima() {

        return -10;
    }

    @Override
    public int obtenerColumnaMaxima() {

        return 10;
    }

    @Override
    public int obtenerColumnaMinima() {

        return -10;
    }
}
