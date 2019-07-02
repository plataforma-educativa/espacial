package espacial.tableros;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Pieza;
import espacial.excepciones.Defecto;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

abstract class EstadoDelCasilleroTest implements TestDeContrato {

    protected final CasilleroInterior CASILLERO = mock(CasilleroInterior.class, "CASILLERO");
    protected final Casillero CASILLERO_ORIGEN = mock(Casillero.class, "CASILLERO_ORIGEN");
    protected final Casillero CASILLERO_DESTINO = mock(Casillero.class, "CASILLERO_DESTINO");
    protected final Pieza PIEZA = mock(Pieza.class, "PIEZA");
    protected final Pieza NAVE = mock(Pieza.class, "NAVE");
    protected final Pieza OTRA_PIEZA = mock(Pieza.class, "OTRA_PIEZA");
    protected final Pieza PIEZA_EN_ORIGEN = mock(Pieza.class, "PIEZA_EN_ORIGEN");
    protected final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");

    protected EstadoDelCasillero estado;

    protected Postcondicion noCambioElEstadoDelCasillero() {

        return post(() -> verify(CASILLERO, never()).cambiarA(any()));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorVacio() {

        return post(() -> verify(CASILLERO).cambiarA(any(Vacio.class)));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorOcupado() {

        return post(() -> verify(CASILLERO).cambiarA(any(Ocupado.class)));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorOcupadoPorUnaBase() {

        return post(() -> verify(CASILLERO).cambiarA(any(OcupadoPorUnaBase.class)));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras() {

        return post(() -> verify(CASILLERO).cambiarA(any(OcupadoPorUnaBaseConNaveEnManiobras.class)));
    }

    protected Postcondicion generaUnDefecto(Ejecutable ejecutable) {

        return post(() -> assertThatThrownBy(ejecutable::ejecutar).isInstanceOf(Defecto.class));
    }

    protected Postcondicion generaUnChoqueEntre(Pieza unaPieza, Pieza otraPieza) {

        return post("genera un choque entre " + unaPieza + " y " + otraPieza, () ->

                verify(unaPieza).chocarCon(otraPieza)
        );
    }

    abstract void siEstaVacio();

    abstract void siEstaOcupado();

    abstract void siEstaOcupadoPorUnaBase();

    abstract void siEstaOcupadoPorUnaBaseConNaveEnManiobras();
}
