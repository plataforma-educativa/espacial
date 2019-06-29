package espacial;

import espacial.piezas.BaseEspacial;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class TableroTest implements TestDeContrato {

    @Test
    void obtenerDimensiones() {

        Tablero tablero = new Tablero();

        assertThat(tablero.contarFilas()).as("filas").isEqualTo(21);
        assertThat(tablero.contarColumnas()).as("columnas").isEqualTo(53);
        assertThat(tablero.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);
        assertThat(tablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
        assertThat(tablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-26);
        assertThat(tablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(26);
    }

    @Test
    void conCadaCoordenada() {

        Tablero tablero = new Tablero();
        final Set<Coordenada> coordenadasIteradas = new HashSet<>();

        tablero.conCadaCoordenada((fila, columna) -> coordenadasIteradas.add(Coordenada.con(fila, columna)));

        assertThat(coordenadasIteradas)
                .as("conjunto con las coordenadas iteradas")
                .hasSize(tablero.contarFilas() * tablero.contarColumnas());
    }

    @Test
    void conCadaCoodenadaDelBorde() {

        Tablero tablero = new Tablero();
        final List<Coordenada> coordenadasIteradas = new LinkedList<>();

        tablero.conCadaCoordenadaDelBorde((fila, columna) -> coordenadasIteradas.add(Coordenada.con(fila, columna)));

        final int filaMinima = tablero.obtenerFilaMinima();
        final int filaMaxima = tablero.obtenerFilaMaxima();
        final int columnaMinima = tablero.obtenerColumnaMinima();
        final int columnaMaxima = tablero.obtenerColumnaMaxima();

        assertThat(coordenadasIteradas)
                .as("conjunto de las coordenadas iteradas")
                .contains(
                        Coordenada.con(filaMinima - 1, columnaMinima - 1),
                        Coordenada.con(filaMinima - 1, columnaMaxima + 1),
                        Coordenada.con(filaMaxima + 1, columnaMaxima + 1),
                        Coordenada.con(filaMaxima + 1, columnaMinima - 1)
                )
                .hasSize(4 + (tablero.contarFilas() * 2) + (tablero.contarColumnas() * 2))
                .hasSameElementsAs(new HashSet<>(coordenadasIteradas));
    }

    @Test
    void obtenerCasilleroEnFilaColumna() {

        Tablero tablero = new Tablero();

        Casillero casillero = tablero.obtenerCasilleroEn(4, 2);

        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.CONTENEDOR);
    }

    @Test
    void obtenerCasilleroEnCoordenada() {

        Tablero tablero = new Tablero();

        Casillero casillero = tablero.obtenerCasilleroEn(Coordenada.con(4, 2));

        assertThat(casillero).isEqualTo(tablero.obtenerCasilleroEn(4, 2));
        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.CONTENEDOR);
    }

    @Test
    void colocarEnCasilleroUnaBase() {

        final BaseEspacial base = new BaseEspacial();
        Tablero tablero = new Tablero();

        tablero.colocarEnCasillero(1, 4, base);

        assertThat(tablero.obtenerCasilleroEn(1, 4).obtenerPieza()).isSameAs(base);
    }

    @Test
    void tieneToString() {

        Tablero tablero = new Tablero();

        assertThat(tablero).hasToString("Tablero[-10..10][-26..26]");
    }
}
