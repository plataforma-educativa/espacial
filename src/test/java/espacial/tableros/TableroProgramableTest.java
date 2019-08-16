package espacial.tableros;

import espacial.Casillero;
import espacial.excepciones.ParametroInvalido;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TableroProgramableTest implements TestDeContrato {

    private TableroProgramable unTablero;
    
    private List<Casillero> casillerosIterados = new LinkedList<>();

    @Test
    void crearConDimensiones() {

        dadoQue(unTableroFueCreadoConDimensiones(30, 26));

        comprobarQue(unTableroTieneDimensiones(30, 26));
        comprobarQue(unTableroTieneLimites(1, 30, 1, 26));
    }

    private Precondicion unTableroFueCreadoConDimensiones(int filas, int columnas) {

        return pre(condicion -> unTablero = new TableroProgramable(filas, columnas));
    }

    private Postcondicion unTableroTieneDimensiones(int filas, int columnas) {

        return post(condicion -> {

            assertThat(unTablero.contarFilas()).as("cantidad de filas").isEqualTo(filas);
            assertThat(unTablero.contarColumnas()).as("cantidad de columnas").isEqualTo(columnas);
        });
    }

    private Postcondicion unTableroTieneLimites(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        return post(condicion -> {

            assertThat(unTablero.obtenerFilaMinima()).as("fila minima").isEqualTo(filaDesde);
            assertThat(unTablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(filaHasta);
            assertThat(unTablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(columnaDesde);
            assertThat(unTablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(columnaHasta);
        });
    }

    @Test
    void crearConDimensionesInvalidas() {

        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroProgramable(-4, 5)));
        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroProgramable(10, 0)));
    }

    private Postcondicion generaUnaExcepcionPorqueElParametroEsInvalido(Operacion operacion) {

        return post(condicion -> {

            assertThatThrownBy(operacion::ejecutar)
                    .as("excepción lanzada")
                    .isInstanceOf(ParametroInvalido.class);

        });
    }

    @Test
    void crearConLimites() {

        dadoQue(unTableroFueCreadoConLimites(-3, 10, 2, 10));

        comprobarQue(unTableroTieneDimensiones(14, 9));
        comprobarQue(unTableroTieneLimites(-3, 10, 2, 10));
    }

    private Precondicion unTableroFueCreadoConLimites(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        return pre(condicion -> unTablero = new TableroProgramable(filaDesde, filaHasta, columnaDesde, columnaHasta));
    }

    @Test
    void crearConLimitesInvalidos() {

        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroProgramable(9, 5, 1, 10)));
        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroProgramable(1, 9, 10, 0)));
    }
    
    @Test
    void iterar() {
        
        dadoQue(unTableroFueCreadoConDimensiones(10, 10));
  
        unTablero.conCadaCasillero((casillero, piezas) -> casillerosIterados.add(casillero));

        comprobarQue(losCasillerosIteradosSon(144));
    }

    private Postcondicion losCasillerosIteradosSon(int cantidadDeCasilleros) {

        return post(condicion -> assertThat(casillerosIterados).hasSize(cantidadDeCasilleros));
    }

}
