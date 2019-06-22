import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MonitorTest implements TestDeContrato {

    private static final int CARGA_MINIMA = 1;
    private static final int CARGA_NULA = 0;

    private BatallaEspacial batallaEspacial;
    private Nave unaNave;
    private Monitor unMonitor;

    @Test
    void consultarNivelDeEscudos() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveEnLaBase());

        comprobarQue(obtieneElNivelDeEscudosInicial());
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveEnLaBase() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();
        });
    }

    private Postcondicion obtieneElNivelDeEscudosInicial() {

        return post(() ->

                assertThat(unMonitor.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(100)
        );
    }

    @Test
    void consultarNivelDeEscudosLuegoDeUnChoque() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueLuegoChocoConUnAsteroide());

        comprobarQue(obtieneElNivelDeEscudosAfectadoPorElChoque());
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueLuegoChocoConUnAsteroide() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            nave.avanzarAlNorte();
            repetir(3, i -> nave.avanzarAlOeste());
        });
    }

    private Postcondicion obtieneElNivelDeEscudosAfectadoPorElChoque() {

        return post(() ->

                assertThat(unMonitor.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isLessThan(100)
        );

    }

    @Test
    void consultarCantidadDeTorpedos() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveEnLaBase());

        comprobarQue(obtieneLaCantidadDeTorpedosInicial());
    }

    private Postcondicion obtieneLaCantidadDeTorpedosInicial() {

        return post(() ->

                assertThat(unMonitor.consultarCantidadDeTorpedos())
                        .as("cantidad de torpedos")
                        .isEqualTo(100)
        );
    }

    @Test
    void consultarCantidadDeTorpedosLuegoDeAtacar() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueLuegoHizoUnAtaque());

        comprobarQue(laCantidadDeTorpedosDisminuyoLuegoDelAtaque());
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueLuegoHizoUnAtaque() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            nave.atacarAlNorte();
        });
    }

    private Postcondicion laCantidadDeTorpedosDisminuyoLuegoDelAtaque() {

        return post(() ->

                assertThat(unMonitor.consultarCantidadDeTorpedos())
                        .as("cantidad de torpedos")
                        .isLessThan(100)
        );
    }

    @Test
    void evaluarVariable() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveEnLaBase());

        comprobarQue(alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro());
    }

    private Postcondicion alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro() {

        return post(() -> assertThat(unMonitor).hasToString("Monitor de la Nave"));
    }

    @Test
    void consultarNivelDeCarga() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaAntimateria());

        comprobarQue(elNivelDeCargaEs(CARGA_MINIMA));
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaAntimateria() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            repetir(3, i -> nave.avanzarAlNorte());
            repetir(2, i -> nave.avanzarAlEste());
            nave.cargarDesdeNorte(Sustancia.ANTIMATERIA, CARGA_MINIMA);
        });
    }

    private Postcondicion elNivelDeCargaEs(int nivelEsperado) {

        return post(() ->

                assertThat(unMonitor.consultarNivelDeCarga())
                        .as("nivel de carga")
                        .isEqualTo(nivelEsperado)
        );
    }

    @Test
    void consultarCargaDeMetal() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaMetal());

        comprobarQue(laCargaEs(CARGA_MINIMA, Sustancia.METAL));
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaMetal() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            repetir(24, i -> nave.avanzarAlOeste());
            repetir(9, i -> nave.avanzarAlNorte());
            nave.cargarDesdeNorte(Sustancia.METAL, CARGA_MINIMA);
        });
    }

    private Postcondicion laCargaEs(int cantidad, Sustancia sustancia) {

        return post(() ->

                assertThat(unMonitor.consultarCargaDe(sustancia))
                        .as("carga de " + sustancia)
                        .isEqualTo(cantidad)
        );
    }

    @Test
    void consultarCargaDeCristal() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaCristal());

        comprobarQue(laCargaEs(CARGA_MINIMA, Sustancia.CRISTAL));
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaCristal() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            repetir(9, i -> nave.avanzarAlOeste());
            repetir(2, i -> nave.avanzarAlNorte());

            while (nave.obtenerRadar().escanearOeste() == Espectro.ASTEROIDE) {
                nave.atacarAlEste();
            }
            nave.avanzarAlEste();

            nave.cargarDesdeEste(Sustancia.CRISTAL, CARGA_MINIMA);
        });
    }

    @Test
    void consultarNivelDeEscudosCuandoLaNaveFueDestruida() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueLuegoFueDestruida());

        comprobarQue(elNivelDeCargaEs(0));
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueLuegoFueDestruida() {

        return pre(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            nave.avanzarAlNorte();
            repetir(6, i -> nave.avanzarAlOeste());
        });
    }

    @Test
    void consultarCargaDeCuandoLaNaveFueDestruida() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueLuegoFueDestruida());

        comprobarQue(laCargaEs(CARGA_NULA, Sustancia.CRISTAL));
    }
}
