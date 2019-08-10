package espacial.piezas;

import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

class EstacionCentralObservadaTest extends TestDePiezaObservada {

    private EstacionCentral unaEstacionCentral;

    @Override
    protected Pieza unaPieza() {

        return unaEstacionCentral;
    }

    @Test
    void cambioElEstadoCuandoRecibeUnaCarga() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        unaEstacionCentral.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadorDelCambioDeEstado());
    }

    private Precondicion fueCreadaUnaBaseEspacial() {

        return pre(condicion -> {

            unaEstacionCentral = new EstacionCentral();
            unaEstacionCentral.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void cambioElEstadoCuandoEntregaUnaCarga() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        unaEstacionCentral.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadorDelCambioDeEstado());
    }

    @Test
    void cambioElEstadoCuandoEsAtacada() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        unaEstacionCentral.fueAtacadoCon(new AtaqueConTorpedoDeFotones());

        comprobarQue(notificoAlObservadorDelCambioDeEstado());
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        repetir(20, i -> unaEstacionCentral.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    @Test
    void fueAtacado() {

        dadoQue(fueCreadaUnaBaseEspacial());
        dadoQue(fueRegistradoUnObservador());

        unaEstacionCentral.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(notificoAlObservadorDelAtaque());
    }
}
