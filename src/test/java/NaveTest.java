import espacial.Casillero;
import espacial.Coordenada;
import espacial.Pieza;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

public class NaveTest implements Prueba {

    private BatallaEspacial batallaEspacial;
    private Nave unaNave;
    private Radar unRadar;
    private Monitor unMonitor;
    private Pieza asteroideAlNorte;
    private int puntosInicialesDelAsteroide;
    private Pieza contenedorAlOeste;
    private int puntosInicialesDelContenedor;
    private Pieza baseAlNorte;
    private int puntosInicialesDeLaBase;
    private Pieza naveAlEste;
    private int puntosInicialesDeOtraNave;

    @Test
    public void crearUnObjetoDeTipoNaveDejandoloEnLaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());

        Nave unaNave = new Nave();

        comprobarQue(existeEnLaBase(unaNave));
    }

    private Postcondicion existeEnLaBase(Nave unaNave) {

        return postcondicion("existe en la Base unaNave", () -> {

            assertThat(batallaEspacial.obtenerNaves())
                    .as("naves de la BatallaEspacial")
                    .hasSize(1)
                    .containsExactly(unaNave);

            assertThat(batallaEspacial.obtenerTablero()).tieneBase().en(0, 0);
        });
    }

    private Precondicion fueCreadaLaBatallaEspacial() {

        return precondicion(() -> batallaEspacial = new BatallaEspacial());
    }

    @Test
    public void crearTresObjetosDeTipoNaveDejandolosEnLaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());

        Nave primerNave = new Nave();
        Nave segundaNave = new Nave();
        Nave tercerNave = new Nave();

        comprobarQue(existenEnLaBase(primerNave, segundaNave, tercerNave));
    }

    private Postcondicion existenEnLaBase(Nave primerNave, Nave segundaNave, Nave tercerNave) {

        return postcondicion("existen en la Base tres Naves", () ->

                assertThat(batallaEspacial.obtenerNaves())
                        .as("naves de la BatallaEspacial")
                        .hasSize(3)
                        .containsExactly(primerNave, segundaNave, tercerNave)
        );
    }

    @Test
    public void noPuedeAvanzarAlNorteSiNoDespegoAntes() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        comprobarQue(generaElErrorPorqueLaNaveNoDespego(() -> unaNave.avanzarAlNorte()));
    }

    private Precondicion fueCreadaUnaNave() {

        return precondicion(() -> unaNave = new Nave());
    }

    private Postcondicion generaElErrorPorqueLaNaveNoDespego(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
        );
    }

    @Test
    public void avanzarAlNorte() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveEstaAlNorteDeLaBase());
    }

    private Precondicion fueCreadaUnaNaveQueDespegoDeLaBase() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
        });
    }

    private Postcondicion unaNaveEstaAlNorteDeLaBase() {

        return postcondicion(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(1, 0)
                        .tieneBase().en(0, 0));
    }

    @Test
    public void avanzarAlNorteTresVeces() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlNorte();
        unaNave.avanzarAlNorte();
        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveEstaDosCasillerosAlNorteDeLaBase());
    }

    private Postcondicion unaNaveEstaDosCasillerosAlNorteDeLaBase() {

        return postcondicion(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(3, 0)
                        .tieneBase().en(0, 0)
                        .tieneVacio().en(1, 0).en(2, 0)
        );
    }

    @Test
    public void avanzarAlSur() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlSur();

        comprobarQue(unaNaveEstaAlSurDeLaBase());
    }

    private Postcondicion unaNaveEstaAlSurDeLaBase() {

        return postcondicion(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(-1, 0)
                        .tieneBase().en(0, 0)
        );
    }

    @Test
    public void avanzarAlEste() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlEste();

        comprobarQue(unaNaveEstaAlEsteDeLaBase());
    }

    private Postcondicion unaNaveEstaAlEsteDeLaBase() {

        return postcondicion(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(0, 1)
                        .tieneBase().en(0, 0)
        );
    }

    @Test
    public void avanzarAlOeste() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlOeste();

        comprobarQue(unaNaveEstaAlOesteDeLaBase());
    }

    private Postcondicion unaNaveEstaAlOesteDeLaBase() {

        return postcondicion(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(0, -1)
                        .tieneBase().en(0, 0)
        );
    }

    @Test
    public void noPuedeAvanzarSiExisteUnAsteroideEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlEsteDeUnAsteroide());

        unaNave.avanzarAlOeste();

        comprobarQue(unaNaveQuedoEnElCasillero(1, -2));
        comprobarQue(unaNaveSufrioElChoqueContraElAsteroide());
    }

    private Precondicion unaNaveEstaAlEsteDeUnAsteroide() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlNorte();
            unaNave.avanzarAlOeste();
            unaNave.avanzarAlOeste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAsteroide() {

        return postcondicion(() ->

                assertThat(unaNave.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(75)
        );
    }

    @Test
    public void consultarNivelDeEscudos() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        comprobarQue(estaAlMaximoElNivelDeEscudos());
    }

    private Postcondicion estaAlMaximoElNivelDeEscudos() {

        return postcondicion(() ->

                assertThat(unaNave.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(100)
        );
    }

    @Test
    public void noPuedeAvanzarSiExisteUnContenedorEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlSurDeUnContenedor());

        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveQuedoEnElCasillero(3, 2));
        comprobarQue(unaNaveSufrioElChoqueContraElContenedor());
    }

    private Precondicion unaNaveEstaAlSurDeUnContenedor() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            IntStream.range(0, 2).forEach(n -> unaNave.avanzarAlEste());
            IntStream.range(0, 3).forEach(n -> unaNave.avanzarAlNorte());
        });
    }

    private Postcondicion unaNaveQuedoEnElCasillero(int fila, int columna) {

        return postcondicion(() -> assertThat(batallaEspacial.obtenerTablero()).tieneNave().en(fila, columna));
    }

    private Postcondicion unaNaveSufrioElChoqueContraElContenedor() {

        return postcondicion(() -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(90));
    }

    @Test
    public void noPuedeAvanzarSiElCasilleroDestinoEsElBordeDelTablero() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaEnElBordeSurDelTablero());

        unaNave.avanzarAlSur();

        comprobarQue(unaNaveQuedoEnElCasillero(-10, 0));
        comprobarQue(unaNaveSufrioElChoqueContraElBorde());
    }

    private Precondicion unaNaveEstaEnElBordeSurDelTablero() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlOeste();
            IntStream.range(0, 10).forEach(n -> unaNave.avanzarAlSur());
            unaNave.avanzarAlEste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElBorde() {

        return postcondicion(() ->

                assertThat(unaNave.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(50)
        );
    }

    @Test
    public void noPuedeAvanzarSiExisteUnAgujeroNegroEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlEsteDeUnAgujeroNegro());

        unaNave.avanzarAlOeste();

        comprobarQue(unaNaveQuedoEnElCasillero(1, -5));
        comprobarQue(unaNaveSufrioElChoqueContraElAgujeroNegro());
    }

    private Precondicion unaNaveEstaAlEsteDeUnAgujeroNegro() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            IntStream.range(0, 5).forEach(n -> unaNave.avanzarAlOeste());
            unaNave.avanzarAlNorte();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAgujeroNegro() {

        return postcondicion(() -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(25));
    }

    @Test
    public void atacarAlSurUnAsteroide() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlNorteDeUnAsteroide());
        dadoQue(seConoceLaCantidadDePuntosQueTieneElAsteroide());

        unaNave.atacarAlSur();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneElAsteroide());

    }

    private Precondicion unaNaveEstaAlNorteDeUnAsteroide() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            IntStream.range(0, 5).forEach(n -> unaNave.avanzarAlSur());
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneElAsteroide() {

        return precondicion(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(-6, 0));
            asteroideAlNorte = casillero.obtenerPieza();
            puntosInicialesDelAsteroide = asteroideAlNorte.obtenerPuntos();
        });

    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneElAsteroide() {

        return postcondicion(() ->

                assertThat(asteroideAlNorte.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDelAsteroide)
        );
    }

    @Test
    public void atacarAlOesteUnContenedor() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlEsteDeUnContenedor());
        dadoQue(seConoceLaCantidadDePuntosQueTieneElContenedor());

        unaNave.atacarAlOeste();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneElContenedor());

    }

    private Precondicion unaNaveEstaAlEsteDeUnContenedor() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            IntStream.range(0, 2).forEach(n -> unaNave.avanzarAlSur());
            unaNave.avanzarAlOeste();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneElContenedor() {

        return precondicion(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(-2, -2));
            contenedorAlOeste = casillero.obtenerPieza();
            puntosInicialesDelContenedor = contenedorAlOeste.obtenerPuntos();
        });

    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneElContenedor() {

        return postcondicion(() ->

                assertThat(contenedorAlOeste.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDelContenedor)
        );
    }

    @Test
    public void atacarAlNorteUnaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlSurDeUnaBase());
        dadoQue(seConoceLaCantidadDePuntosQueTieneLaBaseAlNorte());

        unaNave.atacarAlNorte();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneLaBaseAlNorte());

    }

    private Precondicion unaNaveEstaAlSurDeUnaBase() {

        return precondicion(() -> {

            unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlSur();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneLaBaseAlNorte() {

        return precondicion(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(0, 0));
            baseAlNorte = casillero.obtenerPieza();
            puntosInicialesDeLaBase = baseAlNorte.obtenerPuntos();
        });
    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneLaBaseAlNorte() {

        return postcondicion(() ->

                assertThat(baseAlNorte.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDeLaBase)
        );
    }

    @Test
    public void atacarAlEsteOtraNave() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlOesteDeOtraNave());
        dadoQue(seConoceLaCantidadDePuntosQueTieneLaNaveAlEste());

        unaNave.atacarAlEste();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneLaNaveAlEste());

    }

    private Precondicion unaNaveEstaAlOesteDeOtraNave() {

        return precondicion(() -> {

            Nave otraNave = new Nave();
            otraNave.despegar();
            IntStream.range(0, 2).forEach(n -> otraNave.avanzarAlEste());

            unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlEste();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneLaNaveAlEste() {

        return precondicion(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(0, 2));
            naveAlEste = casillero.obtenerPieza();
            puntosInicialesDeOtraNave = naveAlEste.obtenerPuntos();
        });
    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneLaNaveAlEste() {

        return postcondicion(() ->

                assertThat(naveAlEste.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDeOtraNave)
        );
    }

    @Test
    public void obtenerRadar() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unRadar = unaNave.obtenerRadar();

        comprobarQue(unRadarEscaneaVacioAlrededorDeUnaNave());
    }

    private Postcondicion unRadarEscaneaVacioAlrededorDeUnaNave() {

        return postcondicion(() -> {

            assertThat(unRadar).as("unRadar").isNotNull();
            assertThat(unRadar.escanearNorte()).as("escanear al NORTE").isEqualTo(Espectro.VACIO);
            assertThat(unRadar.escanearSur()).as("escanear al SUR").isEqualTo(Espectro.VACIO);
            assertThat(unRadar.escanearEste()).as("escanear al ESTE").isEqualTo(Espectro.VACIO);
            assertThat(unRadar.escanearOeste()).as("escanear al OESTE").isEqualTo(Espectro.VACIO);
        });
    }

    @Test
    public void obtenerMonitor() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unMonitor = unaNave.obtenerMonitor();

        comprobarQue(unMonitorTieneElEstadoDeUnaNave());
    }

    private Postcondicion unMonitorTieneElEstadoDeUnaNave() {

        return postcondicion(() -> assertThat(unMonitor).as("unMonitor").isNotNull());
    }
}
