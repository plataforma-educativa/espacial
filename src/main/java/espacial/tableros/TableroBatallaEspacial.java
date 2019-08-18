package espacial.tableros;

import espacial.BaseEspacial;
import espacial.Coordenadas;
import espacial.Pieza;
import espacial.partidas.batalla.DefensaDeBaseRival;

import java.util.List;

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

        Coordenadas.Lista deBarreNorte = Coordenadas
                .entre(7, -1, 7, 0)
                .entre(8, -3, 8, 3);

        enCasilleros(deBarreNorte).colocarAsteroide();

        enCasillero(-6, 0).colocarAsteroide();
        enCasillero(-9, 0).colocarAsteroide();
        enCasillero(-2, 4).colocarAsteroide();
        enCasillero(2, 6).colocarAsteroide();
        enCasillero(2, -5).colocarAsteroide();
        enCasillero(3, -6).colocarAgujeroNegro();
        enCasillero(2, -6).colocarAgujeroNegro();
        enCasillero(1, -6).colocarAgujeroNegro();
        enCasillero(1, -7).colocarAgujeroNegro();

        colocarBaseRival();
    }

    private void colocarBaseRival() {

        Coordenadas.Lista delPerimetro = Coordenadas
                .entre(8, -25, 8, -19)
                .entre(2, -25, 2, -19)
                .entre(3, -25, 7, -25)
                .entre(3, -19, 7, -19);

        BaseEspacial baseRival = enCasillero(5, -22).colocarBaseRival();
        final DefensaDeBaseRival defensa = new DefensaDeBaseRival(this, baseRival);

        List<Pieza> asteroides = enCasilleros(delPerimetro).colocarAsteroide();
        asteroides.forEach(asteroide -> asteroide.registrar(defensa));
    }
}
