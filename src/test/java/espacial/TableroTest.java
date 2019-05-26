package espacial;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import espacial.piezas.BaseEspacial;

public class TableroTest {

    @Test
    public void obtenerDimensiones() {

        Tablero tablero = new Tablero();

        assertThat(tablero.contarFilas()).as("filas").isEqualTo(21);
        assertThat(tablero.contarColumnas()).as("columnas").isEqualTo(53);
        assertThat(tablero.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);
        assertThat(tablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
        assertThat(tablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-26);
        assertThat(tablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(26);
    }

    @Test
    public void paraCadaCoordenada() {

        Tablero tablero = new Tablero();
        final Set<Coordenada> coordenadasIteradas = new HashSet<>();

        tablero.conCadaCoordenada((fila, columna) -> coordenadasIteradas.add(Coordenada.con(fila, columna)));

        assertThat(coordenadasIteradas)
                .as("conjunto con las coordenadas iteradas")
                .hasSize(tablero.contarFilas() * tablero.contarColumnas());
    }

    @Test
    public void obtenerCasilleroEnFilaColumna() {

        Tablero tablero = new Tablero();

        Casillero casillero = tablero.obtenerCasilleroEn(4, 2);

        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.CONTENEDOR);
    }

    @Test
    public void obtenerCasilleroEnCoordenada() {

        Tablero tablero = new Tablero();

        Casillero casillero = tablero.obtenerCasilleroEn(Coordenada.con(4, 2));

        assertThat(casillero).isEqualTo(tablero.obtenerCasilleroEn(4, 2));
        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.CONTENEDOR);
    }

    @Test
    public void colocarEnCasilleroUnaBase() {

        final BaseEspacial base = new BaseEspacial();
        Tablero tablero = new Tablero();

        tablero.colocarEnCasillero(1, 4, base);

        assertThat(tablero.obtenerCasilleroEn(1, 4).obtenerPieza()).isSameAs(base);
    }
}
