package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.Coordenadas;
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
    void crearConCoordenadasDeTablero() {
        
        dadoQue(fueCreadoUnCasilleroInteriorEn(TABLERO, 2, 4));
        
        comprobarQue(elCasilleroTiene(TABLERO, Coordenadas.con(2, 4)));
    }

    private Precondicion fueCreadoUnCasilleroInteriorEn(Tablero tablero, int fila, int columna) {

        return pre(condicion ->  unCasilleroInterior = new CasilleroInterior(tablero, fila, columna));
    }

    private Postcondicion elCasilleroTiene(Tablero tablero, Coordenadas coordenadas) {
        
        return post(condicion ->  {
           
            assertThat(unCasilleroInterior.obtenerTablero()).as("tablero").isSameAs(tablero);
            assertThat(unCasilleroInterior.obtenerCoordenadas()).as("coordenaadas").isEqualTo(coordenadas);
        });
    }

    @Test
    void escanear() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        comprobarQue(delegaEnElEstadoLaOperacionEscanear());
    }

    private Precondicion fueCreadoUnCasilleroInteriorCon(EstadoDelCasillero estado) {

        return pre(condicion ->  {

            unCasilleroInterior = new CasilleroInterior(TABLERO, 0, 0);
            unCasilleroInterior.cambiarA(ESTADO);
            when(ESTADO.alEscanear()).thenReturn(ESPECTRO_ESCANEADO);
            when(ESTADO.alBuscar(any(SustanciaEspacial.class))).thenReturn(CANTIDAD_DE_SUSTANCIA_ENCONTRADA);
            when(ESTADO.alObtenerPieza()).thenReturn(PIEZA_DEL_CASILLERO);
        });
    }

    private Postcondicion delegaEnElEstadoLaOperacionEscanear() {

        return post(condicion ->

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

        return post(condicion ->

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
    }

    private Postcondicion delegoEnElEstadoLaOperacionOcuparCon() {

        return post(condicion ->  verify(ESTADO).alOcuparCon(UNA_PIEZA));
    }

    @Test
    void moverPiezaA() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.moverPiezaA(OTRO_CASILLERO);

        comprobarQue(delegoEnElEstadoLaOperacionMoverPiezaA());
    }

    private Postcondicion delegoEnElEstadoLaOperacionMoverPiezaA() {

        return post(condicion ->  verify(ESTADO).alMoverPiezaA(OTRO_CASILLERO));
    }

    @Test
    void recibirPiezaDesde() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.recibirPiezaDesde(OTRO_CASILLERO);

        comprobarQue(delegoEnElEstadoLaOperacionRecibirPiezaDesde());
    }

    private Postcondicion delegoEnElEstadoLaOperacionRecibirPiezaDesde() {

        return post(condicion ->  verify(ESTADO).alRecibirPiezaDesde(OTRO_CASILLERO));
    }

    @Test
    void desocupar() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.desocupar();

        comprobarQue(delegoEnElEstadoLaOperacionDesodupar());
    }

    private Postcondicion delegoEnElEstadoLaOperacionDesodupar() {

        return post(condicion ->  verify(ESTADO).alDesocupar());
    }

    @Test
    void obtenerPieza() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        comprobarQue(delegoEnElEstadoLaOperacionObtenerPieza());
    }

    private Postcondicion delegoEnElEstadoLaOperacionObtenerPieza() {

        return post(condicion ->

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

        return post(condicion ->  verify(ESTADO).alSerAtacadoCon(UN_ATAQUE));
    }

    @Test
    void entregar() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.entregar(UNA_CARGA);

        comprobarQue(delegoEnElEstadoLaOperacionEntregar());
    }

    private Postcondicion delegoEnElEstadoLaOperacionEntregar() {

        return post(condicion ->  verify(ESTADO).alEntregar(UNA_CARGA));
    }

    @Test
    void recibir() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        unCasilleroInterior.recibir(UNA_CARGA);

        comprobarQue(delegoEnElEstadoLaOperacionRecibir());
    }

    private Postcondicion delegoEnElEstadoLaOperacionRecibir() {

        return post(condicion ->  verify(ESTADO).alRecibir(UNA_CARGA));
    }

    @Test
    void tieneToString() {

        dadoQue(fueCreadoUnCasilleroInteriorCon(ESTADO));

        comprobarQue(esAutoDescriptivo());
    }

    private Postcondicion esAutoDescriptivo() {

        return post(condicion ->  assertThat(unCasilleroInterior).hasToString("Casillero[0][0] -> BASE"));
    }
}