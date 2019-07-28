package espacial.piezas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class BaseEspacialObservadaTest implements TestDeContrato {

    private final Pieza.Observador UN_OBSERVADOR = mock(Pieza.Observador.class, "UN_OBSERVADOR");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    private BaseEspacial unaBaseEspacial;

    @Test
    void cambioElEstadoCuandoRecibeUnaCarga() {

        dadoQue(fueCreadaUnaBaseEspacialRegistrando(UN_OBSERVADOR));

        unaBaseEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    private Precondicion fueCreadaUnaBaseEspacialRegistrando(Pieza.Observador unObservador) {

        return pre(condicion -> {

            unaBaseEspacial = new BaseEspacial();
            unaBaseEspacial.fueColocadaEn(UN_CASILLERO);
            unaBaseEspacial.registrar(unObservador);
        });
    }

    private Postcondicion notificoAlObservadoDelCambioDeEstado() {

        return post(condicion -> verify(UN_OBSERVADOR).cambioElEstadoDe(unaBaseEspacial));
    }


    @Test
    void cambioElEstadoCuandoEntregaUnaCarga() {

        dadoQue(fueCreadaUnaBaseEspacialRegistrando(UN_OBSERVADOR));

        unaBaseEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadaUnaBaseEspacialRegistrando(UN_OBSERVADOR));

        repetir(20, i -> unaBaseEspacial.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    private Postcondicion notificoAlObservorDeLaDestruccion() {

        return post(condicion -> verify(UN_OBSERVADOR).fueDestruida(unaBaseEspacial));
    }
}
