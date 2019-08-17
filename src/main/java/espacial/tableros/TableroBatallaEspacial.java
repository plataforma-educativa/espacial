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

        enCasillero(-2, -2).ocuparConContenedorDeAntimateria();
        enCasillero(4, 2).ocuparConContenedorDeAntimateria();
        enCasillero(2, -7).ocuparConContenedorDeCristal();
        enCasillero(10, -24).ocuparConContenedorDeMetal();
        enCasillero(-10, 14).ocuparConBaseDesconocida();
        enCasillero(1, -3).ocuparConAsteroide();
        enCasilleros(7, -1, 7, 0).ocuparConAsteroide();
        enCasilleros(8, -3, 8, 3).ocuparConAsteroide();
        enCasillero(-6, 0).ocuparConAsteroide();
        enCasillero(-9, 0).ocuparConAsteroide();
        enCasillero(-2, 4).ocuparConAsteroide();
        enCasillero(2, 6).ocuparConAsteroide();
        enCasillero(2, -5).ocuparConAsteroide();
        enCasillero(3, -6).ocuparConAgujeroNegro();
        enCasillero(2, -6).ocuparConAgujeroNegro();
        enCasillero(1, -6).ocuparConAgujeroNegro();
        enCasillero(1, -7).ocuparConAgujeroNegro();
    }
}
