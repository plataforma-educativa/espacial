package espacial.tableros;

import espacial.Casillero;
import espacial.Chocable;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import espacial.excepciones.LaOperacionNoEstaSoportada;
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

        return precondicion(() -> unCasilleroBorde = new CasilleroBorde(TABLERO));
    }

    private Postcondicion alEscanearDevuelveDesconocido() {

        return postcondicion(() ->

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

        return postcondicion(() -> {

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

        return postcondicion(() -> {

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

        comprobarQue(generaExcepcionPorqueLaOperacionNoEstaSoportada(() ->

                unCasilleroBorde.moverPiezaA(OTRO_CASILLERO)
        ));
    }

    private Postcondicion generaExcepcionPorqueLaOperacionNoEstaSoportada(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(LaOperacionNoEstaSoportada.class)
        );
    }

    @Test
    void ocuparCon() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcionPorqueLaOperacionNoEstaSoportada(() ->

                unCasilleroBorde.ocuparCon(UNA_PIEZA)
        ));
    }

    @Test
    void desocupar() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(generaExcepcionPorqueLaOperacionNoEstaSoportada(() ->

                unCasilleroBorde.desocupar()
        ));
    }

    @Test
    void obtenerPieza() {

        dadoQue(fueCreadoUnCasilleroBorde());

        comprobarQue(alObtenerPiezaDevuelveNull());
    }

    private Postcondicion alObtenerPiezaDevuelveNull() {

        return postcondicion(() -> assertThat(unCasilleroBorde.obtenerPieza()).as("obtenerPieza()").isNull());
    }

    @Test
    void recibirPiezaDesde() {

        dadoQue(fueCreadoUnCasilleroBorde());
        dadoQue(otroCasilleroTieneUnaPieza());

        unCasilleroBorde.recibirPiezaDesde(OTRO_CASILLERO);

        comprobarQue(unaPiezaChocoControUnCasilleroBorde());
    }

    private Precondicion otroCasilleroTieneUnaPieza() {

        return precondicion(() -> when(OTRO_CASILLERO.obtenerPieza()).thenReturn(UNA_PIEZA));
    }

    private Postcondicion unaPiezaChocoControUnCasilleroBorde() {

        return postcondicion(() -> verify(UNA_PIEZA).chocarCon(unCasilleroBorde));
    }

    @Test
    void fueChocadaPor() {

        dadoQue(fueCreadoUnCasilleroBorde());

        unCasilleroBorde.fueChocadaPor(UN_CHOCABLE);

        comprobarQue(unChocableChocoControalElBordeDelTablero());
    }

    private Postcondicion unChocableChocoControalElBordeDelTablero() {

        return postcondicion(() -> verify(UN_CHOCABLE).chocoContraElBordeDelTablero());
    }
}