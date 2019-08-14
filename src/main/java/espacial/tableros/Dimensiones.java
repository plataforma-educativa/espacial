package espacial.tableros;

import espacial.excepciones.ParametroInvalido;

public class Dimensiones {

    private final int filaMinima;
    private final int columnaMinima;
    private final int filaMaxima;
    private final int columnaMaxima;

    public Dimensiones(int filas, int columnas) {

        validarDimensiones(filas, columnas);

        filaMinima = 1;
        columnaMinima = 1;
        filaMaxima = filas;
        columnaMaxima = columnas;
    }

    public Dimensiones(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        validarLimites(filaDesde, filaHasta, columnaDesde, columnaHasta);

        filaMinima = filaDesde;
        columnaMinima = columnaDesde;
        filaMaxima = filaHasta;
        columnaMaxima = columnaHasta;
    }

    private void validarLimites(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        if (filaDesde >= filaHasta) {
            throw new ParametroInvalido("El Tablero no puede tener filas desde %d hasta %d", filaDesde, filaHasta);
        }

        if (columnaDesde >= columnaHasta) {
            throw new ParametroInvalido("El Tablero no puede tener columnas desde %d hasta %d", columnaDesde, columnaHasta);
        }
    }

    private void validarDimensiones(int filas, int columnas) {

        if (filas < 1) {
            throw new ParametroInvalido("El Tablero no puede tener %d filas, debe ser mayo a 0", filas);
        }

        if (columnas < 1) {
            throw new ParametroInvalido("El Tablero no puede tener %d columnas, debe ser mayo a 0", columnas);
        }
    }

    public int obtenerFilaMinima() {

        return filaMinima;
    }

    public int obtenerColumnaMinima() {

        return columnaMinima;
    }

    public int obtenerFilaMaxima() {

        return filaMaxima;
    }

    public int obtenerColumnaMaxima() {

        return columnaMaxima;
    }

    public int contarColumnas() {

        return columnaMaxima - columnaMinima + 1;
    }

    public int contarFilas() {

        return filaMaxima - filaMinima + 1;
    }
}
