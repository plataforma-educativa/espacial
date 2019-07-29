package espacial.piezas;

import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

class BaseEspacialObservadaTest extends TestDePiezaObservada {

    private BaseEspacial unaBaseEspacial;

    @Override
    protected Pieza unaPieza() {

        return unaBaseEspacial;
    }

    @Test
    void cambioElEstadoCuandoRecibeUnaCarga() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        unaBaseEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    private Precondicion fueCreadaUnaBaseEspacial() {

        return pre(condicion -> {

            unaBaseEspacial = new BaseEspacial();
            unaBaseEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void cambioElEstadoCuandoEntregaUnaCarga() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        unaBaseEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        repetir(20, i -> unaBaseEspacial.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }
}
