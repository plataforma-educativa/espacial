package espacial;

import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class CoordenadasTest implements TestDeContrato {

    @Test
    void crearConFilaColumna() {

        Coordenadas coordenadas = Coordenadas.con(5, 3);

        comprobarQue(fueInicializada(coordenadas, 5, 3));
    }

    private Postcondicion fueInicializada(Coordenadas coordenadas, int fila, int columna) {

        return post(condicion ->  {

            assertThat(coordenadas.obtenerFila()).as("fila").isEqualTo(5);
            assertThat(coordenadas.obtenerColumna()).as("columna").isEqualTo(3);
        });
    }

    @Test
    void sonIgualesSiSonElMismoObjeto() {

        Coordenadas coordenadas = Coordenadas.con(2, 4);

        assertThat(coordenadas.equals(coordenadas)).isTrue();
    }

    @Test
    void sonIgualesSiCoincideFilaColumna() {

        Coordenadas unaCoordenadas = Coordenadas.con(3, 9);
        Coordenadas otraCoordenadas = Coordenadas.con(3, 9);

        assertThat(unaCoordenadas.equals(otraCoordenadas)).isTrue();
        assertThat(otraCoordenadas.equals(unaCoordenadas)).isTrue();
    }

    @Test
    void noSonIgualesSiSoloCoincideFila() {

        Coordenadas unaCoordenadas = Coordenadas.con(5, 8);
        Coordenadas otraCoordenadas = Coordenadas.con(5, -6);

        assertThat(unaCoordenadas.equals(otraCoordenadas)).isFalse();
        assertThat(otraCoordenadas.equals(unaCoordenadas)).isFalse();
    }

    @Test
    void noSonIgualesSiSoloCoincideLaColumna() {

        Coordenadas unaCoordenadas = Coordenadas.con(2, 5);
        Coordenadas otraCoordenadas = Coordenadas.con(-1, 5);

        assertThat(unaCoordenadas.equals(otraCoordenadas)).isFalse();
        assertThat(otraCoordenadas.equals(unaCoordenadas)).isFalse();
    }

    @Test
    void siSonIgualesTieneElMismoHash() {

        Coordenadas unaCoordenadas = Coordenadas.con(2, 5);
        Coordenadas otraCoordenadas = Coordenadas.con(2, 5);

        assertThat(unaCoordenadas).hasSameHashCodeAs(otraCoordenadas);
    }

    @Test
    void tieneToString() {

        Coordenadas coordenadas = Coordenadas.con(23, 78);

        assertThat(coordenadas).hasToString("[23][78]");
    }

    @ParameterizedTest
    @MethodSource
    void numerar(int fila, int columna, int numero) {

        Coordenadas coordenadas = Coordenadas.con(fila, columna);

        assertThat(coordenadas.numerar()).as("numero de Coordenadas %s", coordenadas).isEqualTo(numero);
    }

    static Stream<Arguments> numerar() {

        return Stream.of(
                arguments(0, 1, 1),
                arguments(1, 1, 2),
                arguments(1, 0, 3),
                arguments(1, -1, 4),
                arguments(0, -1, 5),
                arguments(-1, -1, 6),
                arguments(-1, 0, 7),
                arguments(-1, 1, 8),
                arguments(-1, 2, 9),
                arguments(0, 2, 10),
                arguments(1, 2, 11),
                arguments(2, 2, 12),
                arguments(2, 1, 13),
                arguments(2, 0, 14),
                arguments(2, -1, 15),
                arguments(2, -2, 16),
                arguments(1, -2, 17),
                arguments(0, -2, 18),
                arguments(-1, -2, 19),
                arguments(-2, -2, 20),
                arguments(-2, -1, 21),
                arguments(-2, 0, 22),
                arguments(-2, 1, 23),
                arguments(-2, 2, 24),
                arguments(-2, 3, 25),
                arguments(-1, 3, 26),
                arguments(0, 3, 27),
                arguments(1, 3, 28),
                arguments(2, 3, 29),
                arguments(3, 3, 30),
                arguments(3, 2, 31),
                arguments(3, 1, 32),
                arguments(3, 0, 33),
                arguments(3, -1, 34),
                arguments(3, -2, 35),
                arguments(3, -3, 36),
                arguments(2, -3, 37),
                arguments(1, -3, 38),
                arguments(0, -3, 39),
                arguments(-1, -3, 40),
                arguments(-2, -3, 41),
                arguments(-3, -3, 42),
                arguments(-3, -2, 43),
                arguments(-3, -1, 44),
                arguments(-3, 0, 45),
                arguments(-3, 1, 46),
                arguments(-3, 2, 47),
                arguments(-3, 3, 48),
                arguments(0, 0, 0)
        );
    }
}
