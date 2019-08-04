package espacial.piezas;

import espacial.Amarre;
import espacial.Carga;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Faccion;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstacionCentralTest extends TestDeContratoSobrePieza<EstacionCentral> {

    private final NaveEspacial NAVE = mock(NaveEspacial.class, "NAVE");
    private final NaveEspacial NAVE_ALFA = mock(NaveEspacial.class, "NAVE_ALFA");
    private final NaveEspacial NAVE_BETA = mock(NaveEspacial.class, "NAVE_BETA");
    private final NaveEspacial NAVE_GAMMA = mock(NaveEspacial.class, "NAVE_GAMMA");
    private final Casillero CASILLERO = mock(Casillero.class, "CASILLERO");

    private EstacionCentral unaBase;

    @Override
    EstacionCentral piezaCreada() {

        return new EstacionCentral();
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Override
    Faccion faccionEsperada() {

        return Faccion.ALIADO;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return post(condicion ->  verify(NAVE_ESPACIAL).chocoContraUnaBase());
    }

    @Test
    void amarrarUnaNave() {

        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());

        unaBase.amarrar(NAVE);

        comprobarQue(unaBaseTieneAmarradaLaNave());
    }

    private Precondicion unaBaseFueCreadaYColocadaEnCasillero() {

        return pre(condicion ->  {

            unaBase = new EstacionCentral();
            unaBase.fueColocadaEn(CASILLERO);
        });
    }

    private Postcondicion unaBaseTieneAmarradaLaNave() {

        return post(condicion ->  {

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

        return pre(condicion ->  {

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

        return post(condicion ->  verify(CASILLERO).ocuparCon(NAVE_BETA));
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadaUnaBase());

        comprobarQue(losPuntosInicialesDeUnaBaseSonCorrectos());
    }

    private Precondicion fueCreadaUnaBase() {

        return pre(condicion ->  unaBase = new EstacionCentral());
    }

    private Postcondicion losPuntosInicialesDeUnaBaseSonCorrectos() {

        return post(condicion ->  assertThat(unaBase.obtenerPuntos()).as("puntos").isEqualTo(200));
    }

    @Test
    void recibirUnaCargaDeAntimateria() {

        final int cantidad = 40;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidad));
    }

    private Postcondicion unaBaseTiene(SustanciaEspacial sustanciaEsperada, int cantidadEsperada) {

        return post(condicion ->

                assertThat(unaBase.buscar(sustanciaEsperada))
                        .as("buscar(" + sustanciaEsperada + ")")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    void recibirMultiplesCargasDeAntimateria() {

        final int cantidadInicial = 45;
        final int cantidad = 20;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial + cantidad));
    }

    private Precondicion fueCreadaUnaBaseRecibiendo(Carga unaCarga) {

        return pre(condicion ->  {

            unaBase = new EstacionCentral();
            unaBase.recibir(unaCarga);
        });
    }

    @Test
    void recibirLaCargaMaximaDeAntimateria() {

        final int cantidadMaxima = 5000;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadMaxima));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadMaxima));
    }

    @Test
    void recibirCargaExcediendoLaCapacidadDeAntimateria() {

        final int cantidadExcedida = 5001;

        dadoQue(fueCreadaUnaBase());

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBase.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadExcedida)))
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeElLugarDisponible(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeElLugarDisponible.class)
        );
    }

    @Test
    void entregarUnaCarga() {

        final int cantidadInicial = 400;
        final int cantidadEntregada = 150;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.ANTIMATERIA.por(cantidadEntregada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial - cantidadEntregada));
    }

    @Test
    void recibirUnaCargaDeMetal() {

        final int cantidad = 400;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.METAL, cantidad));
    }

    @Test
    void recibirUnaCargaDeCristal() {

        final int cantidad = 320;

        dadoQue(fueCreadaUnaBase());

        unaBase.recibir(SustanciaEspacial.CRISTAL.por(cantidad));

        comprobarQue(unaBaseTiene(SustanciaEspacial.CRISTAL, cantidad));
    }

    @Test
    void entregarUnaCargaDeAntimateria() {

        final int cantidadInicial = 60;
        final int cantidadRetirada = 12;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.ANTIMATERIA.por(cantidadRetirada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial - cantidadRetirada));
    }

    @Test
    void entregarUnaCargaDeCristal() {

        final int cantidadInicial = 500;
        final int cantidadRetirada = 120;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.CRISTAL.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.CRISTAL.por(cantidadRetirada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.CRISTAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    void entregarUnaCargaDeMETAL() {

        final int cantidadInicial = 1300;
        final int cantidadRetirada = 450;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.METAL.por(cantidadInicial)));

        unaBase.entregar(SustanciaEspacial.METAL.por(cantidadRetirada));

        comprobarQue(unaBaseTiene(SustanciaEspacial.METAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    void recibirCargaDeCristalQueExcedeLaCapacidadPorqueTieneAntimateria() {

        final int cantidadInicialDeAntimateria = 3000;
        final int cantidadDeCristal = 2001;

        dadoQue(fueCreadaUnaBaseRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicialDeAntimateria)));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBase.recibir(SustanciaEspacial.METAL.por(cantidadDeCristal)))
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
}
