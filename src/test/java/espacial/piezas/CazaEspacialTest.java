package espacial.piezas;

import espacial.Amarre;
import espacial.Atacable;
import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CazaEspacialTest extends TestDeContratoSobrePieza<CazaEspacial> {

    private final Amarre AMARRE = mock(Amarre.class, "AMARRE");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");
    private final Casillero CASILLERO_NORTE = mock(Casillero.class, "CASILLERO_NORTE");
    private final Casillero CASILLERO_SUR = mock(Casillero.class, "CASILLERO_SUR");
    private final Casillero CASILLERO_ESTE = mock(Casillero.class, "CASILLERO_ESTE");
    private final Casillero CASILLERO_OESTE = mock(Casillero.class, "CASILLERO_OESTE");

    private final Pieza OTRA_PIEZA = mock(Pieza.class, "OTRA_PIEZA");

    private CazaEspacial unCazaEspacial;

    @BeforeEach
    void simularCasillero() {

        when(UN_CASILLERO.obtenerContiguoEn(Direccion.NORTE)).thenReturn(CASILLERO_NORTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.SUR)).thenReturn(CASILLERO_SUR);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.ESTE)).thenReturn(CASILLERO_ESTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.OESTE)).thenReturn(CASILLERO_OESTE);
    }

    @Override
    CazaEspacial piezaCreada() {

        return new CazaEspacial();
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.NAVE;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnaNave());
    }

    @Test
    void obtenerNivelDeEscudosInicial() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(elNivelDeEscudosEs(100));
    }

    private Postcondicion elNivelDeEscudosEs(int nivelEsperado) {

        return postcondicion(() ->

                assertThat(unCazaEspacial.obtenerNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(nivelEsperado));
    }

    @Test
    void disminuirNivelDeEscudosEn() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.disminuirNivelDeEscudosEn(34);

        comprobarQue(elNivelDeEscudosEs(66));
    }

    @Test
    void disminuirNivelDeEscudosEnUnValorMayorA100() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.disminuirNivelDeEscudosEn(101);

        comprobarQue(elNivelDeEscudosEs(0));
    }

    @Test
    void fueChocadaPorOtraNaveEspacial() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.fueChocadaPor(NAVE_ESPACIAL);

        comprobarQue(elNivelDeEscudosBajoHasta(70));
    }

    @Test
    void moverEnDireccionCuandoNoDespego() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.moverEn(Direccion.NORTE)));
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class));
    }

    @Test
    void moverEnDireccion() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.moverEn(Direccion.NORTE);

        comprobarQue(unCazaEspacialSeMovioUnCasilleroEnDireccionNorte());
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasillero() {

        return precondicion(() -> {
            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private Postcondicion unCazaEspacialSeMovioUnCasilleroEnDireccionNorte() {

        return postcondicion(() -> verify(UN_CASILLERO).moverPiezaA(CASILLERO_NORTE));
    }

    @Test
    void chocoContraUnAsteroide() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnAsteroide();

        comprobarQue(elNivelDeEscudosBajoHasta(75));
    }

    private Precondicion fueCreadoUnCazaEspacial() {

        return precondicion(() -> unCazaEspacial = new CazaEspacial());
    }

    private Postcondicion elNivelDeEscudosBajoHasta(int nivelDeEscudoEsperado) {

        return postcondicion(() ->

                assertThat(unCazaEspacial.obtenerNivelDeEscudos())
                        .as("nivel de escudos")
                        .isEqualTo(nivelDeEscudoEsperado));
    }

    @Test
    void chocoContraUnContenedor() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnContenedor();

        comprobarQue(elNivelDeEscudosBajoHasta(90));
    }

    @Test
    void chocoContraElBordeDelTablero() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraElBordeDelTablero();

        comprobarQue(elNivelDeEscudosBajoHasta(50));
    }

    @Test
    void chocoContraUnAgujeroNegro() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnAgujeroNegro();

        comprobarQue(elNivelDeEscudosBajoHasta(25));
    }

    @Test
    void chocoContraUnaNave() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnaNave();

        comprobarQue(elNivelDeEscudosBajoHasta(75));
    }

    @Test
    void chocoContraUnaBase() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnaBase();

        comprobarQue(elNivelDeEscudosBajoHasta(95));
    }

    @Test
    void despegar() {

        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(unCazaEspacialFueAmarrado());

        unCazaEspacial.despegar();

        comprobarQue(unCazaEspacialSoltoElAmarre());
    }

    private Precondicion unCazaEspacialFueAmarrado() {

        return precondicion(() -> unCazaEspacial.fueAmarradaCon(AMARRE));
    }

    private Postcondicion unCazaEspacialSoltoElAmarre() {

        return postcondicion(() -> verify(AMARRE).soltar());
    }

    @Test
    void despegarCuandoNoEstaAmarrada() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnLaBase(() -> unCazaEspacial.despegar()));
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnLaBase(Ejecutable ejecutable) {

        return postcondicion(() -> assertThatThrownBy(ejecutable::ejecutar).isInstanceOf(LaNaveNoEstaEnLaBase.class));
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(losPuntosInicialesDeUnCazaEspacialSonCorrectos());
    }

    private Postcondicion losPuntosInicialesDeUnCazaEspacialSonCorrectos() {

        return postcondicion(() -> assertThat(unCazaEspacial.obtenerPuntos()).as("puntos").isEqualTo(100));
    }

    @Test
    void atacarEnDireccion() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlOeste());

        unCazaEspacial.atacarEn(Direccion.OESTE);

        comprobarQue(elCasilleroEnDireccionOesteFueAtacado());
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlOeste() {

        return precondicion(() -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

            when(CASILLERO_OESTE.obtenerPieza()).thenReturn(OTRA_PIEZA);
        });
    }

    private Postcondicion elCasilleroEnDireccionOesteFueAtacado() {

        return postcondicion(() -> {

            ArgumentCaptor<Ataque> ataqueCapturado = ArgumentCaptor.forClass(Ataque.class);

            verify(CASILLERO_OESTE).fueAtacadoCon(ataqueCapturado.capture());

        });
    }

    @Test
    void atacarEnUnaDireccionUtilizaTorpedosDeFotonesHasta100VecesLuegoUsaLaser() {

        final int TORPEDOS_DE_FOTONES = 100;
        final int LASER = 45;

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlNorte());

        IntStream.rangeClosed(1, TORPEDOS_DE_FOTONES + LASER).forEach(n -> unCazaEspacial.atacarEn(Direccion.NORTE));

        comprobarQue(otraPiezaFueAtacadaCon(TORPEDOS_DE_FOTONES, LASER));
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlNorte() {

        return precondicion(() -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private Postcondicion otraPiezaFueAtacadaCon(int torpedos, int laser) {

        return postcondicion(() -> {

            ArgumentCaptor<Ataque> captorDeAtaques = ArgumentCaptor.forClass(Ataque.class);
            verify(CASILLERO_NORTE, atLeastOnce()).fueAtacadoCon(captorDeAtaques.capture());

            Atacable atacable = mock(Atacable.class);
            captorDeAtaques.getAllValues().forEach(ataque -> ataque.aplicarSobre(atacable));

            InOrder secuencia = inOrder(atacable);

            secuencia.verify(atacable, times(torpedos)).atacadoConTorpedoDeFotones();
            secuencia.verify(atacable, times(laser)).atacadoConLaser();
            secuencia.verifyNoMoreInteractions();
        });
    }

    @Test
    void escanearEnDireccion() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasilleroRodeadoDeAsteroides());

        comprobarQue(alEscanearAlrededorEncuentra(EspectroEspacial.ASTEROIDE));
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasilleroRodeadoDeAsteroides() {

        return precondicion(() -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

            when(CASILLERO_NORTE.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
            when(CASILLERO_SUR.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
            when(CASILLERO_ESTE.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
            when(CASILLERO_OESTE.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
        });
    }

    private Postcondicion alEscanearAlrededorEncuentra(EspectroEspacial esperado) {

        return postcondicion(() -> {

            assertThat(unCazaEspacial.escanearEn(Direccion.NORTE)).as("al NORTE").isEqualTo(esperado);
            assertThat(unCazaEspacial.escanearEn(Direccion.SUR)).as("al SUR").isEqualTo(esperado);
            assertThat(unCazaEspacial.escanearEn(Direccion.ESTE)).as("al ESTE").isEqualTo(esperado);
            assertThat(unCazaEspacial.escanearEn(Direccion.OESTE)).as("al OESTE").isEqualTo(esperado);
        });
    }

    @Test
    void escanearEnDireccionCuandoNoEstaEnUnCasillero() {

        dadoQue(fueCreadoUnCazaEspacialQueNoSeColocoEnNingunCasillero());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.escanearEn(Direccion.NORTE)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.escanearEn(Direccion.SUR)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.escanearEn(Direccion.ESTE)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.escanearEn(Direccion.OESTE)));
    }

    private Precondicion fueCreadoUnCazaEspacialQueNoSeColocoEnNingunCasillero() {

        return precondicion(() -> unCazaEspacial = new CazaEspacial());
    }

    @Test
    void atacarEnDireccionCuandoNoEstaEnUnCasillero() {

        dadoQue(fueCreadoUnCazaEspacialQueNoSeColocoEnNingunCasillero());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.NORTE)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.SUR)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.ESTE)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.OESTE)));
    }

    @Test
    void consultarCantidadDeTorpedosIniciales() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(laCantidadDeTorpedosDeFotonesEs(100));
    }

    private Postcondicion laCantidadDeTorpedosDeFotonesEs(int esperado) {

        return postcondicion(() ->

                assertThat(unCazaEspacial.obtenerCantidadDeTorpedosDeFotones())
                        .as("cantidad de torpedos")
                        .isEqualTo(100)
        );
    }

    @Test
    void buscarEnDireccionUnaSustanciaEspacial() {

        dadoQue(fueCreadoUnCazaEspacialConAntimateriaAlrededor(10,0, 15, 30));

        comprobarQue(alBuscarAlrededorAntimateriaEncuentra(10, 0, 15, 30));
    }

    private Precondicion fueCreadoUnCazaEspacialConAntimateriaAlrededor(int alNorte, int alSur, int alEste, int alOeste) {

        return precondicion(() -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

            when(CASILLERO_NORTE.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alNorte);
            when(CASILLERO_SUR.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alSur);
            when(CASILLERO_ESTE.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alEste);
            when(CASILLERO_OESTE.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alOeste);
        });
    }

    private Postcondicion alBuscarAlrededorAntimateriaEncuentra(int alNorte, int alSur, int alEste, int alOeste) {

        return postcondicion(() -> {

            assertThat(unCazaEspacial.buscarEn(Direccion.NORTE, SustanciaEspacial.ANTIMATERIA))
                    .as("ANTIMATERIA al NORTE").isEqualTo(alNorte);
            assertThat(unCazaEspacial.buscarEn(Direccion.SUR, SustanciaEspacial.ANTIMATERIA))
                    .as("ANTIMATERIA al SUR").isEqualTo(alSur);
            assertThat(unCazaEspacial.buscarEn(Direccion.ESTE, SustanciaEspacial.ANTIMATERIA))
                    .as("ANTIMATERIA al ESTE").isEqualTo(alEste);
            assertThat(unCazaEspacial.buscarEn(Direccion.OESTE, SustanciaEspacial.ANTIMATERIA))
                    .as("ANTIMATERIA al OESTE").isEqualTo(alOeste);
        });
    }

    @Test
    void recibirUnaCarga() {

        final int cantidad = 40;

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.ANTIMATERIA, cantidad));
    }

    private Postcondicion unCazaEspacialTiene(SustanciaEspacial sustanciaEsperada, int esperada) {

        return postcondicion(() ->

                assertThat(unCazaEspacial.buscar(sustanciaEsperada))
                        .as("buscar(" + sustanciaEsperada + ")")
                        .isEqualTo(esperada)
        );
    }

    @Test
    void recibirMultiplesCargas() {

        final int cantidadInicial = 45;
        final int cantidad = 20;

        dadoQue(fueCreadoUnCazaEspacialRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial + cantidad));
    }

    private Precondicion fueCreadoUnCazaEspacialRecibiendo(Carga unaCarga) {

        return precondicion(() -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.recibir(unaCarga);
        });
    }

    @Test
    void recibirLaCargaMaxima() {

        final int cantidadMaxima = 100;

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadMaxima));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.ANTIMATERIA, cantidadMaxima));
    }

    @Test
    void recibirCargaExcediendoLaCapacidad() {

        final int cantidadExcedida = 101;

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadExcedida)))
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
    void recibirUnaCargaDeMetal() {

        final int cantidad = 79;

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.recibir(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.METAL, cantidad));
    }

    @Test
    void entregarUnaCargaDeMetal() {

        final int cantidadInicial = 80;
        final int cantidadRetirada = 45;

        dadoQue(fueCreadoUnCazaEspacialRecibiendo(SustanciaEspacial.METAL.por(cantidadInicial)));

        unCazaEspacial.entregar(SustanciaEspacial.METAL.por(cantidadRetirada));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.METAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    void recibirUnaCargaDeCristal() {

        final int cantidad = 23;

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.recibir(SustanciaEspacial.CRISTAL.por(cantidad));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.CRISTAL, cantidad));
    }

    @Test
    void entregarUnaCargaDeCristal() {

        final int cantidadInicial = 70;
        final int cantidadRetirada = 12;

        dadoQue(fueCreadoUnCazaEspacialRecibiendo(SustanciaEspacial.CRISTAL.por(cantidadInicial)));

        unCazaEspacial.entregar(SustanciaEspacial.CRISTAL.por(cantidadRetirada));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.CRISTAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    void recibirCargasDeAntimateriaCristal() {

        final int cantidadDeAntimateria = 30;
        final int cantidadDeCristal = 23;

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadDeAntimateria));
        unCazaEspacial.recibir(SustanciaEspacial.CRISTAL.por(cantidadDeCristal));

        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.ANTIMATERIA, cantidadDeAntimateria));
        comprobarQue(unCazaEspacialTiene(SustanciaEspacial.CRISTAL, cantidadDeCristal));
    }

    @Test
    void recibirCargaDeMetalQueExcedeLaCapacidadPorqueTieneAntimateria() {

        final int cantidadInicialDeAntimateria = 50;
        final int cantidadDeMetal = 51;

        dadoQue(fueCreadoUnCazaEspacialRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicialDeAntimateria)));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unCazaEspacial.recibir(SustanciaEspacial.METAL.por(cantidadDeMetal)))
        );
    }

    @Test
    void recibirDesdeElNorteUnaCarga() {

        final int cantidad = 1;
        final SustanciaEspacial sustancia = SustanciaEspacial.ANTIMATERIA;

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasilleroConUnaPiezaCoAntimateriaAlNorte());

        unCazaEspacial.cargarDesde(Direccion.NORTE, sustancia.por(cantidad));

        comprobarQue(unCazaEspacialTiene(sustancia, cantidad));
        comprobarQue(unaCargaFueEntregada(sustancia, cantidad, CASILLERO_NORTE));
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasilleroConUnaPiezaCoAntimateriaAlNorte() {

        return precondicion(() -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

        });
    }

    private Postcondicion unaCargaFueEntregada(SustanciaEspacial sustancia, int cantidad, Casillero casillero) {

        return postcondicion(() -> verify(casillero).entregar(sustancia.por(cantidad)));
    }
}

