package espacial.piezas;

import espacial.Amarre;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseRivalTest extends TestDeContratoSobrePieza<BaseRival> {

    private final NaveEspacial NAVE = mock(NaveEspacial.class, "NAVE");
    private final NaveEspacial NAVE_ALFA = mock(NaveEspacial.class, "NAVE_ALFA");
    private final NaveEspacial NAVE_BETA = mock(NaveEspacial.class, "NAVE_BETA");
    private final NaveEspacial NAVE_GAMMA = mock(NaveEspacial.class, "NAVE_GAMMA");
    private final Casillero CASILLERO = mock(Casillero.class, "CASILLERO");

    private BaseRival unaBase;

    @Override
    BaseRival piezaCreada() {

        return new BaseRival();
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Override
    Postcondicion evaluoLaCondicionDePartidarioEsperada() {

        return evaluoQueEsRival();
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return post(condicion -> verify(NAVE_ESPACIAL).chocoContraUnaBase());
    }

    @Test
    void amarrarUnaNave() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());

        unaBase.amarrar(NAVE);

        comprobarQue(unaBaseTieneAmarradaLaNave());
    }

    private Precondicion unaBaseFueCreadaYColocadaEnCasillero() {

        return pre(condicion -> {

            unaBase = new BaseRival();
            unaBase.fueColocadaEn(CASILLERO);
        });
    }

    private Postcondicion unaBaseTieneAmarradaLaNave() {

        return post(condicion -> {

            Amarre amarre = unaBase.obtenerAmarres()[0];

            assertThat(unaBase.obtenerAmarres())
                    .as("amarres")
                    .extracting(Amarre::obtenerPieza)
                    .containsExactly(NAVE);

            verify(NAVE).fueAmarradaCon(amarre);
        });
    }

    @Test
    void amarrarMultiplesNaves() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());

        unaBase.amarrar(NAVE_ALFA);
        unaBase.amarrar(NAVE_BETA);
        unaBase.amarrar(NAVE_GAMMA);

        comprobarQue(unaBaseTieneAmarradasLasNavesAlfaBetaGamma());
    }

    private Postcondicion unaBaseTieneAmarradasLasNavesAlfaBetaGamma() {

        return post(condicion ->

                assertThat(unaBase.obtenerAmarres())
                        .as("amarres")
                        .extracting(Amarre::obtenerPieza)
                        .containsExactly(NAVE_ALFA, NAVE_BETA, NAVE_GAMMA)
        );
    }

    @Test
    void soltarAmarreDeUnaNave() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());
        dadoQue(lasNavesAlfaBetaGammaEstanAmarradasAUnaBase());

        unaBase.obtenerAmarres()[1].soltar();

        comprobarQue(unaBaseYaNoTieneMasAmarradaLaNaveBeta());
        comprobarQue(naveBetaFueColocadaEnElCasilleroDeLaBase());
    }

    private Precondicion lasNavesAlfaBetaGammaEstanAmarradasAUnaBase() {

        return pre(condicion -> {

            unaBase.amarrar(NAVE_ALFA);
            unaBase.amarrar(NAVE_BETA);
            unaBase.amarrar(NAVE_GAMMA);
        });
    }

    private Postcondicion unaBaseYaNoTieneMasAmarradaLaNaveBeta() {

        return post(condicion ->

                assertThat(unaBase.obtenerAmarres())
                        .as("amarres")
                        .extracting(Amarre::obtenerPieza)
                        .containsExactly(NAVE_ALFA, NAVE_GAMMA));
    }

    private Postcondicion naveBetaFueColocadaEnElCasilleroDeLaBase() {

        return post(condicion -> verify(CASILLERO).ocuparCon(NAVE_BETA));
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadaUnaBase());

        comprobarQue(losPuntosInicialesDeUnaBaseSonCorrectos());
    }

    private Precondicion fueCreadaUnaBase() {

        return pre(condicion -> unaBase = new BaseRival());
    }

    private Postcondicion losPuntosInicialesDeUnaBaseSonCorrectos() {

        return post(condicion -> assertThat(unaBase.obtenerPuntos()).as("puntos").isEqualTo(1000));
    }

    @Test
    void recibirUnaCargaDeAntimateria() {

        final int cantidad = 40;

        dadoQue(fueCreadaUnaBase());

        comprobarQue(lanzaExcepcionPorqueNoPuedeRecibirCarga(() -> unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad))));
    }

    private Postcondicion lanzaExcepcionPorqueNoPuedeRecibirCarga(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(NoPuedeRecibirUnaCarga.class)
        );
    }

    @Test
    void aceptar() {

        dadoQue(fueCreadaUnaBase());

        unaBase.aceptar(UN_VISITANTE);

        comprobarQue(unVisitanteEsBase());
    }

    private Postcondicion unVisitanteEsBase() {

        return post(condicion -> verify(UN_VISITANTE).siEsBase(unaBase));
    }

    @Test
    void obtenerNivelDeDefensasIniciales() {

        dadoQue(fueCreadaUnaBase());

        comprobarQue(unaBaseTieneDefensasEnNivel(100));
    }

    private Postcondicion unaBaseTieneDefensasEnNivel(int nivelEsperado) {

        return post(condicion ->

                assertThat(unaBase.obtenerNivelDeDefensas())
                        .as("nivel de defensas")
                        .isEqualTo(nivelEsperado)
        );
    }

    @Test
    void obtenerNivelesDeEscudosLuegoDeRecibirUnAtaque() {

        dadoQue(fueCreadaUnaBase());

        unaBase.fueAtacadoCon(new AtaqueConTorpedoDeFotones());

        comprobarQue(unaBaseTieneDefensasEnNivel(99));
    }

}
