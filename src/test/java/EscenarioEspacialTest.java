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
    void obtenerNombre() {

        dadoQue(unEscenarioFueCreadoCon(10, 20));

        comprobarQue(unEscenarioTieneNombre());
    }

    private Postcondicion unEscenarioTieneNombre() {

        return post(condicion -> assertThat(unEscenario.obtenerNombre()).isEqualTo("Escenario Espacial"));
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
    void colocarBaseConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(9, 9));

        unEscenario.colocar(Espacial.BASE, 5, 6);

        comprobarQue(unEscenarioTieneUnaBaseEn(5, 6));
        comprobarQue(unaNavePuedeSalirDeLaBaseColocandoseEn(6, 6));
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

    @Test
    void colocarAsteroideConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(8, 5));

        unEscenario.colocar(Espacial.ASTEROIDE, 8, 5);

        comprobarQue(unEscenarioTieneAsteroideEn(8, 5));
    }

    @Test
    void colocarContenedorDeAntimateriaEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarContenedorDeAntimateriaEn(2, 2);

        comprobarQue(unEscenarioTieneContenedorDeAntimateriaEn(2, 2));
    }

    private Postcondicion unEscenarioTieneContenedorDeAntimateriaEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unEscenario.obtenerTablero()).tieneContenedor().conAntimateria().en(fila, columna)
        );
    }

    @Test
    void colocarContenedorDeAntimateriaConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(7, 3));

        unEscenario.colocar(Espacial.CONTENEDOR_ANTIMATERIA, 3, 1);

        comprobarQue(unEscenarioTieneContenedorDeAntimateriaEn(3, 1));
    }

    @Test
    void colocarContenedorDeCristalEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarContenedorDeCristalEn(2, 2);

        comprobarQue(unEscenarioTieneContenedorDeCristalEn(2, 2));
    }

    private Postcondicion unEscenarioTieneContenedorDeCristalEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unEscenario.obtenerTablero()).tieneContenedor().conCristal().en(fila, columna)
        );
    }

    @Test
    void colocarContenedorDeCristalConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(4, 10));

        unEscenario.colocar(Espacial.CONTENEDOR_CRISTAL, 1, 9);

        comprobarQue(unEscenarioTieneContenedorDeCristalEn(1, 9));
    }

    @Test
    void colocarContenedorDeMetalEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarContenedorDeMetalEn(2, 2);

        comprobarQue(unEscenarioTieneContenedorDeMetalEn(2, 2));
    }

    private Postcondicion unEscenarioTieneContenedorDeMetalEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unEscenario.obtenerTablero()).tieneContenedor().conMetal().en(fila, columna)
        );
    }

    @Test
    void colocarContenedorDeMetalConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(6, 3));

        unEscenario.colocar(Espacial.CONTENEDOR_METAL, 5, 2);

        comprobarQue(unEscenarioTieneContenedorDeMetalEn(5, 2));
    }

    @Test
    void colocarAgujeroNegroEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarAgujeroNegroEn(3, 3);

        comprobarQue(unEscenarioTieneAgujeroNegroEn(3, 3));
    }

    private Postcondicion unEscenarioTieneAgujeroNegroEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unEscenario.obtenerTablero()).tieneAgujeroNegro().en(fila, columna)
        );
    }

    @Test
    void colocarAgujeroNegroConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(8, 8));

        unEscenario.colocar(Espacial.AGUJERO_NEGRO, 3, 4);

        comprobarQue(unEscenarioTieneAgujeroNegroEn(3, 4));
    }

    @Test
    void colocarBaseDesconocidaEn() {

        dadoQue(unEscenarioFueCreadoCon(5, 5));

        unEscenario.colocarBaseDesconocidaEn(5, 5);

        comprobarQue(unEscenarioTieneBaseDesconocidaEn(5, 5));
    }

    private Postcondicion unEscenarioTieneBaseDesconocidaEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unEscenario.obtenerTablero()).tieneBase().esNeutral().en(fila, columna)
        );
    }

    @Test
    void colocarBaseDesconocidaConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(10, 5));

        unEscenario.colocar(Espacial.BASE_DESCONOCIDA,3, 2);

        comprobarQue(unEscenarioTieneBaseDesconocidaEn(3, 2));
    }

    @Test
    void colocarNaveEn() {

        dadoQue(unEscenarioFueCreadoCon(7, 7));

        unEscenario.colocarNaveEn(5, 5);

        comprobarQue(unEscenarioTieneNaveEn(5, 5));
    }

    private Postcondicion unEscenarioTieneNaveEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unEscenario.obtenerTablero()).tieneNave().esAliado().en(fila, columna)
        );
    }

    @Test
    void colocarNaveConEspacial() {

        dadoQue(unEscenarioFueCreadoCon(2, 5));

        unEscenario.colocar(Espacial.NAVE,2, 1);

        comprobarQue(unEscenarioTieneNaveEn(2, 1));
    }

}
