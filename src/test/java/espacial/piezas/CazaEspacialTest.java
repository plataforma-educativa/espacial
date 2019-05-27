package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.*;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.stream.IntStream;

public class CazaEspacialTest extends PruebaSobrePieza<CazaEspacial> {

    private final Amarre AMARRE = mock(Amarre.class, "AMARRE");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");
    private final Casillero CASILLERO_NORTE = mock(Casillero.class, "CASILLERO_NORTE");
    private final Casillero CASILLERO_SUR = mock(Casillero.class, "CASILLERO_SUR");
    private final Casillero CASILLERO_ESTE = mock(Casillero.class, "CASILLERO_ESTE");
    private final Casillero CASILLERO_OESTE = mock(Casillero.class, "CASILLERO_OESTE");

    private final Pieza OTRA_PIEZA = mock(Pieza.class, "OTRA_PIEZA");

    private CazaEspacial unCazaEspacial;

    @BeforeEach
    public void simularCasillero() {

        when(UN_CASILLERO.obtenerContiguoEn(Direccion.NORTE)).thenReturn(CASILLERO_NORTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.SUR)).thenReturn(CASILLERO_SUR);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.ESTE)).thenReturn(CASILLERO_ESTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.OESTE)).thenReturn(CASILLERO_OESTE);
    }

    @Override
    public CazaEspacial piezaCreada() {

        return new CazaEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.NAVE;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnaNave());
    }

    @Test
    public void obtenerNivelDeEscudosInicial() {

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
    public void disminuirNivelDeEscudosEn() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.disminuirNivelDeEscudosEn(34);

        comprobarQue(elNivelDeEscudosEs(66));
    }

    @Test
    public void disminuirNivelDeEscudosEnUnValorMayorA100() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.disminuirNivelDeEscudosEn(101);

        comprobarQue(elNivelDeEscudosEs(0));
    }

    @Test
    public void fueChocadaPorOtraNaveEspacial() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.fueChocadaPor(NAVE_ESPACIAL);

        comprobarQue(elNivelDeEscudosBajoHasta(70));
    }

    @Test
    public void moverEnDireccionCuandoNoDespego() {

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
    public void moverEnDireccion() {

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
    public void chocoContraUnAsteroide() {

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
    public void chocoContraUnContenedor() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnContenedor();

        comprobarQue(elNivelDeEscudosBajoHasta(90));
    }

    @Test
    public void chocoContraElBordeDelTablero() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraElBordeDelTablero();

        comprobarQue(elNivelDeEscudosBajoHasta(50));
    }

    @Test
    public void chocoContraUnAgujeroNegro() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnAgujeroNegro();

        comprobarQue(elNivelDeEscudosBajoHasta(25));
    }

    @Test
    public void chocoContraUnaNave() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnaNave();

        comprobarQue(elNivelDeEscudosBajoHasta(75));
    }

    @Test
    public void chocoContraUnaBase() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnaBase();

        comprobarQue(elNivelDeEscudosBajoHasta(95));
    }

    @Test
    public void despegar() {

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
    public void despegarCuandoNoEstaAmarrada() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnLaBase(() -> unCazaEspacial.despegar()));
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnLaBase(Ejecutable ejecutable) {

        return postcondicion(() -> assertThatThrownBy(ejecutable::ejecutar).isInstanceOf(LaNaveNoEstaEnLaBase.class));
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(losPuntosInicialesDeUnCazaEspacialSonCorrectos());
    }

    private Postcondicion losPuntosInicialesDeUnCazaEspacialSonCorrectos() {

        return postcondicion(() -> assertThat(unCazaEspacial.obtenerPuntos()).as("puntos").isEqualTo(100));
    }

    @Test
    public void atacarEnDireccion() {

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
    public void atacarEnUnaDireccionUtilizaTorpedosDeFotonesHasta100VecesLuegoUsaLaser() {

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
    public void escanearEnDireccion() {

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
    public void escanearEnDireccionCuandoNoEstaEnUnCasillero() {

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
    public void atacarEnDireccionCuandoNoEstaEnUnCasillero() {

        dadoQue(fueCreadoUnCazaEspacialQueNoSeColocoEnNingunCasillero());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.NORTE)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.SUR)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.ESTE)));
        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.atacarEn(Direccion.OESTE)));
    }

    @Test
    public void consultarCantidadDeTorpedosIniciales() {

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
}
