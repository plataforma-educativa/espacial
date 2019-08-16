import espacial.excepciones.ErrorEspacial;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

public class EscenarioEspacialTest extends TestDeBatallaEspacial implements TestDeContrato {

    private EscenarioEspacial unEscenario;

    @Test
    void crearConLasDimensionesEspecificadas() {

        dadoQue(unEscenarioFueCreadoCon(10, 10));

        comprobarQue(unEscenarioTieneCasillerosEntre(1, 10, 1, 10));
    }

    private Precondicion unEscenarioFueCreadoCon(int filas, int columnas) {

        return pre(condicion -> unEscenario = new EscenarioEspacial(filas, columnas));
    }

    private Postcondicion unEscenarioTieneCasillerosEntre(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        return post(condicion -> {

            assertThat(unEscenario.obtenerFilaMinima()).as("fila mínima").isEqualTo(filaDesde);
            assertThat(unEscenario.obtenerFilaMaxima()).as("fila máxima").isEqualTo(filaHasta);
            assertThat(unEscenario.obtenerColumnaMinima()).as("columna mínima").isEqualTo(columnaDesde);
            assertThat(unEscenario.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(columnaHasta);
        });
    }

    @Test
    void colocarBaseEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarBaseEn(3, 2);

        comprobarQue(unEscenarioTieneUnaBaseEn(3, 2));
        comprobarQue(unaNavePuedeSalirDeLaBaseColocandoseEn(4, 2));
    }

    private Postcondicion unEscenarioTieneUnaBaseEn(int fila, int columna) {

        return post(condicion ->

                assertThat(unEscenario.obtenerTablero())
                        .tieneBase().esAliado().en(fila, columna)
        );
    }

    private Postcondicion unaNavePuedeSalirDeLaBaseColocandoseEn(int fila, int columna) {

        return post(condicion -> {

            Nave unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlNorte();

            assertThat(unEscenario.obtenerTablero())
                    .tieneNave().esAliado().en(fila, columna);
        });
    }

    @Test
    void crearUnaNaveSinColocarUnaBase() {

        dadoQue(unEscenarioFueCreadoCon(10, 10));

        comprobarQue(generaUnaExcepcionPorqueNoFueColocadaUnaBase(() -> new Nave()));
    }

    private Postcondicion generaUnaExcepcionPorqueNoFueColocadaUnaBase(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(ErrorEspacial.class)
        );
    }

    @Test
    void colocarMultiplesBases() {

        dadoQue(unEscenarioFueCreadoCon(10, 10));

        unEscenario.colocarBaseEn(1, 1);
        unEscenario.colocarBaseEn(2, 2);
        unEscenario.colocarBaseEn(3, 3);

        comprobarQue(unEscenarioTieneUnaBaseEn(1, 1));
        comprobarQue(unEscenarioTieneUnaBaseEn(2, 2));
        comprobarQue(unEscenarioTieneUnaBaseEn(3, 3));
        comprobarQue(unaNavePuedeSalirDeLaBaseColocandoseEn(4, 3));
    }

    @Test
    void colocarAsteroideEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarAsteroideEn(1, 1);

        comprobarQue(unEscenarioTieneAsteroideEn(1, 1));
    }

    private Postcondicion unEscenarioTieneAsteroideEn(int fila, int columna) {

        return post(condicion -> assertThat(unEscenario.obtenerTablero()).tieneAsteroide().en(fila, columna));
    }
}
