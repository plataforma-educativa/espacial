package espacial.piezas;

import espacial.Casillero;
import espacial.Direccion;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CazaEspacialObservadoTest implements TestDeContrato {

    private final Pieza.Observador UN_OBSERVADOR = mock(Pieza.Observador.class, "UN_OBSERVADOR");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");
    private final Casillero CASILLERO_NORTE = mock(Casillero.class, "CASILLERO_NORTE");
    private final Casillero CASILLERO_SUR = mock(Casillero.class, "CASILLERO_SUR");
    private final Casillero CASILLERO_ESTE = mock(Casillero.class, "CASILLERO_ESTE");
    private final Casillero CASILLERO_OESTE = mock(Casillero.class, "CASILLERO_OESTE");

    private NaveEspacial unCazaEspacial;

    @BeforeEach
    void simularCasillero() {

        when(UN_CASILLERO.obtenerContiguoEn(Direccion.NORTE)).thenReturn(CASILLERO_NORTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.SUR)).thenReturn(CASILLERO_SUR);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.ESTE)).thenReturn(CASILLERO_ESTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.OESTE)).thenReturn(CASILLERO_OESTE);
    }

    @Test
    void fueMovida() {

        dadoQue(fueCreadoUnCazaEspacialRegistrando(UN_OBSERVADOR));

        unCazaEspacial.fueColocadaEn(UN_CASILLERO);

        comprobarQue(notificoFueMovidaSobreUnObservador());
    }

    private Precondicion fueCreadoUnCazaEspacialRegistrando(Pieza.Observador un_observador) {

        return pre(condicion -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
            unCazaEspacial.registrar(UN_OBSERVADOR);
        });
    }

    private Postcondicion notificoFueMovidaSobreUnObservador() {

        return post(condicion -> verify(UN_OBSERVADOR).fueMovida(unCazaEspacial, UN_CASILLERO));
    }

    @Test
    void cambioElEstacoCuandoDecrementoEscudo() {

        dadoQue(fueCreadoUnCazaEspacialRegistrando(UN_OBSERVADOR));

        unCazaEspacial.fueAtacadoCon(new AtaqueConLaser());

        comprobarQue(notificoCambioElEstadoSobreUnObservador());
    }

    private Postcondicion notificoCambioElEstadoSobreUnObservador() {

        return post(condicion -> verify(UN_OBSERVADOR).cambioElEstadoDe(unCazaEspacial));
    }

    @Test
    void cambioElEstadoCuandoConsumeUnTorpedo() {

        dadoQue(fueCreadoUnCazaEspacialRegistrando(UN_OBSERVADOR));

        unCazaEspacial.atacarEn(Direccion.NORTE);

        comprobarQue(notificoCambioElEstadoSobreUnObservador());
    }
}
