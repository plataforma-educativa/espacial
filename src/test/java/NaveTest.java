import espacial.Casillero;
import espacial.Coordenada;
import espacial.Pieza;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

class NaveTest implements TestDeContrato {

    private static final int CANTIDAD_MINIMA = 1;

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
    void crearUnObjetoDeTipoNaveDejandoloEnLaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());

        Nave unaNave = new Nave();

        comprobarQue(existeEnLaBase(unaNave));
    }

    private Postcondicion existeEnLaBase(Nave unaNave) {

        return post("existe en la Base unaNave", () -> {

            assertThat(batallaEspacial.obtenerNaves())
                    .as("naves de la BatallaEspacial")
                    .hasSize(1)
                    .containsExactly(unaNave);

            assertThat(batallaEspacial.obtenerTablero()).tieneBase().en(0, 0);
        });
    }

    private Precondicion fueCreadaLaBatallaEspacial() {

        return pre(() -> batallaEspacial = new BatallaEspacial());
    }

    @Test
    void crearTresObjetosDeTipoNaveDejandolosEnLaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());

        Nave primerNave = new Nave();
        Nave segundaNave = new Nave();
        Nave tercerNave = new Nave();

        comprobarQue(existenEnLaBase(primerNave, segundaNave, tercerNave));
    }

    private Postcondicion existenEnLaBase(Nave primerNave, Nave segundaNave, Nave tercerNave) {

        return post("existen en la Base tres Naves", () ->

                assertThat(batallaEspacial.obtenerNaves())
                        .as("naves de la BatallaEspacial")
                        .hasSize(3)
                        .containsExactly(primerNave, segundaNave, tercerNave)
        );
    }

    @Test
    void noPuedeAvanzarAlNorteSiNoDespegoAntes() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        comprobarQue(generaElErrorPorqueLaNaveNoDespego(() -> unaNave.avanzarAlNorte()));
    }

    private Precondicion fueCreadaUnaNave() {

        return pre(() -> unaNave = new Nave());
    }

    private Postcondicion generaElErrorPorqueLaNaveNoDespego(Ejecutable ejecutable) {

        return post(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
        );
    }

    @Test
    void avanzarAlNorte() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveEstaAlNorteDeLaBase());
    }

    private Precondicion fueCreadaUnaNaveQueDespegoDeLaBase() {

        return pre(() -> {

            unaNave = new Nave();
            unaNave.despegar();
        });
    }

    private Postcondicion unaNaveEstaAlNorteDeLaBase() {

        return post(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(1, 0)
                        .tieneBase().en(0, 0));
    }

    @Test
    void avanzarAlNorteTresVeces() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlNorte();
        unaNave.avanzarAlNorte();
        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveEstaDosCasillerosAlNorteDeLaBase());
    }

    private Postcondicion unaNaveEstaDosCasillerosAlNorteDeLaBase() {

        return post(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(3, 0)
                        .tieneBase().en(0, 0)
                        .tieneVacio().en(1, 0).en(2, 0)
        );
    }

    @Test
    void avanzarAlSur() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlSur();

        comprobarQue(unaNaveEstaAlSurDeLaBase());
    }

    private Postcondicion unaNaveEstaAlSurDeLaBase() {

        return post(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(-1, 0)
                        .tieneBase().en(0, 0)
        );
    }

    @Test
    void avanzarAlEste() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlEste();

        comprobarQue(unaNaveEstaAlEsteDeLaBase());
    }

    private Postcondicion unaNaveEstaAlEsteDeLaBase() {

        return post(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(0, 1)
                        .tieneBase().en(0, 0)
        );
    }

    @Test
    void avanzarAlOeste() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlOeste();

        comprobarQue(unaNaveEstaAlOesteDeLaBase());
    }

    private Postcondicion unaNaveEstaAlOesteDeLaBase() {

        return post(() ->

                assertThat(batallaEspacial.obtenerTablero())
                        .tieneNave().en(0, -1)
                        .tieneBase().en(0, 0)
        );
    }

    @Test
    void noPuedeAvanzarSiExisteUnAsteroideEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlEsteDeUnAsteroide());

        unaNave.avanzarAlOeste();

        comprobarQue(unaNaveQuedoEnElCasillero(1, -2));
        comprobarQue(unaNaveSufrioElChoqueContraElAsteroide());
    }

    private Precondicion unaNaveEstaAlEsteDeUnAsteroide() {

        return pre(() -> {

            unaNave.avanzarAlNorte();
            unaNave.avanzarAlOeste();
            unaNave.avanzarAlOeste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAsteroide() {

        return post(() ->

                assertThat(unaNave.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(75)
        );
    }

    @Test
    void consultarNivelDeEscudos() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        comprobarQue(estaAlMaximoElNivelDeEscudos());
    }

    private Postcondicion estaAlMaximoElNivelDeEscudos() {

        return post(() ->

                assertThat(unaNave.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(100)
        );
    }

    @Test
    void noPuedeAvanzarSiExisteUnContenedorEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlSurDeUnContenedor());

        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveQuedoEnElCasillero(3, 2));
        comprobarQue(unaNaveSufrioElChoqueContraElContenedor());
    }

    private Precondicion unaNaveEstaAlSurDeUnContenedor() {

        return pre(() -> {

            IntStream.range(0, 2).forEach(n -> unaNave.avanzarAlEste());
            IntStream.range(0, 3).forEach(n -> unaNave.avanzarAlNorte());
        });
    }

    private Postcondicion unaNaveQuedoEnElCasillero(int fila, int columna) {

        return post(() -> assertThat(batallaEspacial.obtenerTablero()).tieneNave().en(fila, columna));
    }

    private Postcondicion unaNaveSufrioElChoqueContraElContenedor() {

        return post(() -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(90));
    }

    @Test
    void noPuedeAvanzarSiElCasilleroDestinoEsElBordeDelTablero() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaEnElBordeSurDelTablero());

        unaNave.avanzarAlSur();

        comprobarQue(unaNaveQuedoEnElCasillero(-10, 0));
        comprobarQue(unaNaveSufrioElChoqueContraElBorde());
    }

    private Precondicion unaNaveEstaEnElBordeSurDelTablero() {

        return pre(() -> {

            unaNave.avanzarAlOeste();
            IntStream.range(0, 10).forEach(n -> unaNave.avanzarAlSur());
            unaNave.avanzarAlEste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElBorde() {

        return post(() ->

                assertThat(unaNave.consultarNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(50)
        );
    }

    @Test
    void noPuedeAvanzarSiExisteUnAgujeroNegroEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlEsteDeUnAgujeroNegro());

        unaNave.avanzarAlOeste();

        comprobarQue(unaNaveQuedoEnElCasillero(1, -5));
        comprobarQue(unaNaveSufrioElChoqueContraElAgujeroNegro());
    }

    private Precondicion unaNaveEstaAlEsteDeUnAgujeroNegro() {

        return pre(() -> {

            IntStream.range(0, 5).forEach(n -> unaNave.avanzarAlOeste());
            unaNave.avanzarAlNorte();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAgujeroNegro() {

        return post(() -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(25));
    }

    @Test
    void atacarAlSurUnAsteroide() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlNorteDeUnAsteroide());
        dadoQue(seConoceLaCantidadDePuntosQueTieneElAsteroide());

        unaNave.atacarAlSur();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneElAsteroide());

    }

    private Precondicion unaNaveEstaAlNorteDeUnAsteroide() {

        return pre(() -> {

            IntStream.range(0, 5).forEach(n -> unaNave.avanzarAlSur());
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneElAsteroide() {

        return pre(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(-6, 0));
            asteroideAlNorte = casillero.obtenerPieza();
            puntosInicialesDelAsteroide = asteroideAlNorte.obtenerPuntos();
        });

    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneElAsteroide() {

        return post(() ->

                assertThat(asteroideAlNorte.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDelAsteroide)
        );
    }

    @Test
    void atacarAlOesteUnContenedor() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlEsteDeUnContenedor());
        dadoQue(seConoceLaCantidadDePuntosQueTieneElContenedor());

        unaNave.atacarAlOeste();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneElContenedor());

    }

    private Precondicion unaNaveEstaAlEsteDeUnContenedor() {

        return pre(() -> {

            IntStream.range(0, 2).forEach(n -> unaNave.avanzarAlSur());
            unaNave.avanzarAlOeste();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneElContenedor() {

        return pre(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(-2, -2));
            contenedorAlOeste = casillero.obtenerPieza();
            puntosInicialesDelContenedor = contenedorAlOeste.obtenerPuntos();
        });

    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneElContenedor() {

        return post(() ->

                assertThat(contenedorAlOeste.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDelContenedor)
        );
    }

    @Test
    void atacarAlNorteUnaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlSurDeUnaBase());
        dadoQue(seConoceLaCantidadDePuntosQueTieneLaBaseAlNorte());

        unaNave.atacarAlNorte();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneLaBaseAlNorte());

    }

    private Precondicion unaNaveEstaAlSurDeUnaBase() {

        return pre(() -> {

            unaNave.avanzarAlSur();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneLaBaseAlNorte() {

        return pre(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(0, 0));
            baseAlNorte = casillero.obtenerPieza();
            puntosInicialesDeLaBase = baseAlNorte.obtenerPuntos();
        });
    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneLaBaseAlNorte() {

        return post(() ->

                assertThat(baseAlNorte.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDeLaBase)
        );
    }

    @Test
    void atacarAlEsteOtraNave() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlOesteDeOtraNave());
        dadoQue(seConoceLaCantidadDePuntosQueTieneLaNaveAlEste());

        unaNave.atacarAlEste();

        comprobarQue(disminuyoLaCantidadDePuntosQueTieneLaNaveAlEste());

    }

    private Precondicion unaNaveEstaAlOesteDeOtraNave() {

        return pre(() -> {

            Nave otraNave = new Nave();
            otraNave.despegar();
            IntStream.range(0, 2).forEach(n -> otraNave.avanzarAlEste());

            unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlEste();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneLaNaveAlEste() {

        return pre(() -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenada.con(0, 2));
            naveAlEste = casillero.obtenerPieza();
            puntosInicialesDeOtraNave = naveAlEste.obtenerPuntos();
        });
    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneLaNaveAlEste() {

        return post(() ->

                assertThat(naveAlEste.obtenerPuntos())
                        .as("puntos")
                        .isLessThan(puntosInicialesDeOtraNave)
        );
    }

    @Test
    void obtenerRadar() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unRadar = unaNave.obtenerRadar();

        comprobarQue(unRadarEscaneaVacioAlrededorDeUnaNave());
    }

    private Postcondicion unRadarEscaneaVacioAlrededorDeUnaNave() {

        return post(() -> {

            assertThat(unRadar).as("unRadar").isNotNull();
            assertThat(unRadar.escanearNorte()).as("escanear al NORTE").isEqualTo(Espectro.VACIO);
            assertThat(unRadar.escanearSur()).as("escanear al SUR").isEqualTo(Espectro.VACIO);
            assertThat(unRadar.escanearEste()).as("escanear al ESTE").isEqualTo(Espectro.VACIO);
            assertThat(unRadar.escanearOeste()).as("escanear al OESTE").isEqualTo(Espectro.VACIO);
        });
    }

    @Test
    void obtenerMonitor() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unMonitor = unaNave.obtenerMonitor();

        comprobarQue(unMonitorTieneElEstadoDeUnaNave());
    }

    private Postcondicion unMonitorTieneElEstadoDeUnaNave() {

        return post(() -> {

            assertThat(unMonitor).as("unMonitor").isNotNull();
            assertThat(unMonitor.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(100);
            assertThat(unMonitor.consultarCantidadDeTorpedos()).as("cantidad de torpedos").isEqualTo(100);
        });
    }

    @Test
    void evaluarVariable() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        comprobarQue(alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro());
    }

    private Postcondicion alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro() {

        return post(() -> assertThat(unaNave).hasToString("Nave a la espera de comandos"));
    }

    @Test
    void cargarDesdeNorteAntimateria() {

        final int cantidad = 1;
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlSurDeUnContenedor());

        unaNave.cargarDesdeNorte(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Postcondicion unaNaveTieneCargado(Sustancia sustancia, int cantidad) {

        return post(() ->

                assertThat(unaNave.obtenerMonitor().consultarCargaDe(sustancia))
                        .as("monitor.consultarCargaDe(" + sustancia + ")")
                        .isEqualTo(cantidad));
    }

    @Test
    void cargarDesdeSurAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlNorteDeUnContenedor());

        unaNave.cargarDesdeSur(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Precondicion unaNaveEstaAlNorteDeUnContenedor() {

        return pre(() -> {

            IntStream.range(0, 2).forEach(n -> unaNave.avanzarAlOeste());
            unaNave.avanzarAlSur();
        });
    }

    @Test
    void cargarDesdeEsteAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlOesteDeUnContenedor());

        unaNave.cargarDesdeEste(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Precondicion unaNaveEstaAlOesteDeUnContenedor() {

        return pre(() -> {

            IntStream.range(0, 4).forEach(n -> unaNave.avanzarAlNorte());
            IntStream.range(0, 1).forEach(n -> unaNave.avanzarAlEste());
        });
    }

    @Test
    void cargarDesdeOesteAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlEsteDeUnContenedor());

        unaNave.cargarDesdeOeste(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    @Test
    void avanzarLuegoDeSerDestruida() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(fueDestruidaUnaNave());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unaNave.avanzarAlOeste()));
    }

    private Precondicion fueDestruidaUnaNave() {

        return pre(() -> {

            unaNave.avanzarAlNorte();
            IntStream.range(0, 6).forEach(n -> unaNave.avanzarAlOeste());
        });
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(Ejecutable ejecutable) {

        return post(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
        );
    }
}
