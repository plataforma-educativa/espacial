package espacial.piezas;

import espacial.Casillero;
import espacial.Direccion;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CazaEspacialObservadoTest extends TestDePiezaObservada {

    private final Casillero CASILLERO_NORTE = mock(Casillero.class, "CASILLERO_NORTE");
    private final Casillero CASILLERO_SUR = mock(Casillero.class, "CASILLERO_SUR");
    private final Casillero CASILLERO_ESTE = mock(Casillero.class, "CASILLERO_ESTE");
    private final Casillero CASILLERO_OESTE = mock(Casillero.class, "CASILLERO_OESTE");

    private NaveEspacial unCazaEspacial;

    @Override
    protected Pieza unaPieza() {

        return unCazaEspacial;
    }

    @BeforeEach
    void simularCasillero() {

        when(UN_CASILLERO.obtenerContiguoEn(Direccion.NORTE)).thenReturn(CASILLERO_NORTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.SUR)).thenReturn(CASILLERO_SUR);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.ESTE)).thenReturn(CASILLERO_ESTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.OESTE)).thenReturn(CASILLERO_OESTE);
    }

    @Test
    void fueMovida() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        unCazaEspacial.fueColocadaEn(UN_CASILLERO);

        comprobarQue(notificoAlObservadorDelMovimiento());
    }

    private Precondicion fueCreadoUnCazaEspacial() {

        return pre(condicion -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void cambioElEstacoCuandoDecrementoEscudo() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        unCazaEspacial.fueAtacadoCon(new AtaqueConLaser());

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    @Test
    void cambioElEstadoCuandoConsumeUnTorpedo() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        unCazaEspacial.atacarEn(Direccion.NORTE);

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    @Test
    void cambioElEstadoCuandoRecibeUnaCarga() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(19));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    @Test
    void cambioElEstadoCuandoEntregaUnaCarga() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(10));
        unCazaEspacial.entregar(SustanciaEspacial.ANTIMATERIA.por(5));

        comprobarQue(notificoAlObservadoDelCambioDeEstadoDosVeces());
    }

    private Postcondicion notificoAlObservadoDelCambioDeEstadoDosVeces() {

        return post(condicion -> verify(UN_OBSERVADOR, times(2)).cambioElEstadoDe(unCazaEspacial));
    }

    @Test
    void cambioElEstadoCuandoSeMuevePrimeroAlNorteLuegoAlSur() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        unCazaEspacial.moverEn(Direccion.NORTE);
        unCazaEspacial.moverEn(Direccion.SUR);

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(fueRegistradoUnObservador());

        repetir(10, i -> unCazaEspacial.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

}
