package espacial.piezas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ContenedorDeSustanciaObservadoTest implements TestDeContrato {

    private final Pieza.Observador UN_OBSERVADOR = mock(Pieza.Observador.class, "UN_OBSERVADOR");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    private ContenedorDeSustancia unContenedor;

    @Test
    void fueDestruida() {

        dadoQue(fueCreadoUnContenedor(UN_OBSERVADOR));

        repetir(5, i -> unContenedor.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    private Precondicion fueCreadoUnContenedor(Pieza.Observador unObservador) {

        return pre(condicion -> {

            unContenedor = new ContenedorDeAntimateria(100);
            unContenedor.fueColocadaEn(UN_CASILLERO);
            unContenedor.registrar(unObservador);
        });
    }

    private Postcondicion notificoAlObservorDeLaDestruccion() {

        return post(condicion -> verify(UN_OBSERVADOR).fueDestruida(unContenedor));
    }

}
