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

        enCasillero(-2, -2).crearContenedorDeAntimateria();
        enCasillero(4, 2).crearContenedorDeAntimateria();
        enCasillero(2, -7).crearContenedorDeCristal();
        enCasillero(10, -24).crearContenedorDeMetal();
        enCasillero(-10, 14).crearBaseDesconocida();
        enCasillero(1, -3).crearAsteroide();
        enCasilleros(7, -1, 7, 0).crearAsteroide();
        enCasilleros(8, -3, 8, 3).crearAsteroide();
        enCasillero(-6, 0).crearAsteroide();
        enCasillero(-9, 0).crearAsteroide();
        enCasillero(-2, 4).crearAsteroide();
        enCasillero(2, 6).crearAsteroide();
        enCasillero(2, -5).crearAsteroide();
        enCasillero(3, -6).crearAgujeroNegro();
        enCasillero(2, -6).crearAgujeroNegro();
        enCasillero(1, -6).crearAgujeroNegro();
        enCasillero(1, -7).crearAgujeroNegro();
    }
}
