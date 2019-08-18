package espacial.piezas;

import espacial.Pieza;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

class BaseRivalObservadaTest extends TestDePiezaObservada {

    private BaseRival unaBaseRival;

    @Override
    protected Pieza unaPieza() {

        return unaBaseRival;
    }

    @Test
    void cambioElEstadoCuandoEsAtacada() {

        dadoQue(fueCreadaUnaBaseRival());
        dadoQue(fueRegistradoUnObservador());

        unaBaseRival.fueAtacadoCon(new AtaqueConTorpedoDeFotones());

        comprobarQue(notificoAlObservadorDelCambioDeEstado());
    }

    private Precondicion fueCreadaUnaBaseRival() {

        return pre(condicion -> {

            unaBaseRival = new BaseRival();
            unaBaseRival.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadaUnaBaseRival());
        dadoQue(fueRegistradoUnObservador());

        repetir(100, i -> unaBaseRival.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    @Test
    void fueAtacado() {

        dadoQue(fueCreadaUnaBaseRival());
        dadoQue(fueRegistradoUnObservador());

        unaBaseRival.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(notificoAlObservadorDelAtaque());
    }
}
