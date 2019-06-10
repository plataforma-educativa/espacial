package espacial.piezas;

import espacial.Amarre;
import espacial.Carga;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BaseEspacialTest extends TestDeContratoSobrePieza<BaseEspacial> {

    private final NaveEspacial NAVE = mock(NaveEspacial.class, "NAVE");
    private final NaveEspacial NAVE_ALFA = mock(NaveEspacial.class, "NAVE_ALFA");
    private final NaveEspacial NAVE_BETA = mock(NaveEspacial.class, "NAVE_BETA");
    private final NaveEspacial NAVE_GAMMA = mock(NaveEspacial.class, "NAVE_GAMMA");
    private final Casillero CASILLERO = mock(Casillero.class, "CASILLERO");

    private BaseEspacial unaBase;

    @Override
    public BaseEspacial piezaCreada() {

        return new BaseEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnaBase());
    }

    @Test
    public void amarrarUnaNave() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());

        unaBase.amarrar(NAVE);

        comprobarQue(unaBaseTieneAmarradaLaNave());
    }

    private Precondicion unaBaseFueCreadaYColocadaEnCasillero() {

        return precondicion(() -> {

            unaBase = new BaseEspacial();
            unaBase.fueColocadaEn(CASILLERO);
        });
    }

    private Postcondicion unaBaseTieneAmarradaLaNave() {

        return postcondicion(() -> {

            Amarre amarre = unaBase.obtenerAmarres()[0];

            assertThat(unaBase.obtenerAmarres())
                    .as("amarres")
                    .extracting(Amarre::obtenerPieza)
                    .containsExactly(NAVE);

            verify(NAVE).fueAmarradaCon(amarre);
        });
    }

    @Test
    public void amarrarMultiplesNaves() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());

        unaBase.amarrar(NAVE_ALFA);
        unaBase.amarrar(NAVE_BETA);
        unaBase.amarrar(NAVE_GAMMA);

        comprobarQue(unaBaseTieneAmarradasLasNavesAlfaBetaGamma());
    }

    private Postcondicion unaBaseTieneAmarradasLasNavesAlfaBetaGamma() {

        return postcondicion(() ->

            assertThat(unaBase.obtenerAmarres())
                    .as("amarres")
                    .extracting(Amarre::obtenerPieza)
                    .containsExactly(NAVE_ALFA, NAVE_BETA, NAVE_GAMMA)
        );
    }

    @Test
    public void soltarAmarreDeUnaNave() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());
        dadoQue(lasNavesAlfaBetaGammaEstanAmarradasAUnaBase());

        unaBase.obtenerAmarres()[1].soltar();

        comprobarQue(unaBaseYaNoTieneMasAmarradaLaNaveBeta());
        comprobarQue(naveBetaFueColocadaEnElCasilleroDeLaBase());
    }

    private Precondicion lasNavesAlfaBetaGammaEstanAmarradasAUnaBase() {

        return precondicion(() -> {

            unaBase.amarrar(NAVE_ALFA);
            unaBase.amarrar(NAVE_BETA);
            unaBase.amarrar(NAVE_GAMMA);
        });
    }

    private Postcondicion unaBaseYaNoTieneMasAmarradaLaNaveBeta() {

        return postcondicion(() ->

                assertThat(unaBase.obtenerAmarres())
                        .as("amarres")
                        .extracting(Amarre::obtenerPieza)
                        .containsExactly(NAVE_ALFA, NAVE_GAMMA));
    }

    private Postcondicion naveBetaFueColocadaEnElCasilleroDeLaBase() {

        return postcondicion(() -> verify(CASILLERO).ocuparCon(NAVE_BETA));
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadaUnaBase());

        comprobarQue(losPuntosInicialesDeUnaBaseSonCorrectos());
    }

    private Precondicion fueCreadaUnaBase() {

        return precondicion(() -> unaBase = new BaseEspacial());
    }

    private Postcondicion losPuntosInicialesDeUnaBaseSonCorrectos() {

        return postcondicion(() -> assertThat(unaBase.obtenerPuntos()).as("puntos").isEqualTo(200));
    }

    @Test
    public void recibirUnaCargaDeAntimateria() {

        final int cantidad = 40;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidad));
    }

    private Postcondicion unaBaseTiene(SustanciaEspacial sustanciaEsperada, int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unaBase.buscar(sustanciaEsperada))
                        .as("buscar(" + sustanciaEsperada + ")")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    public void recibirMultiplesCargasDeAntimateria() {

        final int cantidadInicial = 45;
        final int cantidad = 20;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial + cantidad));
    }

    private Precondicion fueCreadaUnaBaseRecibiendo(Carga unaCarga) {

        return precondicion(() -> {

            unaBase = new BaseEspacial();
            unaBase.recibir(unaCarga);
        });
    }

    @Test
    public void recibirLaCargaMaximaDeAntimateria() {

        final int cantidadMaxima = 5000;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadMaxima));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadMaxima));
    }

    @Test
    public void recibirCargaExcediendoLaCapacidadDeAntimateria() {

        final int cantidadExcedida = 5001;

        dadoQue(fueCreadaUnaBase());

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadExcedida)))
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeElLugarDisponible(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeElLugarDisponible.class)
        );
    }

    @Test
    public void entregarUnaCarga() {

        final int cantidadInicial = 400;
        final int cantidadEntregada = 150;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.ANTIMATERIA.por(cantidadEntregada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial - cantidadEntregada));
    }

    @Test
    public void recibirUnaCargaDeMetal() {

        final int cantidad = 400;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.METAL, cantidad));
    }

    @Test
    public void recibirUnaCargaDeCristal() {

        final int cantidad = 320;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.CRISTAL.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.CRISTAL, cantidad));
    }

    @Test
    public void entregarUnaCargaDeAntimateria() {

        final int cantidadInicial = 60;
        final int cantidadRetirada = 12;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.ANTIMATERIA.por(cantidadRetirada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial - cantidadRetirada));
    }

    @Test
    public void entregarUnaCargaDeCristal() {

        final int cantidadInicial = 500;
        final int cantidadRetirada = 120;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.CRISTAL.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.CRISTAL.por(cantidadRetirada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.CRISTAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    public void entregarUnaCargaDeMETAL() {

        final int cantidadInicial = 1300;
        final int cantidadRetirada = 450;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.METAL.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.METAL.por(cantidadRetirada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.METAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    public void recibirCargaDeCristalQueExcedeLaCapacidadPorqueTieneAntimateria() {

        final int cantidadInicialDeAntimateria = 3000;
        final int cantidadDeCristal = 2001;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicialDeAntimateria)));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBase.recibir(SustanciaEspacial.METAL.por(cantidadDeCristal)))
        );
    }
}
