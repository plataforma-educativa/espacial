
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class MonitorTest implements Prueba {

    private BatallaEspacial batallaEspacial;
    private Nave unaNave;
    private Monitor unMonitor;

    @Test
    public void consultarNivelDeEscudos() {

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
    public void consultarNivelDeEscudosLuegoDeUnChoque() {

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
    public void consultarCantidadDeTorpedos() {

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
    public void consultarCantidadDeTorpedosLuegoDeAtacar() {

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

}
