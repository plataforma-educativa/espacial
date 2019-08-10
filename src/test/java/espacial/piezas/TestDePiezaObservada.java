package espacial.piezas;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;

import static org.mockito.Mockito.*;

public abstract class TestDePiezaObservada implements TestDeContrato {

    protected final Pieza.Observador UN_OBSERVADOR = mock(Pieza.Observador.class, "UN_OBSERVADOR");
    protected final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");
    protected final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");

    protected abstract Pieza unaPieza();

    protected Precondicion fueRegistradoUnObservador() {

        return pre(condicion -> unaPieza().registrar(UN_OBSERVADOR));
    }

    protected Postcondicion notificoAlObservorDeLaDestruccion() {

        return post(condicion -> verify(UN_OBSERVADOR).fueDestruida(unaPieza()));
    }

    protected Postcondicion notificoAlObservadorDelMovimiento() {

        return post(condicion -> verify(UN_OBSERVADOR).fueMovida(unaPieza(), UN_CASILLERO));
    }

    protected Postcondicion notificoAlObservadorDelCambioDeEstado() {

        return post(condicion -> verify(UN_OBSERVADOR).cambioElEstadoDe(unaPieza()));
    }

    protected Postcondicion notificoAlObservadorDelAtaque() {

        return post(condicion -> verify(UN_OBSERVADOR).fueAtacada(unaPieza(), UN_ATAQUE));
    }
}
