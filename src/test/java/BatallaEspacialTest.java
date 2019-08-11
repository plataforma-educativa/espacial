import espacial.Tablero;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

class BatallaEspacialTest implements TestDeContrato {

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

        Nave unaNave = new Nave();

        comprobarQue(entreLasNavesDeLaBatallaEsta(unaNave));
        comprobarQue(enLaBaseEsta(unaNave));
    }

    private Precondicion fueCreadaLaBatalla() {

        return pre(condicion -> batalla = new BatallaEspacial());
    }

    private Postcondicion entreLasNavesDeLaBatallaEsta(Nave unaNave) {

        return post(condicion -> {

            condicion.es("entre las Naves de la batalla está %s", unaNave);

            assertThat(batalla.obtenerParticipantes())
                    .as("naves en la batalla")
                    .contains(unaNave);
        });
    }

    private Postcondicion enLaBaseEsta(Nave nave) {

        return post(condicion -> {

            condicion.es("en la base está la pieza");

            nave.despegar();
            nave.avanzarAlNorte();

            assertThat(batalla.obtenerTablero()).tieneNave().en(1, 0);
        });
    }

    @Test
    void intervenirConMultiplesNaves() {

        dadoQue(fueCreadaLaBatalla());

        Nave nave1 = new Nave();
        Nave nave2 = new Nave();
        Nave nave3 = new Nave();
        Nave nave4 = new Nave();

        comprobarQue(entreLasNavesDeLaBatallaEsta(nave1));
        comprobarQue(entreLasNavesDeLaBatallaEsta(nave2));
        comprobarQue(entreLasNavesDeLaBatallaEsta(nave3));
        comprobarQue(entreLasNavesDeLaBatallaEsta(nave4));
        comprobarQue(tienenNombresDiferentes(nave1, nave2, nave3, nave4));
    }

    private Postcondicion tienenNombresDiferentes(Nave... naves) {

        return post(condicion -> assertThat(naves).extracting(Nave::toString).doesNotHaveDuplicates());
    }
}
