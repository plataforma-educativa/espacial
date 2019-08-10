package espacial.tableros;

import espacial.Ataque;
import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.excepciones.Defecto;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

abstract class EstadoDelCasilleroTest implements TestDeContrato {

    protected final CasilleroInterior CASILLERO = mock(CasilleroInterior.class, "CASILLERO");
    protected final Casillero CASILLERO_ORIGEN = mock(Casillero.class, "CASILLERO_ORIGEN");
    protected final Casillero CASILLERO_DESTINO = mock(Casillero.class, "CASILLERO_DESTINO");
    protected final Pieza PIEZA = mock(NaveEspacial.class, "PIEZA");
    protected final Pieza NAVE = mock(NaveEspacial.class, "NAVE");
    protected final Pieza BASE = mock(BaseEspacial.class, "BASE");
    protected final Pieza OTRA_PIEZA = mock(Pieza.class, "OTRA_PIEZA");
    protected final Pieza PIEZA_EN_ORIGEN = mock(NaveEspacial.class, "PIEZA_EN_ORIGEN");
    protected final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");

    protected EstadoDelCasillero estado;

    protected Postcondicion noCambioElEstadoDelCasillero() {

        return post(condicion -> verify(CASILLERO, never()).cambiarA(any()));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorVacio() {

        return post(condicion -> verify(CASILLERO).cambiarA(any(Vacio.class)));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorOcupado() {

        return post(condicion -> verify(CASILLERO).cambiarA(any(Ocupado.class)));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorOcupadoPorUnaBase() {

        return post(condicion -> verify(CASILLERO).cambiarA(any(OcupadoPorUnaBase.class)));
    }

    protected Postcondicion cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras() {

        return post(condicion -> verify(CASILLERO).cambiarA(any(OcupadoPorUnaBaseConNaveEnManiobras.class)));
    }

    protected Postcondicion generaUnDefecto(Operacion operacion) {

        return post(condicion -> assertThatThrownBy(operacion::ejecutar).isInstanceOf(Defecto.class));
    }

    protected Postcondicion generaUnChoqueEntre(Pieza unaPieza, Pieza otraPieza) {

        return post(condicion -> {

            condicion.es("genera un choque entre %s y %s", unaPieza, otraPieza);

            verify(unaPieza).chocarCon(otraPieza);
        });
    }

    abstract void siEstaVacio();

    abstract void siEstaOcupado();

    abstract void siEstaOcupadoPorUnaBase();

    abstract void siEstaOcupadoPorUnaBaseConNaveEnManiobras();
}
