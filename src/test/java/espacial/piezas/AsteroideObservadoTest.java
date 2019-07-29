package espacial.piezas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AsteroideObservadoTest implements TestDeContrato {

    private final Pieza.Observador UN_OBSERVADOR = mock(Pieza.Observador.class, "UN_OBSERVADOR");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    private Asteroide unAsteroide;

    @Test
    void fueDestruida() {

        dadoQue(fueCreadoUnAsteroide(UN_OBSERVADOR));

        repetir(30, i -> unAsteroide.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    private Precondicion fueCreadoUnAsteroide(Pieza.Observador unObservador) {

        return pre(condicion -> {

            unAsteroide = new Asteroide(100);
            unAsteroide.fueColocadaEn(UN_CASILLERO);
            unAsteroide.registrar(unObservador);
        });
    }

    private Postcondicion notificoAlObservorDeLaDestruccion() {

        return post(condicion -> verify(UN_OBSERVADOR).fueDestruida(unAsteroide));
    }

}
