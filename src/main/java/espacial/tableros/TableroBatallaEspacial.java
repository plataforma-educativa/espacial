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

        enCasillero(-2, -2).colocarContenedorDeAntimateria();
        enCasillero(4, 2).colocarContenedorDeAntimateria();
        enCasillero(2, -7).colocarContenedorDeCristal();
        enCasillero(10, -24).colocarContenedorDeMetal();
        enCasillero(-10, 14).colocarBaseDesconocida();
        enCasillero(1, -3).colocarAsteroide();
        enCasilleros(7, -1, 7, 0).colocarAsteroide();
        enCasilleros(8, -3, 8, 3).colocarAsteroide();
        enCasillero(-6, 0).colocarAsteroide();
        enCasillero(-9, 0).colocarAsteroide();
        enCasillero(-2, 4).colocarAsteroide();
        enCasillero(2, 6).colocarAsteroide();
        enCasillero(2, -5).colocarAsteroide();
        enCasillero(3, -6).colocarAgujeroNegro();
        enCasillero(2, -6).colocarAgujeroNegro();
        enCasillero(1, -6).colocarAgujeroNegro();
        enCasillero(1, -7).colocarAgujeroNegro();
    }
}
