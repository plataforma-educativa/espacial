package espacial.piezas;

import espacial.Pieza;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

class BaseDesconocidaObservadaTest extends TestDePiezaObservada {

    private BaseDesconocida unaBaseDesconocida;

    @Override
    protected Pieza unaPieza() {

        return unaBaseDesconocida;
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadaUnaBaseDesconocida());
        dadoQue(fueRegistradoUnObservador());

        repetir(20, i -> unaBaseDesconocida.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    private Precondicion fueCreadaUnaBaseDesconocida() {

        return pre(condicion -> {

            unaBaseDesconocida = new BaseDesconocida();
            unaBaseDesconocida.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void fueAtacado() {

        dadoQue(fueCreadaUnaBaseDesconocida());
        dadoQue(fueRegistradoUnObservador());

        unaBaseDesconocida.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(notificoAlObservadorDelAtaque());
    }
}
