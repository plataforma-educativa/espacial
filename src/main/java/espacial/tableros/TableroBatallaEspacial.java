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

        colocarContenedoresDeAntimateria();
        colocarContenedorDeCristal();
        colocarContendoreDeMetal();
        colocarAsteroides();
        colocarBasesDesconocidas();
        colocarAgujerosNegros();
        colocarBaseRival();
    }

    private void colocarAgujerosNegros() {

        Coordenadas.Lista listaDeCoordenadas = Coordenadas
                .en(3, -6)
                .en(2, -6)
                .en(1, -6)
                .en(1, -7)
                .en(1, 19)
                .entre(2, 15, 2,17)
                .en(3, 16)
                .entre(-5, -21, -5, -19)
                .entre(-7, -21, -6, -21)
                .entre(-7, -23, -7, -22)
                .entre(-9, -23, -8, -23)
                .entre(-9, -22, -9, -19)
                .entre(-8, -19, -7, -19)
                .entre(-8, -11, -8, -10)
                .en(-9, -10);

        enCasilleros(listaDeCoordenadas).colocarAgujeroNegro();
    }

    private void colocarBasesDesconocidas() {

        enCasilleros(Coordenadas.en(-10, 14).en(-9, -11).en(9, -12))
                .colocarBaseDesconocida();
    }

    private void colocarAsteroides() {

        Coordenadas.Lista listaDeCoordenadas = Coordenadas
                .en(0, 21)
                .entre(1, 16, 1, 18)
                .en(2, 6)
                .en(2,19)
                .entre(3, 17, 3, 19)
                .en(4, 11)
                .en(6, 6)
                .en(6, 20)
                .en(7, 4)
                .en(8, 9)
                .entre(3, -7, 4, -7)
                .en(4, -5)
                .en(5, -6)
                .entre(8, -3, 8, 3)
                .en(9, 1)
                .entre(7, -1, 7, 0)
                .en(1, -3)
                .entre(1, -8, 3, -8)
                .en(1, -11)
                .en(6, -3)
                .en(7, -4)
                .en(9, -2)
                .entre(8, -7, 8, -5)
                .entre(8, -14, 8, -9)
                .en(6, -13)
                .en(9, -13)
                .en(10, -15)
                .en(7, -15)
                .en(8, -16)
                .en(7, -17)
                .en(8, - 18)
                .en(-1, -15)
                .en(-2, -10)
                .en(-4, -7)
                .en(-4, -23)
                .en(-6, -11)
                .en(-7, -7)
                .en(-10, -13)
                .en(-6, 0)
                .en(-9, 0)
                .en(-2, 4)
                .en(-2, 11)
                .en(-5, 13)
                .en(-6, 8)
                .entre(-9, 10, -8, 10)
                .en(-8, 18)
                .en(-9, 17)
                .en(-10, 4)
                .en(-10, 8)
                .en(-10, 15)
                .en(2, -5);

        enCasilleros(listaDeCoordenadas).colocarAsteroide();
    }

    private void colocarContendoreDeMetal() {

        Coordenadas.Lista listaDeCoordenadas = Coordenadas
                .en(6, 17)
                .en(10, 17)
                .en(10, -24)
                .en(-7, -17)
                .en(-4, 21);

        enCasilleros(listaDeCoordenadas).colocarContenedorDeMetal();
    }

    private void colocarContenedoresDeAntimateria() {

        Coordenadas.Lista listaDeCoordenadas = Coordenadas
                .en(-2, -2)
                .en(4, 2)
                .en( 8,4)
                .en(9, 2)
                .en(1, 10)
                .en(10, 10)
                .en(9, 14)
                .en(9, -6)
                .en(7, -8)
                .en(9, -10)
                .en(9, -11)
                .en(3, -14)
                .en(6, -17)
                .en(1, -22)
                .en(7, -23)
                .en(10, -25)
                .en(1, -26)
                .en(-4, -16)
                .en(-2, -25)
                .en(-10, -20)
                .en(2, 7);

        enCasilleros(listaDeCoordenadas).colocarContenedorDeAntimateria();
    }

    private void colocarContenedorDeCristal() {

        Coordenadas.Lista listaDeCoordenadas = Coordenadas
                .en(2, 18)
                .en(-8, -22)
                .en(2, -7);

        enCasilleros(listaDeCoordenadas).colocarContenedorDeCristal();
    }

    private void colocarBaseRival() {

        Coordenadas.Lista delPerimetro = Coordenadas
                .entre(8, -24, 8, -20)
                .entre(2, -24, 2, -20)
                .entre(3, -25, 7, -25)
                .entre(3, -19, 7, -19);

        BaseEspacial baseRival = enCasillero(5, -22).colocarBaseRival();
        final DefensaDeBaseRival defensa = new DefensaDeBaseRival(this, baseRival);

        List<Pieza> asteroides = enCasilleros(delPerimetro).colocarAsteroide();
        asteroides.forEach(asteroide -> asteroide.registrar(defensa));
    }
}
