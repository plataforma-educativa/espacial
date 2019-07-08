package espacial.tableros;

import espacial.Casillero;
import espacial.Chocable;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class CasilleroBordeTest implements TestDeContrato {

    private final Tablero TABLERO = mock(Tablero.class, "TABLERO");
    private final Casillero OTRO_CASILLERO = mock(Casillero.class, "OTRO_CASILLERO");
    private final Pieza UNA_PIEZA = mock(Pieza.class, "UNA_PIEZA");
    private final Chocable UN_CHOCABLE = mock(Chocable.class, "UN_CHOCABLE");
    private CasilleroBorde unCasilleroBorde;

    @Test
    void escanear() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(alEscanearDevuelveDesconocido());
    }

    private Precondicion fueCreadoUnCasilleroBorde() {

        return pre(() -> unCasilleroBorde = new CasilleroBorde(TABLERO));
    }

    private Postcondicion alEscanearDevuelveDesconocido() {

        return post(() ->

                assertThat(unCasilleroBorde.escanear())
                        .as("escanear()")
                        .isEqualTo(EspectroEspacial.DESCONOCIDO)
        );
    }

    @Test
    void buscar() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(alBuscarCualquierSustanciaDevuelve0());
    }

    private Postcondicion alBuscarCualquierSustanciaDevuelve0() {

        return post(() -> {

            for (SustanciaEspacial sustancia : SustanciaEspacial.values()) {

                assertThat(unCasilleroBorde.buscar(sustancia))
                        .as("buscar(" + sustancia + ")")
                        .isEqualTo(0);
            }
        });
    }

    @Test
    void obtenerContiguoEn() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(alObtenerContiguoEnCualquierDireccionDevuelveElMismoCasillero());
    }

    private Postcondicion alObtenerContiguoEnCualquierDireccionDevuelveElMismoCasillero() {

        return post(() -> {

            for (Direccion direccion : Direccion.values()) {

                assertThat(unCasilleroBorde.obtenerContiguoEn(direccion))
                        .as("obtenerContiguoEn(" + direccion + ")")
                        .isSameAs(unCasilleroBorde);
            }
        });
    }

    @Test
    void moverPiezaA() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcion(LaOperacionNoEstaSoportada.class, () ->

                unCasilleroBorde.moverPiezaA(OTRO_CASILLERO)
        ));
    }

    @Test
    void ocuparCon() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcion(LaOperacionNoEstaSoportada.class, () ->

                unCasilleroBorde.ocuparCon(UNA_PIEZA)
        ));
    }

    @Test
    void desocupar() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcion(LaOperacionNoEstaSoportada.class, () ->

                unCasilleroBorde.desocupar()
        ));
    }

    @Test
    void obtenerPieza() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(alObtenerPiezaDevuelveNull());
    }

    private Postcondicion alObtenerPiezaDevuelveNull() {

        return post(() -> assertThat(unCasilleroBorde.obtenerPieza()).as("obtenerPieza()").isNull());
    }

    @Test
    void recibirPiezaDesde() {

        dadoQue(fueCreadoUnCasilleroBorde());
        dadoQue(otroCasilleroTieneUnaPieza());

        unCasilleroBorde.recibirPiezaDesde(OTRO_CASILLERO);

        comprobarQue(unaPiezaChocoControUnCasilleroBorde());
    }

    private Precondicion otroCasilleroTieneUnaPieza() {

        return pre(() -> when(OTRO_CASILLERO.obtenerPieza()).thenReturn(UNA_PIEZA));
    }

    private Postcondicion unaPiezaChocoControUnCasilleroBorde() {

        return post(() -> verify(UNA_PIEZA).chocarCon(unCasilleroBorde));
    }

    @Test
    void fueChocadaPor() {

        dadoQue(fueCreadoUnCasilleroBorde());

        unCasilleroBorde.fueChocadaPor(UN_CHOCABLE);

        comprobarQue(unChocableChocoControalElBordeDelTablero());
    }

    private Postcondicion unChocableChocoControalElBordeDelTablero() {

        return post(() -> verify(UN_CHOCABLE).chocoContraElBordeDelTablero());
    }

    @Test
    void entregar() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcion(NoPuedeEntregarUnaCarga.class, () ->

                unCasilleroBorde.entregar(SustanciaEspacial.ANTIMATERIA.por(1))
        ));
    }

    @Test
    void recibir() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcion(NoPuedeRecibirUnaCarga.class, () ->

                unCasilleroBorde.recibir(SustanciaEspacial.METAL.por(1))
        ));
    }

    private Postcondicion generaExcepcion(Class<?> claseExcepcion, Ejecutable ejecutable) {

        return post(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(claseExcepcion)
        );
    }

    @Test
    void tieneToString() {

        final int fila = -100;
        final int columna = 800;

        dadoQue(fueCreadoUnCasilleroBordeEn(fila, columna));

        comprobarQue(esAutoDescriptivo(fila, columna));
    }

    private Precondicion fueCreadoUnCasilleroBordeEn(int fila, int columna) {

        return pre(() -> unCasilleroBorde = new CasilleroBorde(TABLERO, fila, columna));
    }

    private Postcondicion esAutoDescriptivo(int fila, int columna) {

        return post(() -> assertThat(unCasilleroBorde).hasToString("Casillero[" + fila + "][" + columna + "] -> BORDE"));
    }
}