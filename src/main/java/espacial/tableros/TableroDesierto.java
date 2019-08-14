package espacial.tableros;

/**
 * Implementación del Tablero que no tiene ninguna Pieza.
 *
 * @author Mariano Tugnarelli
 */
public class TableroDesierto extends TableroEspacial {

    public TableroDesierto() {

        super(new Dimensiones(-10, 10, -10, 10));
    }

    @Override
    protected void inicializarPiezas() {

        /* No tiene ninguna Pieza */
    }
}
