package espacial.tableros;


import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

class TableroDesiertoTest implements TestDeContrato {

    private TableroDesierto unTableroDesierto;

    @Test
    void dimensiones() {

        dadoQue(fueCreadoUnTableroDesierto());

        comprobarQue(esCuadradoDe20x20());
    }

    private Precondicion fueCreadoUnTableroDesierto() {

        return pre(condicion -> unTableroDesierto = new TableroDesierto());
    }

    private Postcondicion esCuadradoDe20x20() {

        return post(condicion -> {

            assertThat(unTableroDesierto.contarFilas()).as("filas").isEqualTo(21);
            assertThat(unTableroDesierto.contarColumnas()).as("columnas").isEqualTo(21);
            assertThat(unTableroDesierto.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);
            assertThat(unTableroDesierto.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-10);
            assertThat(unTableroDesierto.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
            assertThat(unTableroDesierto.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(10);
        });
    }

    @Test
    void piezas() {

        dadoQue(fueCreadoUnTableroDesierto());

        comprobarQue(noTieneNingunaPieza());
    }

    private Postcondicion noTieneNingunaPieza() {

        return post(condicion -> assertThat(unTableroDesierto).yTieneVacioEnElResto());
    }
}