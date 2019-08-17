package espacial.tableros;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.Coordenadas;
import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class TableroBatallaEspacialTest implements TestDeContrato {

    private TableroBatallaEspacial unTablero;

    private final Set<Coordenadas> coordenadasIteradas = new HashSet<>();
    private Casillero unCasillero;
    private BaseEspacial unaBase;

    @Test
    void obtenerDimensiones() {

        dadoQue(fueCreadoUnTablero());

        comprobarQue(unTableroTieneLasDimensionesApropiadas());
    }

    private Precondicion fueCreadoUnTablero() {

        return pre(condicion -> unTablero = new TableroBatallaEspacial());
    }

    private Postcondicion unTableroTieneLasDimensionesApropiadas() {

        return post(condicion -> {

            assertThat(unTablero.contarFilas()).as("filas").isEqualTo(21);
            assertThat(unTablero.contarColumnas()).as("columnas").isEqualTo(53);
            assertThat(unTablero.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);
            assertThat(unTablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
            assertThat(unTablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-26);
            assertThat(unTablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(26);
        });
    }

    @Test
    void obtenerCasilleroEnFilaColumna() {

        dadoQue(fueCreadoUnTablero());

        unCasillero = unTablero.obtenerCasilleroEn(4, 2);

        comprobarQue(unCasilleroTieneUnContenedor());
    }

    private Postcondicion unCasilleroTieneUnContenedor() {

        return post(condicion -> assertThat(unCasillero.escanear()).isEqualTo(EspectroEspacial.CONTENEDOR));
    }

    @Test
    void obtenerCasilleroEnCoordenada() {

        dadoQue(fueCreadoUnTablero());

        unCasillero = unTablero.obtenerCasilleroEn(Coordenadas.con(4, 2));

        comprobarQue(unCasilleroTieneUnContenedor());
        comprobarQue(unCasilleroEsElMismoQue(unTablero.obtenerCasilleroEn(4, 2)));
    }

    private Postcondicion unCasilleroEsElMismoQue(Casillero casillero) {

        return post(condicion -> assertThat(unCasillero).isSameAs(casillero));
    }

    @Test
    void colocarEnCasilleroUnaBase() {

        dadoQue(fueCreadoUnTablero());

        unaBase = unTablero.enCasillero(1, 4).ocuparConBase();

        comprobarQue(unaBaseFueColocadaEnLaPosicion(1, 4));
    }

    private Postcondicion unaBaseFueColocadaEnLaPosicion(int fila, int columna) {

        return post(condicion -> {

            assertThat(unaBase.escanear()).as("escanear pieza obtenida").isEqualTo(EspectroEspacial.BASE);
            assertThat(unTablero.obtenerCasilleroEn(fila, columna).obtenerPieza()).isSameAs(unaBase);
        });
    }

    @Test
    void tieneToString() {

        dadoQue(fueCreadoUnTablero());

        comprobarQue(unTableroEsDescriptivo());
    }

    private Postcondicion unTableroEsDescriptivo() {

        return post(condicion -> assertThat(unTablero).hasToString("Tablero[-10..10][-26..26]"));
    }
}
