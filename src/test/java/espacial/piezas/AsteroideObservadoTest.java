package espacial.piezas;

import espacial.Pieza;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

class AsteroideObservadoTest extends TestDePiezaObservada {

    private Asteroide unAsteroide;

    @Override
    protected Pieza unaPieza() {

        return unAsteroide;
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadoUnAsteroide());
        dadoQue(fueRegistradoUnObservador());

        repetir(30, i -> unAsteroide.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    private Precondicion fueCreadoUnAsteroide() {

        return pre(condicion -> {

            unAsteroide = new Asteroide(100);
            unAsteroide.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void fueAtacado() {

        dadoQue(fueCreadoUnAsteroide());
        dadoQue(fueRegistradoUnObservador());

        unAsteroide.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(notificoAlObservadorDelAtaque());
    }
}
