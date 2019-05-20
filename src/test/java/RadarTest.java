
import static org.assertj.core.api.Assertions.*;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class RadarTest implements Prueba {

    private Radar unRadar;
    private Espectro escaneadoAlNorte;
    private Espectro escaneadoAlSur;
    private Espectro escaneadoAlEste;
    private Espectro escaneadoAlOeste;

    @Test
    public void escanearEnLaBase() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveEnLaBase());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSon(Espectro.VACIO, Espectro.VACIO, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveEnLaBase() {

        return precondicion("fue obtenido unRadar de unaNave en la Base", () -> {

            BatallaEspacial batalla  = new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unRadar = nave.obtenerRadar();
        });
    }

    private void escanearConUnRadarAlrededor() {

        escaneadoAlNorte = unRadar.escanearNorte();
        escaneadoAlSur   = unRadar.escanearSur();
        escaneadoAlEste  = unRadar.escanearEste();
        escaneadoAlOeste = unRadar.escanearOeste();
    }

    private Postcondicion losEspectrosEscaneadosSon(Espectro alNorte, Espectro alSur,
                                                    Espectro alEste, Espectro alOeste) {

        return postcondicion("los espectros escaneados son los esperados", () -> {

            assertThat(escaneadoAlNorte).as("espectro escaneado al NORTE").isEqualTo(alNorte);
            assertThat(escaneadoAlSur  ).as("espectro escaneado al SUR"  ).isEqualTo(alSur);
            assertThat(escaneadoAlEste ).as("espectro escaneado al ESTE" ).isEqualTo(alEste);
            assertThat(escaneadoAlOeste).as("espectro escaneado al OESTE").isEqualTo(alOeste);
        });
    }


    @Test
    public void escanearAlOesteDeUnContenedor() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedor());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSon(Espectro.VACIO, Espectro.VACIO, Espectro.CONTENEDOR, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedor() {

        return precondicion("fue obtenido unRadar de unaNave al OESTE de un CONTENEDOR", () -> {

            BatallaEspacial batalla  = new BatallaEspacial();
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

        comprobarQue(losEspectrosEscaneadosSon(Espectro.VACIO, Espectro.BASE, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlNorteDeUnaBase() {

        return precondicion("fue obtenido unRadar de unaNave al NORTE de una BASE", () -> {

            BatallaEspacial batalla  = new BatallaEspacial();
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

        comprobarQue(losEspectrosEscaneadosSon(Espectro.ASTEROIDE, Espectro.VACIO, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlSurDeUnAsteroide() {

        return precondicion("fue obtenido unRadar de unaNave al SUR de un ASTEROIDE", () -> {

            BatallaEspacial batalla  = new BatallaEspacial();
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

        comprobarQue(losEspectrosEscaneadosSon(Espectro.ASTEROIDE, Espectro.VACIO, Espectro.VACIO, Espectro.DESCONOCIDO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlEsteDeDesconocido() {

        return precondicion("fue obtenido unRadar de unaNave al ESTE de DESCONOCIDO", () -> {

            BatallaEspacial batalla  = new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 5).forEach(n -> nave.avanzarAlOeste());
            nave.avanzarAlNorte();

            unRadar = nave.obtenerRadar();
        });
    }

}
