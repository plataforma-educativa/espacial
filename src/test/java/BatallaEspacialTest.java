import espacial.Direccion;
import espacial.NaveEspacial;
import espacial.Tablero;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BatallaEspacialTest implements TestDeContrato {

    private final Nave UNA_NAVE = mock(Nave.class, "UNA_NAVE");

    private BatallaEspacial batalla;

    @Test
    void crearUnObjetoDeTipoBatallaEspacialDejandolaRegistrada() {

        BatallaEspacial batallaEspacial = new BatallaEspacial();

        comprobarQue(quedoRegistrada(batallaEspacial));
    }

    private Postcondicion quedoRegistrada(BatallaEspacial objeto) {

        return post(condicion -> assertThat(BatallaEspacial.obtener()).isSameAs(objeto));
    }

    @Test
    void evaluarVariable() {

        dadoQue(fueCreadaLaBatalla());

        comprobarQue(alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro());
    }

    private Postcondicion alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro() {

        return post(condicion -> assertThat(batalla).hasToString("Batalla Espacial"));
    }

    @Test
    void obtenerTablero() {

        BatallaEspacial batalla = new BatallaEspacial();

        Tablero tablero = batalla.obtenerTablero();

        comprobarQue(fueInicializadoEl(tablero));
    }

    private Postcondicion fueInicializadoEl(Tablero tablero) {

        return post(condicion -> {

            condicion.es("fue inicializado el tablero con las dimensiones correctas");

            assertThat(tablero.contarFilas()).as("filas del Tablero").isEqualTo(21);
            assertThat(tablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
            assertThat(tablero.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);

            assertThat(tablero.contarColumnas()).as("columnas del Tablero").isEqualTo(53);
            assertThat(tablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(26);
            assertThat(tablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-26);

            condicion.es("fue inicializado el tablero con las piezas esperadas");

            assertThat(tablero)
                    .tieneBase().esAliado()
                    .en(0, 0)
                    .tieneVacio()
                    .en(0, 1).en(0, -1)
                    .en(1, 0).en(-1, -0)
                    .en(1, 1).en(-1, -1).en(1, -1).en(-1, 1)
                    .tieneContenedor().conAntimateria()
                    .en(-2, -2)
                    .en(4, 2)
                    .tieneContenedor().conCristal()
                    .en(2, -7)
                    .tieneContenedor().conMetal()
                    .en(10, -24)
                    .tieneAsteroide()
                    .en(1, -3)
                    .entre(7, -1, 7, 0)
                    .entre(8, -3, 8, 3)
                    .en(-6, 0)
                    .en(-9, 0)
                    .en(-2, 4)
                    .en(2, 6)
                    .en(2, -5)
                    .tieneAgujeroNegro()
                    .en(3, -6)
                    .en(2, -6)
                    .en(1, -6)
                    .en(1, -7)
                    .tieneBase().esNeutral()
                    .en(-10, 14)
                    .yTieneVacioEnElResto();
        });
    }

    @Test
    void obtenerTableroSiempreDevuelveElMismoTablero() {

        BatallaEspacial batalla = new BatallaEspacial();

        Tablero tablero = batalla.obtenerTablero();

        comprobarQue(devuelveElMismoTablero(batalla, tablero));
    }

    private Postcondicion devuelveElMismoTablero(BatallaEspacial batalla, Tablero tablero) {

        return post(condicion -> {

            assertThat(tablero).isSameAs(batalla.obtenerTablero());
            assertThat(tablero).isSameAs(batalla.obtenerTablero());
        });
    }

    @Test
    void intervenirConNave() {

        dadoQue(fueCreadaLaBatalla());

        NaveEspacial piezaResultante = batalla.intervenirCon(UNA_NAVE);

        comprobarQue(entreLasNavesDeLaBatallaEsta(UNA_NAVE));
        comprobarQue(enLaBaseEsta(piezaResultante));
        comprobarQue(tieneNombre(piezaResultante));
    }

    private Precondicion fueCreadaLaBatalla() {

        return pre(condicion -> batalla = new BatallaEspacial());
    }

    private Postcondicion entreLasNavesDeLaBatallaEsta(Nave unaNave) {

        return post(condicion -> {

            condicion.es("entre las Naves de la batalla está %s", unaNave);

            assertThat(batalla.obtenerNaves())
                    .as("naves en la batalla")
                    .contains(unaNave);
        });
    }

    private Postcondicion enLaBaseEsta(NaveEspacial pieza) {

        return post(condicion -> {

            condicion.es("en la base está la pieza");

            assertThat(pieza).as("pieza").isNotNull();

            pieza.despegar();
            pieza.moverEn(Direccion.NORTE);

            assertThat(batalla.obtenerTablero()).tieneNave().en(1, 0);
        });
    }

    private Postcondicion tieneNombre(NaveEspacial pieza) {

        return post(condicion -> assertThat(pieza.nombrar()).as("nombre").isNotNull());
    }

    @Test
    void intervenirConMultiplesNaves() {

        final Nave nave1 = mock(Nave.class, "NAVE 1");
        final Nave nave2 = mock(Nave.class, "NAVE 2");
        final Nave nave3 = mock(Nave.class, "NAVE 3");
        final Nave nave4 = mock(Nave.class, "NAVE 4");

        dadoQue(fueCreadaLaBatalla());

        NaveEspacial pieza1 = batalla.intervenirCon(nave1);
        NaveEspacial pieza2 = batalla.intervenirCon(nave2);
        NaveEspacial pieza3 = batalla.intervenirCon(nave3);
        NaveEspacial pieza4 = batalla.intervenirCon(nave4);

        comprobarQue(entreLasNavesDeLaBatallaEsta(nave1));
        comprobarQue(entreLasNavesDeLaBatallaEsta(nave2));
        comprobarQue(entreLasNavesDeLaBatallaEsta(nave3));
        comprobarQue(entreLasNavesDeLaBatallaEsta(nave4));
        comprobarQue(tienenNombresDiferentes(pieza1, pieza2, pieza3, pieza4));
    }

    private Postcondicion tienenNombresDiferentes(NaveEspacial... piezas) {

        return post(condicion -> assertThat(piezas).extracting(NaveEspacial::nombrar).doesNotHaveDuplicates());
    }
}
