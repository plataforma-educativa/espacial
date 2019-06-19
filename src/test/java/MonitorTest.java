import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class MonitorTest implements TestDeContrato {

    private BatallaEspacial batallaEspacial;
    private Nave unaNave;
    private Monitor unMonitor;

    @Test
    void consultarNivelDeEscudos() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveEnLaBase());

        comprobarQue(obtieneElNivelDeEscudosInicial());
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveEnLaBase() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();
        });
    }

    private Postcondicion obtieneElNivelDeEscudosInicial() {

        return postcondicion(() ->

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

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            nave.avanzarAlNorte();
            IntStream.range(0, 3).forEach(n -> nave.avanzarAlOeste());
        });
    }

    private Postcondicion obtieneElNivelDeEscudosAfectadoPorElChoque() {

        return postcondicion(() ->

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

        return postcondicion(() ->

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

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            nave.atacarAlNorte();
        });
    }

    private Postcondicion laCantidadDeTorpedosDisminuyoLuegoDelAtaque() {

        return postcondicion(() ->

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

        return postcondicion(() -> assertThat(unMonitor).hasToString("Monitor de la Nave"));
    }

    @Test
    void consultarNivelDeCarga() {

        dadoQue(fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaAntimateria());

        comprobarQue(elNivelDeCargaEs(1));
    }

    private Precondicion fueObtenidoUnMonitorDeUnaNaveQueTieneCargadaAntimateria() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unMonitor = nave.obtenerMonitor();

            IntStream.range(0, 3).forEach(n -> nave.avanzarAlNorte());
            IntStream.range(0, 2).forEach(n -> nave.avanzarAlEste());
            nave.cargarDesdeNorte(Sustancia.ANTIMATERIA, 1);
        });
    }

    private Postcondicion elNivelDeCargaEs(int nivelEsperado) {

        return postcondicion(() ->

                assertThat(unMonitor.consultarNivelDeCarga())
                        .as("nivel de carga")
                        .isEqualTo(nivelEsperado)
        );
    }
}
