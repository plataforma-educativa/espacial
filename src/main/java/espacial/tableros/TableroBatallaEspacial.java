package espacial.tableros;

/**
 * Implementación del Tablero usado por defecto en la BatallaEspacial.
 *
 * @author Mariano Tugnarelli
 */
public class TableroBatallaEspacial extends TableroEspacial {

    public TableroBatallaEspacial() {

        super(new Dimensiones(-10, 10, -26, 26));
    }

    @Override
    protected void inicializarPiezas() {

        colocarEnCoordenada(-2, -2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(4, 2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(2, -7, fabrica::crearContenedorDeCristal);
        colocarEnCoordenada(10, -24, fabrica::crearContenedorDeMetal);
        colocarEnCoordenada(-10, 14, fabrica::crearBaseDesconocida);
        colocarEnCoordenada(1, -3, fabrica::crearAsteoride);
        colocarEntreCoordenadas(7, -1, 7, 0, fabrica::crearAsteoride);
        colocarEntreCoordenadas(8, -3, 8, 3, fabrica::crearAsteoride);
        colocarEnCoordenada(-6, 0, fabrica::crearAsteoride);
        colocarEnCoordenada(-9, 0, fabrica::crearAsteoride);
        colocarEnCoordenada(-2, 4, fabrica::crearAsteoride);
        colocarEnCoordenada(2, 6, fabrica::crearAsteoride);
        colocarEnCoordenada(2, -5, fabrica::crearAsteoride);
        colocarEnCoordenada(3, -6, fabrica::crearAgujeroNegro);
        colocarEnCoordenada(2, -6, fabrica::crearAgujeroNegro);
        colocarEnCoordenada(1, -6, fabrica::crearAgujeroNegro);
        colocarEnCoordenada(1, -7, fabrica::crearAgujeroNegro);
    }
}
