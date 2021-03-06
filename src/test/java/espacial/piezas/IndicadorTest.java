package espacial.piezas;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import espacial.utiles.Accion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndicadorTest implements TestDeContrato {

    private final Accion UNA_ACCION = mock(Accion.class, "UNA_ACCION");
    private Indicador unIndicador;

    @Test
    void crearConUnValor() {

        unIndicador = new Indicador(300);

        comprobarQue(elValorInicialDeUnIndicadorEs(300));
    }

    private Postcondicion elValorInicialDeUnIndicadorEs(int esperado) {

        return post(condicion -> assertThat(unIndicador.obtenerValor()).as("valor").isEqualTo(esperado));
    }

    @Test
    void decrementar() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(100));

        unIndicador.decrementarEn(40);

        comprobarQue(elValorDeUnIndicadorEs(60));
    }

    private Precondicion fueCreadoUnIndicadorConValorInicial(int valor) {

        return pre(condicion -> unIndicador = new Indicador(valor));
    }

    private Postcondicion elValorDeUnIndicadorEs(int esperado) {

        return post(condicion -> assertThat(unIndicador.obtenerValor()).as("valor").isEqualTo(esperado));
    }

    @Test
    void decrementarMasDelValorInicial() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(50));

        unIndicador.decrementarEn(51);

        comprobarQue(elValorDeUnIndicadorEs(0));
    }

    @Test
    void cuandoSeAgota() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(80));

        unIndicador.cuandoSeAgota(UNA_ACCION);
        unIndicador.decrementarEn(30);
        unIndicador.decrementarEn(50);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    private Postcondicion ejecutoUnaAccionConfigurada() {

        return post(condicion -> verify(UNA_ACCION).ejecutar());
    }

    @Test
    void cuandoSeAgotaSiNoTieneConfiguradaUnaAccion() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(25));

        unIndicador.decrementarEn(25);
    }

    @Test
    void cuandoSeAgotaAlDecrementarHabiendoseAgotadoAntes() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(40));

        unIndicador.cuandoSeAgota(UNA_ACCION);
        unIndicador.decrementarEn(40);
        unIndicador.decrementarEn(60);

        comprobarQue(soloSeEjecutoUnaAccionUnaVez());
    }

    private Postcondicion soloSeEjecutoUnaAccionUnaVez() {

        return post(condicion -> verify(UNA_ACCION, times(1)).ejecutar());
    }

    @Test
    void obtenerNivelLuegoDeDecrementarALaMitad() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(40));

        unIndicador.decrementarEn(20);

        comprobarQue(unIndicadorTieneUnNivelDe(50));

    }

    private Postcondicion unIndicadorTieneUnNivelDe(int nivelEsperado) {

        return post(condicion ->

                assertThat(unIndicador.obtenerNivel())
                        .as("porcentaje")
                        .isEqualTo(nivelEsperado)
        );
    }

    @Test
    void obtenerNivelInicial() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(453));

        comprobarQue(unIndicadorTieneUnNivelDe(100));
    }

    @Test
    void obtenerNivelCuandoEsCero() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(13));

        unIndicador.decrementarEn(13);

        comprobarQue(unIndicadorTieneUnNivelDe(0));
    }

    @Test
    void obtenerNivelCuandoUnTercio() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(99));

        unIndicador.decrementarEn(33);

        comprobarQue(unIndicadorTieneUnNivelDe(66));
    }


    @Test
    void cuandoCambia() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(23));

        unIndicador.cuandoCambia(UNA_ACCION);
        unIndicador.decrementarEn(1);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }
}
