package espacial;

import espacial.piezas.EstacionCentral;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
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
        final Set<Coordenadas> coordenadasIteradas = new HashSet<>();

        tablero.conCadaCoordenada((fila, columna) -> coordenadasIteradas.add(Coordenadas.con(fila, columna)));

        assertThat(coordenadasIteradas)
                .as("conjunto con las coordenadas iteradas")
                .hasSize(tablero.contarFilas() * tablero.contarColumnas());
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

        Casillero casillero = tablero.obtenerCasilleroEn(Coordenadas.con(4, 2));

        assertThat(casillero).isEqualTo(tablero.obtenerCasilleroEn(4, 2));
        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.CONTENEDOR);
    }

    @Test
    void colocarEnCasilleroUnaBase() {

        final EstacionCentral base = new EstacionCentral();
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
