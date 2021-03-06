package espacial.tableros;

import espacial.Amarre;
import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.Coordenadas;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Tablero;
import espacial.excepciones.ParametroInvalido;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class TableroEspacialTest implements TestDeContrato {

    private final Tablero.Observador UN_OBSERVADOR = mock(Tablero.Observador.class, "UN_OBSERVADOR");
    private final Tablero.Observador OTRO_OBSERVADOR = mock(Tablero.Observador.class, "OTRO_OBSERVADOR");

    private TableroEspacial unTablero;

    private NaveEspacial unaNaveEspacial;
    private BaseEspacial unaBaseEspacial;

    private List<Casillero> casillerosIterados = new LinkedList<>();

    private List<Pieza> piezasColocadas;

    @Test
    void crearConDimensiones() {

        dadoQue(unTableroFueCreadoConDimensiones(30, 26));

        comprobarQue(unTableroTieneDimensiones(30, 26));
        comprobarQue(unTableroTieneLimites(1, 30, 1, 26));
    }

    private Precondicion unTableroFueCreadoConDimensiones(int filas, int columnas) {

        return pre(condicion -> unTablero = new TableroEspacial(filas, columnas));
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

        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroEspacial(-4, 5)));
        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroEspacial(10, 0)));
    }

    private Postcondicion generaUnaExcepcionPorqueElParametroEsInvalido(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(ParametroInvalido.class)
        );
    }

    @Test
    void crearConLimites() {

        dadoQue(unTableroFueCreadoConLimites(-3, 10, 2, 10));

        comprobarQue(unTableroTieneDimensiones(14, 9));
        comprobarQue(unTableroTieneLimites(-3, 10, 2, 10));
    }

    private Precondicion unTableroFueCreadoConLimites(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        return pre(condicion -> unTablero = new TableroEspacial(filaDesde, filaHasta, columnaDesde, columnaHasta));
    }

    @Test
    void crearConLimitesInvalidos() {

        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroEspacial(9, 5, 1, 10)));
        comprobarQue(generaUnaExcepcionPorqueElParametroEsInvalido(() -> new TableroEspacial(1, 9, 10, 0)));
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

    @Test
    void enCasilleroColocarAsteroide() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasillero(1, 2).colocarAsteroide();

        comprobarQue(unTableroTieneUnAsteroideEn(1, 2));
        comprobarQue(fueronColocadasPiezas(1));
    }

    private Postcondicion unTableroTieneUnAsteroideEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneAsteroide().en(fila, columna));
    }

    private Postcondicion fueronColocadasPiezas(int cantidad) {

        return post(condicion -> assertThat(piezasColocadas).hasSize(cantidad).doesNotHaveDuplicates());
    }

    @Test
    void enCasillerosColocarAsteroide() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasilleros(Coordenadas.entre(1, 1, 1, 3)).colocarAsteroide();

        comprobarQue(unTableroTieneUnAsteroideEn(1, 1));
        comprobarQue(unTableroTieneUnAsteroideEn(1, 2));
        comprobarQue(unTableroTieneUnAsteroideEn(1, 3));
        comprobarQue(fueronColocadasPiezas(3));
    }

    @Test
    void enCasilleroColocarContenedorDeAntimateria() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasillero(1, 1).colocarContenedorDeAntimateria();

        comprobarQue(unTableroTieneUnContenedorDeAntimateriaEn(1, 1));
        comprobarQue(fueronColocadasPiezas(1));
    }

    private Postcondicion unTableroTieneUnContenedorDeAntimateriaEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneContenedor().conAntimateria().en(fila, columna));
    }

    @Test
    void enCasillerosColocarContenedorDeAntimateria() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasilleros(Coordenadas.entre(1, 1, 1, 3)).colocarContenedorDeAntimateria();

        comprobarQue(unTableroTieneUnContenedorDeAntimateriaEn(1, 1));
        comprobarQue(unTableroTieneUnContenedorDeAntimateriaEn(1, 2));
        comprobarQue(unTableroTieneUnContenedorDeAntimateriaEn(1, 3));
        comprobarQue(fueronColocadasPiezas(3));
    }

    @Test
    void enCasilleroColocarContenedorDeCristal() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasillero(1, 1).colocarContenedorDeCristal();

        comprobarQue(unTableroTieneUnContenedorDeCristalEn(1, 1));
        comprobarQue(fueronColocadasPiezas(1));
    }

    private Postcondicion unTableroTieneUnContenedorDeCristalEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneContenedor().conCristal().en(fila, columna));
    }

    @Test
    void enCasillerosColocarContenedorDeCristal() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasilleros(Coordenadas.entre(3, 1, 3, 3)).colocarContenedorDeCristal();

        comprobarQue(unTableroTieneUnContenedorDeCristalEn(3, 1));
        comprobarQue(unTableroTieneUnContenedorDeCristalEn(3, 2));
        comprobarQue(unTableroTieneUnContenedorDeCristalEn(3, 3));
        comprobarQue(fueronColocadasPiezas(3));
    }

    @Test
    void enCasilleroColocarContenedorDeMetal() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasillero(1, 1).colocarContenedorDeMetal();

        comprobarQue(unTableroTieneUnContenedorDeMetalEn(1, 1));
        comprobarQue(fueronColocadasPiezas(1));
    }

    private Postcondicion unTableroTieneUnContenedorDeMetalEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneContenedor().conMetal().en(fila, columna));
    }

    @Test
    void enCasillerosColocarContenedorDeMetal() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasilleros(Coordenadas.entre(2, 1, 3, 3)).colocarContenedorDeMetal();

        comprobarQue(unTableroTieneUnContenedorDeMetalEn(2, 1));
        comprobarQue(unTableroTieneUnContenedorDeMetalEn(2, 2));
        comprobarQue(unTableroTieneUnContenedorDeMetalEn(2, 3));
        comprobarQue(unTableroTieneUnContenedorDeMetalEn(3, 1));
        comprobarQue(unTableroTieneUnContenedorDeMetalEn(3, 2));
        comprobarQue(unTableroTieneUnContenedorDeMetalEn(3, 3));
        comprobarQue(fueronColocadasPiezas(6));
    }

    @Test
    void enCasilleroColocarAgujeroNegro() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasillero(2, 2).colocarAgujeroNegro();

        comprobarQue(unTableroTieneUnAgujeroNegroEn(2, 2));
        comprobarQue(fueronColocadasPiezas(1));
    }

    private Postcondicion unTableroTieneUnAgujeroNegroEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneAgujeroNegro().en(fila, columna));
    }

    @Test
    void enCasillerosColocarAgujeroNegro() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasilleros(Coordenadas.entre(2, 1, 2, 2)).colocarAgujeroNegro();

        comprobarQue(unTableroTieneUnAgujeroNegroEn(2, 1));
        comprobarQue(unTableroTieneUnAgujeroNegroEn(2, 2));
        comprobarQue(fueronColocadasPiezas(2));
    }

    @Test
    void enCasilleroColocarBaseDesierta() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasillero(3, 2).colocarBaseDesconocida();

        comprobarQue(unTableroTieneUnaBaseDesiertaEn(3, 2));
        comprobarQue(fueronColocadasPiezas(1));
    }

    private Postcondicion unTableroTieneUnaBaseDesiertaEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneBase().esNeutral().en(fila, columna));
    }

    @Test
    void enCasilleroColocarBaseRival() {

        dadoQue(unTableroFueCreadoConDimensiones(5, 3));

        unaBaseEspacial = unTablero.enCasillero(3, 1).colocarBaseRival();

        comprobarQue(unTableroTieneUnaBaseRivalEn(3, 1));
        comprobarQue(unaBaseEspacialEstaEn(3 ,1));
    }

    private Postcondicion unTableroTieneUnaBaseRivalEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneBase().esRival().en(fila, columna));
    }

    @Test
    void enCasillerosColocarBaseDesierta() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        piezasColocadas = unTablero.enCasilleros(Coordenadas.entre(3, 2, 3, 3)).colocarBaseDesconocida();

        comprobarQue(unTableroTieneUnaBaseDesiertaEn(3, 2));
        comprobarQue(unTableroTieneUnaBaseDesiertaEn(3, 3));
        comprobarQue(fueronColocadasPiezas(2));
    }

    @Test
    void enCasilleroColocarNave() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        unaNaveEspacial = unTablero.enCasillero(3, 1).colocarNave();

        comprobarQue(unTableroTieneUnaNaveEn(3, 1));
        comprobarQue(unaNaveEspacialEstaEn(3, 1));
    }

    private Postcondicion unTableroTieneUnaNaveEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneNave().esAliado().en(fila, columna));
    }

    private Postcondicion unaNaveEspacialEstaEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unTablero.obtenerCasilleroEn(fila, columna).obtenerPieza())
                        .isSameAs(unaNaveEspacial)
        );
    }

    @Test
    void enCasilleroColocarNaveRival() {

        dadoQue(unTableroFueCreadoConDimensiones(7, 7));

        unaNaveEspacial = unTablero.enCasillero(1, 5).colocarNaveRival();

        comprobarQue(unTableroTieneUnaNaveRivalEn(1, 5));
        comprobarQue(unaNaveEspacialEstaEn(1, 5));
    }

    private Postcondicion unTableroTieneUnaNaveRivalEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneNave().esRival().en(fila, columna));
    }

    @Test
    void enCasilleroColocarBase() {

        dadoQue(unTableroFueCreadoConDimensiones(3, 3));

        unaBaseEspacial = unTablero.enCasillero(1, 3).colocarBase();

        comprobarQue(unTableroTieneUnaBaseEn(1, 3));
        comprobarQue(unaBaseEspacialEstaEn(1, 3));
    }

    private Postcondicion unTableroTieneUnaBaseEn(int fila, int columna) {

        return post(condicion -> assertThat(unTablero).tieneBase().esAliado().en(fila, columna));
    }

    private Postcondicion unaBaseEspacialEstaEn(int fila, int columna) {

        return post(condicion ->
                assertThat(unTablero.obtenerCasilleroEn(fila, columna).obtenerPieza())
                        .isSameAs(unaBaseEspacial)
        );
    }

    @Test
    void enBaseAmarrarNave() {

        dadoQue(unTableroFueCreadoConDimensiones(4, 4));
        dadoQue(unaBaseEspacialFueColocadaEn(1, 1));

        unaNaveEspacial = unTablero.enBase(unaBaseEspacial).amarrarNave();

        comprobarQue(unaNaveEspacialEstaAmarradaEnUnaBaseEspacial());
    }

    private Precondicion unaBaseEspacialFueColocadaEn(int fila, int columna) {

        return pre(condicion -> unaBaseEspacial = unTablero.enCasillero(fila, columna).colocarBase());
    }

    private Postcondicion unaNaveEspacialEstaAmarradaEnUnaBaseEspacial() {

        return post(condicion ->

                assertThat(unaBaseEspacial.obtenerAmarres())
                        .extracting(Amarre::obtenerPieza).contains(unaNaveEspacial)
        );
    }

    @Test
    void enBaseAmarrarNaveRival() {

        dadoQue(unTableroFueCreadoConDimensiones(4, 4));
        dadoQue(unaBaseEspacialFueColocadaEn(2, 2));

        unaNaveEspacial = unTablero.enBase(unaBaseEspacial).amarrarNaveRival();

        comprobarQue(unaNaveEspacialEstaAmarradaEnUnaBaseEspacial());
    }

    @Test
    void registrar() {

        dadoQue(unTableroFueCreadoConDimensiones(10, 5));
        dadoQue(fueRegistrado(UN_OBSERVADOR));

        unaNaveEspacial = unTablero.enCasillero(1, 1).colocarNave();

        comprobarQue(fueNotificadoPorAgregarUnaNaveEspacialA(UN_OBSERVADOR));
    }

    private Precondicion fueRegistrado(Tablero.Observador observador) {

        return pre(condicion -> unTablero.registrar(observador));
    }

    private Postcondicion fueNotificadoPorAgregarUnaNaveEspacialA(Tablero.Observador observador) {

        return post(condicion -> verify(observador).fueAgregadaEn(any(Casillero.class), same(unaNaveEspacial)));
    }

    @Test
    void registrarMultiplesObservadores() {

        dadoQue(unTableroFueCreadoConDimensiones(10, 5));
        dadoQue(fueRegistrado(UN_OBSERVADOR));
        dadoQue(fueRegistrado(OTRO_OBSERVADOR));

        unaNaveEspacial = unTablero.enCasillero(1, 1).colocarNave();

        comprobarQue(fueNotificadoPorAgregarUnaNaveEspacialA(UN_OBSERVADOR));
        comprobarQue(fueNotificadoPorAgregarUnaNaveEspacialA(OTRO_OBSERVADOR));
    }

    @Test
    void tieneToString() {

        dadoQue(unTableroFueCreadoConLimites(-10, 10, -26, 26));

        comprobarQue(unTableroEsDescriptivo());
    }

    private Postcondicion unTableroEsDescriptivo() {

        return post(condicion -> assertThat(unTablero).hasToString("Tablero[-10..10][-26..26]"));
    }
}
