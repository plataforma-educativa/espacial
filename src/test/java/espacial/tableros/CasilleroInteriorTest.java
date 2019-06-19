package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CasilleroInteriorTest implements TestDeContrato {

    private final Tablero TABLERO = mock(Tablero.class, "TABLERO");
    private final Pieza UNA_PIEZA = mock(Pieza.class, "UNA_PIEZA");
    private final Casillero OTRO_CASILLERO = mock(Casillero.class, "OTRO_CASILLERO");
    private final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");

    private final EstadoDelCasillero ESTADO = mock(EstadoDelCasillero.class, "ESTADO");
    private final EspectroEspacial ESPECTRO_ESCANEADO = EspectroEspacial.BASE;
    private final int CANTIDAD_DE_SUSTANCIA_ENCONTRADA = 345;
    private final Pieza PIEZA_DEL_CASILLERO = mock(Pieza.class, "PIEZA_DEL_CASILLERO");
    private final Carga UNA_CARGA = mock(Carga.class, "UNA_CARGA");

    private CasilleroInterior unCasilleroInterior;

    @Test
    void escanear() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        comprobarQue(delegaEnElEstadoLaOperacionEscanear());
    }

    private Precondicion fueCreadoUnCasilleroInteriorCon(EstadoDelCasillero estado) {

        return pre(() -> {

            unCasilleroInterior = new CasilleroInterior(TABLERO, 0, 0);
            unCasilleroInterior.cambiarA(ESTADO);
            when(ESTADO.alEscanear()).thenReturn(ESPECTRO_ESCANEADO);
            when(ESTADO.alBuscar(any(SustanciaEspacial.class))).thenReturn(CANTIDAD_DE_SUSTANCIA_ENCONTRADA);
            when(ESTADO.alObtenerPieza()).thenReturn(PIEZA_DEL_CASILLERO);
        });
    }

    private Postcondicion delegaEnElEstadoLaOperacionEscanear() {

        return post(() ->

                assertThat(unCasilleroInterior.escanear())
                        .as("escanear()")
                        .isEqualTo(ESPECTRO_ESCANEADO)
        );
    }

    @Test
    void buscar() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        comprobarQue(delegaEnElEstadoLaOperacionBuscar());
    }

    private Postcondicion delegaEnElEstadoLaOperacionBuscar() {

        return post(() ->

                assertThat(unCasilleroInterior.buscar(SustanciaEspacial.METAL))
                        .as("buscar(SustanciaEspacial)")
                        .isEqualTo(CANTIDAD_DE_SUSTANCIA_ENCONTRADA)
        );
    }

    @Test
    void ocuparCon() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.ocuparCon(UNA_PIEZA);

        comprobarQue(delegoEnElEstadoLaOperacionOcuparCon());
        comprobarQue(unaPiezaEsNotificadaQueFueColocadaEnElCasillero());
    }

    private Postcondicion delegoEnElEstadoLaOperacionOcuparCon() {

        return post(() -> verify(ESTADO).alOcuparCon(UNA_PIEZA));
    }

    private Postcondicion unaPiezaEsNotificadaQueFueColocadaEnElCasillero() {

        return post(() -> verify(UNA_PIEZA).fueColocadaEn(unCasilleroInterior));
    }

    @Test
    void moverPiezaA() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.moverPiezaA(OTRO_CASILLERO);

        comprobarQue(delegoEnElEstadoLaOperacionMoverPiezaA());
    }

    private Postcondicion delegoEnElEstadoLaOperacionMoverPiezaA() {

        return post(() -> verify(ESTADO).alMoverPiezaA(OTRO_CASILLERO));
    }

    @Test
    void recibirPiezaDesde() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.recibirPiezaDesde(OTRO_CASILLERO);

        comprobarQue(delegoEnElEstadoLaOperacionRecibirPiezaDesde());
    }

    private Postcondicion delegoEnElEstadoLaOperacionRecibirPiezaDesde() {

        return post(() -> verify(ESTADO).alRecibirPiezaDesde(OTRO_CASILLERO));
    }

    @Test
    void desocupar() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.desocupar();

        comprobarQue(delegoEnElEstadoLaOperacionDesodupar());
    }

    private Postcondicion delegoEnElEstadoLaOperacionDesodupar() {

        return post(() -> verify(ESTADO).alDesocupar());
    }

    @Test
    void obtenerPieza() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        comprobarQue(delegoEnElEstadoLaOperacionObtenerPieza());
    }

    private Postcondicion delegoEnElEstadoLaOperacionObtenerPieza() {

        return post(() ->

                assertThat(unCasilleroInterior.obtenerPieza())
                        .as("obtenerPieza()")
                        .isEqualTo(PIEZA_DEL_CASILLERO)
        );
    }

    @Test
    void fueAtacadoCon() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(delegoEnElEstadoLaOperacionFueAtacadoCon());
    }

    private Postcondicion delegoEnElEstadoLaOperacionFueAtacadoCon() {

        return post(() -> verify(ESTADO).alSerAtacadoCon(UN_ATAQUE));
    }

    @Test
    void entregar() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.entregar(UNA_CARGA);

        comprobarQue(delegoEnElEstadoLaOperacionEntregar());
    }

    private Postcondicion delegoEnElEstadoLaOperacionEntregar() {

        return post(() -> verify(ESTADO).alEntregar(UNA_CARGA));
    }

    @Test
    void recibir() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.recibir(UNA_CARGA);

        comprobarQue(delegoEnElEstadoLaOperacionRecibir());
    }

    private Postcondicion delegoEnElEstadoLaOperacionRecibir() {

        return post(() -> verify(ESTADO).alRecibir(UNA_CARGA));
    }
}