import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class RadarTest implements Prueba {

    private Radar unRadar;
    private Espectro escaneadoAlNorte;
    private Espectro escaneadoAlSur;
    private Espectro escaneadoAlEste;
    private Espectro escaneadoAlOeste;

    @Test
    public void escanarSinHaberDespegado() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveQueNoDespego());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unRadar.escanearNorte()));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveQueNoDespego() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();

            unRadar = nave.obtenerRadar();
        });
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
        );
    }

    @Test
    public void escanearEnLaBase() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveEnLaBase());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.VACIO, Espectro.VACIO, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveEnLaBase() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unRadar = nave.obtenerRadar();
        });
    }

    private void escanearConUnRadarAlrededor() {

        escaneadoAlNorte = unRadar.escanearNorte();
        escaneadoAlSur = unRadar.escanearSur();
        escaneadoAlEste = unRadar.escanearEste();
        escaneadoAlOeste = unRadar.escanearOeste();
    }

    private Postcondicion losEspectrosEscaneadosSonLosEsperados(Espectro alNorte, Espectro alSur,
                                                                Espectro alEste, Espectro alOeste) {

        return postcondicion(() -> {

            assertThat(escaneadoAlNorte).as("espectro escaneado al NORTE").isEqualTo(alNorte);
            assertThat(escaneadoAlSur).as("espectro escaneado al SUR").isEqualTo(alSur);
            assertThat(escaneadoAlEste).as("espectro escaneado al ESTE").isEqualTo(alEste);
            assertThat(escaneadoAlOeste).as("espectro escaneado al OESTE").isEqualTo(alOeste);
        });
    }


    @Test
    public void escanearAlOesteDeUnContenedor() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedor());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(
                Espectro.VACIO,
                Espectro.VACIO,
                Espectro.CONTENEDOR,
                Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedor() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 4).forEach(n -> nave.avanzarAlNorte());
            nave.avanzarAlEste();

            unRadar = nave.obtenerRadar();
        });
    }

    @Test
    public void escanearAlNorteDeUnaBase() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlNorteDeUnaBase());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.VACIO, Espectro.BASE, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlNorteDeUnaBase() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            nave.avanzarAlNorte();

            unRadar = nave.obtenerRadar();
        });
    }

    @Test
    public void escanearAlSurDeUnAsteroide() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlSurDeUnAsteroide());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.ASTEROIDE, Espectro.VACIO, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlSurDeUnAsteroide() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 3).forEach(n -> nave.avanzarAlOeste());

            unRadar = nave.obtenerRadar();
        });
    }

    @Test
    public void escanearAlEsteDeDesconocido() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlEsteDeDesconocido());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.ASTEROIDE, Espectro.VACIO, Espectro.VACIO, Espectro.DESCONOCIDO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlEsteDeDesconocido() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 5).forEach(n -> nave.avanzarAlOeste());
            nave.avanzarAlNorte();

            unRadar = nave.obtenerRadar();
        });
    }
}
