import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Pieza;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

class NaveTest implements TestDeContrato {

    private static final int CANTIDAD_MINIMA = 1;
    private static final int NADA = 0;

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

        comprobarQue(existeEnLaBaseLaNave(unaNave));
    }

    private Postcondicion existeEnLaBaseLaNave(Nave unaNave) {

        return post(condicion -> {

            assertThat(batallaEspacial.obtenerNaves())
                    .as("naves de la BatallaEspacial")
                    .hasSize(1)
                    .containsExactly(unaNave);

            assertThat(batallaEspacial.obtenerTablero()).tieneBase().en(0, 0);
        });
    }

    private Precondicion fueCreadaLaBatallaEspacial() {

        return pre(condicion -> batallaEspacial = new BatallaEspacial());
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

        return post(condicion -> {

            condicion.es("existen en la Base tres Naves: '%s', '%s' y '%s'", primerNave, segundaNave, tercerNave);

            assertThat(batallaEspacial.obtenerNaves())
                    .as("naves de la BatallaEspacial")
                    .hasSize(3)
                    .containsExactly(primerNave, segundaNave, tercerNave);
        });
    }

    @Test
    void noPuedeAvanzarAlNorteSiNoDespegoAntes() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        comprobarQue(generaElErrorPorqueLaNaveNoDespego(() -> unaNave.avanzarAlNorte()));
    }

    private Precondicion fueCreadaUnaNave() {

        return pre(condicion -> unaNave = new Nave());
    }

    private Postcondicion generaElErrorPorqueLaNaveNoDespego(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
                        .hasMessageEndingWith(" porque no despegó")
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

        return pre(condicion -> {

            unaNave = new Nave();
            unaNave.despegar();
        });
    }

    private Postcondicion unaNaveEstaAlNorteDeLaBase() {

        return post(condicion ->

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

        return post(condicion ->

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

        return post(condicion ->

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

        return post(condicion ->

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

        return post(condicion ->

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

        return pre(condicion -> {

            unaNave.avanzarAlNorte();
            unaNave.avanzarAlOeste();
            unaNave.avanzarAlOeste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAsteroide() {

        return post(condicion ->

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

        return post(condicion ->

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

        return pre(condicion -> {

            repetir(2, i -> unaNave.avanzarAlEste());
            repetir(3, i -> unaNave.avanzarAlNorte());
        });
    }

    private Postcondicion unaNaveQuedoEnElCasillero(int fila, int columna) {

        return post(condicion -> assertThat(batallaEspacial.obtenerTablero()).tieneNave().en(fila, columna));
    }

    private Postcondicion unaNaveSufrioElChoqueContraElContenedor() {

        return post(condicion -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(90));
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

        return pre(condicion -> {

            unaNave.avanzarAlOeste();
            repetir(10, i -> unaNave.avanzarAlSur());
            unaNave.avanzarAlEste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElBorde() {

        return post(condicion ->

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

        return pre(condicion -> {

            repetir(5, i -> unaNave.avanzarAlOeste());
            unaNave.avanzarAlNorte();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAgujeroNegro() {

        return post(condicion -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(25));
    }

    @Test
    void noPuedeAvanzarSiEstaLaBaseEnElCasilleroDestino() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlSurDeUnaBase());

        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveQuedoEnElCasillero(-1, 0));
        comprobarQue(unaNaveSufrioElChoqueContraLaBase());
    }

    private Postcondicion unaNaveSufrioElChoqueContraLaBase() {

        return post(condicion -> assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos").isEqualTo(95));
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

        return pre(condicion -> repetir(5, i -> unaNave.avanzarAlSur()));
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneElAsteroide() {

        return pre(condicion -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenadas.con(-6, 0));
            asteroideAlNorte = casillero.obtenerPieza();
            puntosInicialesDelAsteroide = asteroideAlNorte.obtenerPuntos();
        });

    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneElAsteroide() {

        return post(condicion ->

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

        return pre(condicion -> {

            repetir(2, i -> unaNave.avanzarAlSur());
            unaNave.avanzarAlOeste();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneElContenedor() {

        return pre(condicion -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenadas.con(-2, -2));
            contenedorAlOeste = casillero.obtenerPieza();
            puntosInicialesDelContenedor = contenedorAlOeste.obtenerPuntos();
        });

    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneElContenedor() {

        return post(condicion ->

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

        return pre(condicion -> unaNave.avanzarAlSur());
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneLaBaseAlNorte() {

        return pre(condicion -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenadas.con(0, 0));
            baseAlNorte = casillero.obtenerPieza();
            puntosInicialesDeLaBase = baseAlNorte.obtenerPuntos();
        });
    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneLaBaseAlNorte() {

        return post(condicion ->

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

        return pre(condicion -> {

            Nave otraNave = new Nave();
            otraNave.despegar();
            repetir(2, i -> otraNave.avanzarAlEste());

            unaNave = new Nave();
            unaNave.despegar();
            unaNave.avanzarAlEste();
        });
    }

    private Precondicion seConoceLaCantidadDePuntosQueTieneLaNaveAlEste() {

        return pre(condicion -> {

            Casillero casillero = batallaEspacial.obtenerTablero().obtenerCasilleroEn(Coordenadas.con(0, 2));
            naveAlEste = casillero.obtenerPieza();
            puntosInicialesDeOtraNave = naveAlEste.obtenerPuntos();
        });
    }

    private Postcondicion disminuyoLaCantidadDePuntosQueTieneLaNaveAlEste() {

        return post(condicion ->

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

        return post(condicion -> {

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

        return post(condicion -> {

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

        return post(condicion -> assertThat(unaNave).hasToString("Nave a la espera de comandos"));
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

        return post(condicion ->

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

        return pre(condicion -> {

            repetir(2, i -> unaNave.avanzarAlOeste());
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

        return pre(condicion -> {

            repetir(4, i -> unaNave.avanzarAlNorte());
            repetir(1, i -> unaNave.avanzarAlEste());
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
    void descargarEnNorteAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveConAntimateriaEstaAlSurDeLaBase());

        unaNave.descargarEnNorte(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, NADA));
        comprobarQue(laBaseAlNorteTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Precondicion unaNaveConAntimateriaEstaAlSurDeLaBase() {

        return pre(condicion -> {

            repetir(2, i -> unaNave.avanzarAlSur());
            unaNave.avanzarAlOeste();
            unaNave.cargarDesdeOeste(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);
            unaNave.avanzarAlEste();
            unaNave.avanzarAlNorte();
        });
    }

    private Postcondicion laBaseAlNorteTieneCargado(Sustancia sustancia, int cantidad) {

        return post(condicion ->

                assertThat(unaNave.obtenerRadar().buscarAlNorte(sustancia))
                        .as("buscarAlNorte(ANTIMATERIA)")
                        .isEqualTo(cantidad)
        );
    }

    @Test
    void descargarEnSurAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveConAntimateriaEstaAlNorteDeLaBase());

        unaNave.descargarEnSur(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, NADA));
        comprobarQue(laBaseAlSurTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Precondicion unaNaveConAntimateriaEstaAlNorteDeLaBase() {

        return pre(condicion -> {

            repetir(4, i -> unaNave.avanzarAlNorte());
            unaNave.avanzarAlEste();
            unaNave.cargarDesdeEste(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);
            unaNave.avanzarAlOeste();
            repetir(3, i -> unaNave.avanzarAlSur());
        });
    }

    private Postcondicion laBaseAlSurTieneCargado(Sustancia sustancia, int cantidad) {

        return post(condicion ->

                assertThat(unaNave.obtenerRadar().buscarAlSur(sustancia))
                        .as("buscarAlSur(ANTIMATERIA)")
                        .isEqualTo(cantidad)
        );
    }

    @Test
    void descargarEnEsteAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveConAntimateriaEstaAlOesteDeLaBase());

        unaNave.descargarEnOeste(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, NADA));
        comprobarQue(laBaseAlOesteTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Precondicion unaNaveConAntimateriaEstaAlOesteDeLaBase() {

        return pre(condicion -> {

            repetir(2, i -> unaNave.avanzarAlEste());
            repetir(3, i -> unaNave.avanzarAlNorte());
            unaNave.cargarDesdeNorte(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);
            repetir(3, i -> unaNave.avanzarAlSur());
            unaNave.avanzarAlOeste();
        });
    }

    private Postcondicion laBaseAlOesteTieneCargado(Sustancia sustancia, int cantidad) {

        return post(condicion ->

                assertThat(unaNave.obtenerRadar().buscarAlOeste(sustancia))
                        .as("buscarAlOeste(ANTIMATERIA)")
                        .isEqualTo(cantidad)
        );
    }

    @Test
    void avanzarLuegoDeSerDestruida() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(fueDestruidaUnaNave());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unaNave.avanzarAlOeste()));
    }

    private Precondicion fueDestruidaUnaNave() {

        return pre(condicion -> {

            unaNave.avanzarAlNorte();
            repetir(6, i -> unaNave.avanzarAlOeste());
        });
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
                        .hasMessageEndingWith("porque fue destruida")
        );
    }

    @Test
    void descargarEnOesteAntimateria() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveConAntimateriaEstaAlEsteDeLaBase());

        unaNave.descargarEnEste(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);

        comprobarQue(unaNaveTieneCargado(Sustancia.ANTIMATERIA, NADA));
        comprobarQue(laBaseAlEsteTieneCargado(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA));
    }

    private Precondicion unaNaveConAntimateriaEstaAlEsteDeLaBase() {

        return pre(condicion -> {

            repetir(2, i -> unaNave.avanzarAlOeste());
            unaNave.avanzarAlSur();
            unaNave.cargarDesdeSur(Sustancia.ANTIMATERIA, CANTIDAD_MINIMA);
            unaNave.avanzarAlNorte();
            unaNave.avanzarAlEste();
        });
    }

    private Postcondicion laBaseAlEsteTieneCargado(Sustancia sustancia, int cantidad) {

        return post(condicion ->

                assertThat(unaNave.obtenerRadar().buscarAlEste(sustancia))
                        .as("buscarAlEste(ANTIMATERIA)")
                        .isEqualTo(cantidad)
        );
    }

    @Test
    void atacarLuegoDeSerDestruida() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(fueDestruidaUnaNave());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unaNave.atacarAlNorte()));
    }

    @Test
    void cargarLuegoDeSerDestruida() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(fueDestruidaUnaNave());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() ->

                unaNave.cargarDesdeNorte(Sustancia.ANTIMATERIA, 1))
        );
    }

    @Test
    void despegarCuandoNoEstaEnLaBase() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());

        unaNave.avanzarAlNorte();


        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnLaBase(() -> unaNave.despegar()));
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnLaBase(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(LaNaveNoEstaEnLaBase.class)
        );
    }

    @Test
    void destruirAsteroide() {

        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNaveQueDespegoDeLaBase());
        dadoQue(unaNaveEstaAlNorteDeUnAsteroide());

        repetir(30, i -> unaNave.atacarAlSur());

        comprobarQue(noExisteUnAsteroideAlSur());
    }

    private Postcondicion noExisteUnAsteroideAlSur() {

        return post(condicion ->

                assertThat(unaNave.obtenerRadar().escanearSur())
                    .as("escanearSur()")
                    .isEqualTo(Espectro.VACIO)
        );
    }

}
