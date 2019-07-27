package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Tablero;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.*;

class PosicionTest implements TestDeContrato {

    private final Tablero EN_TABLERO = mock(Tablero.class, "EN_TABLERO");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    private Posicion posicion = Posicion.de(UN_CASILLERO);

    @Test
    void crearConCasillero() {

        dadoQue(fueCreadoUnTablero(-7, 7, -5, 5));
        dadoQue(fueCreadoUnCasilleroEn(Coordenadas.con(0, 0)));

        posicion = Posicion.de(UN_CASILLERO);

        comprobarQue(losIndicesNoSonNulos());
    }

    private Precondicion fueCreadoUnCasilleroEn(Coordenadas coordenadas) {

        return pre(condicion ->  {

            when(UN_CASILLERO.obtenerTablero()).thenReturn(EN_TABLERO);
            when(UN_CASILLERO.obtenerCoordenadas()).thenReturn(coordenadas);
        });
    }

    private Precondicion fueCreadoUnTablero(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        return pre(condicion ->  {

            when(EN_TABLERO.contarFilas()).thenReturn(filaHasta - filaDesde + 1);
            when(EN_TABLERO.contarColumnas()).thenReturn(columnaHasta - columnaDesde + 1);
            when(EN_TABLERO.obtenerFilaMinima()).thenReturn(filaDesde);
            when(EN_TABLERO.obtenerFilaMaxima()).thenReturn(filaHasta);
            when(EN_TABLERO.obtenerColumnaMinima()).thenReturn(columnaDesde);
            when(EN_TABLERO.obtenerColumnaMaxima()).thenReturn(columnaHasta);
        });
    }

    private Postcondicion losIndicesNoSonNulos() {

        return post(condicion ->  {

            assertThat(posicion).isNotNull();
            assertThat(posicion.enFila()).isNotNull();
            assertThat(posicion.enColumna()).isNotNull();
        });
    }

    @ParameterizedTest
    @MethodSource
    void traducirCoordenadasDeTablero7x9Centrado(Coordenadas coordenadas, int filaEsperada, int columnaEsperada) {

        dadoQue(fueCreadoUnTablero(-3, 3, -4, 4));
        dadoQue(fueCreadoUnCasilleroEn(coordenadas));

        posicion = Posicion.de(UN_CASILLERO);

        comprobarQue(losIndicesSon(filaEsperada, columnaEsperada));
    }

    static Stream<Arguments> traducirCoordenadasDeTablero7x9Centrado() {

        return Stream.of(
                arguments(Coordenadas.con(3, 4), 1, 9),
                arguments(Coordenadas.con(-3, -4), 7, 1),
                arguments(Coordenadas.con(3, -4), 1, 1),
                arguments(Coordenadas.con(-3, 4), 7, 9),
                arguments(Coordenadas.con(0, 0), 4, 5)
        );
    }

    private Postcondicion losIndicesSon(int filaEsperada, int columnaEsperada) {

        return post(condicion ->  {

            assertThat(posicion.enFila()).as("fila").isEqualTo(filaEsperada);
            assertThat(posicion.enColumna()).as("columna").isEqualTo(columnaEsperada);
        });
    }

    @ParameterizedTest
    @MethodSource
    void traducirCoordenadasDeTablero3x3Positivo(Coordenadas coordenadas, int filaEsperada, int columnaEsperada) {

        dadoQue(fueCreadoUnTablero(0, 2, 0, 2));
        dadoQue(fueCreadoUnCasilleroEn(coordenadas));

        posicion = Posicion.de(UN_CASILLERO);

        comprobarQue(losIndicesSon(filaEsperada, columnaEsperada));
    }

    static Stream<Arguments> traducirCoordenadasDeTablero3x3Positivo() {

        return Stream.of(
                arguments(Coordenadas.con(2, 2), 1, 3),
                arguments(Coordenadas.con(2, 0), 1, 1),
                arguments(Coordenadas.con(0, 2), 3, 3),
                arguments(Coordenadas.con(0, 0), 3, 1)
        );
    }

    @Test
    void tieneToString() {

        Coordenadas coordenadas = Coordenadas.con(1, 1);
        dadoQue(fueCreadoUnTablero(-4, 4, -6, 6));
        dadoQue(fueCreadoUnCasilleroEn(coordenadas));

        posicion = Posicion.de(UN_CASILLERO);

        comprobarQue(tieneDescripcion("Posicion[4][8] -> Casillero[1][1]"));
    }

    private Postcondicion tieneDescripcion(String descripcion) {

        return post(condicion -> assertThat(posicion).hasToString(descripcion));
    }
}