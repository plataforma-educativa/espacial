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
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.utiles.Nombre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

abstract class TestDeContratoSobreCazaEspacial extends TestDeContratoSobrePieza<CazaEspacial> {

    private final Amarre AMARRE = mock(Amarre.class, "AMARRE");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");
    private final Casillero CASILLERO_NORTE = mock(Casillero.class, "CASILLERO_NORTE");
    private final Casillero CASILLERO_SUR = mock(Casillero.class, "CASILLERO_SUR");
    private final Casillero CASILLERO_ESTE = mock(Casillero.class, "CASILLERO_ESTE");
    private final Casillero CASILLERO_OESTE = mock(Casillero.class, "CASILLERO_OESTE");

    private final Pieza OTRA_PIEZA = mock(Pieza.class, "OTRA_PIEZA");

    protected CazaEspacial unCazaEspacial;

    @BeforeEach
    void simularCasillero() {

        when(UN_CASILLERO.obtenerContiguoEn(Direccion.NORTE)).thenReturn(CASILLERO_NORTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.SUR)).thenReturn(CASILLERO_SUR);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.ESTE)).thenReturn(CASILLERO_ESTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.OESTE)).thenReturn(CASILLERO_OESTE);
    }

    abstract CazaEspacial piezaCreada(Nombre nombre);

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.NAVE;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return post(condicion -> verify(NAVE_ESPACIAL).chocoContraUnaNave());
    }

    @Test
    void obtenerNivelDeEscudosInicial() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(elNivelDeEscudosEs(100));
    }

    private Postcondicion elNivelDeEscudosEs(int nivelEsperado) {

        return post(condicion ->

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
        comprobarQue(esRemovidoDelTablero());
    }

    private Postcondicion esRemovidoDelTablero() {

        return post(condicion -> verify(UN_CASILLERO).desocupar());
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

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
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

        return pre(condicion -> {
            unCazaEspacial = piezaCreada();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private Postcondicion unCazaEspacialSeMovioUnCasilleroEnDireccionNorte() {

        return post(condicion -> verify(UN_CASILLERO).moverPiezaA(CASILLERO_NORTE));
    }

    @Test
    void moverEnDireccionMultiplesVeces() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.moverEn(Direccion.NORTE, 3);

        comprobarQue(unCazaEspacialSeMovioUnCasilleroEnDireccionNorte(3));
    }

    private Postcondicion unCazaEspacialSeMovioUnCasilleroEnDireccionNorte(int veces) {

        return post(condicion -> verify(UN_CASILLERO, times(veces)).moverPiezaA(CASILLERO_NORTE));
    }

    @Test
    void chocoContraUnAsteroide() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnAsteroide();

        comprobarQue(elNivelDeEscudosBajoHasta(75));
    }

    private Precondicion fueCreadoUnCazaEspacial() {

        return pre(condicion -> unCazaEspacial = piezaCreada());
    }

    private Postcondicion elNivelDeEscudosBajoHasta(int nivelDeEscudoEsperado) {

        return post(condicion ->

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

        return pre(condicion -> unCazaEspacial.fueAmarradaCon(AMARRE));
    }

    private Postcondicion unCazaEspacialSoltoElAmarre() {

        return post(condicion -> verify(AMARRE).soltar());
    }

    @Test
    void despegarCuandoNoEstaAmarrada() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnLaBase(() -> unCazaEspacial.despegar()));
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnLaBase(Operacion operacion) {

        return post(condicion -> assertThatThrownBy(operacion::ejecutar).isInstanceOf(LaNaveNoEstaEnLaBase.class));
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(losPuntosInicialesDeUnCazaEspacialSonCorrectos());
    }

    private Postcondicion losPuntosInicialesDeUnCazaEspacialSonCorrectos() {

        return post(condicion -> assertThat(unCazaEspacial.obtenerPuntos()).as("puntos").isEqualTo(100));
    }

    @Test
    void atacarEnDireccion() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlOeste());

        unCazaEspacial.atacarEn(Direccion.OESTE);

        comprobarQue(elCasilleroEnDireccionOesteFueAtacado());
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlOeste() {

        return pre(condicion -> {

            unCazaEspacial = piezaCreada();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

            when(CASILLERO_OESTE.obtenerPieza()).thenReturn(OTRA_PIEZA);
        });
    }

    private Postcondicion elCasilleroEnDireccionOesteFueAtacado() {

        return post(condicion -> {

            ArgumentCaptor<Ataque> ataqueCapturado = ArgumentCaptor.forClass(Ataque.class);

            verify(CASILLERO_OESTE).fueAtacadoCon(ataqueCapturado.capture());

        });
    }

    @Test
    void atacarEnUnaDireccionUtilizaTorpedosDeFotonesHasta100VecesLuegoUsaLaser() {

        final int TORPEDOS_DE_FOTONES = 100;
        final int LASER = 45;

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlNorte());

        repetir(TORPEDOS_DE_FOTONES + LASER, i -> unCazaEspacial.atacarEn(Direccion.NORTE));

        comprobarQue(otraPiezaFueAtacadaCon(TORPEDOS_DE_FOTONES, LASER));
    }

    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasilleroConOtraPiezaAlNorte() {

        return pre(condicion -> {

            unCazaEspacial = piezaCreada();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private Postcondicion otraPiezaFueAtacadaCon(int torpedos, int laser) {

        return post(condicion -> {

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

        return pre(condicion -> {

            unCazaEspacial = piezaCreada();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

            when(CASILLERO_NORTE.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
            when(CASILLERO_SUR.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
            when(CASILLERO_ESTE.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
            when(CASILLERO_OESTE.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
        });
    }

    private Postcondicion alEscanearAlrededorEncuentra(EspectroEspacial esperado) {

        return post(condicion -> {

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

        return pre(condicion -> unCazaEspacial = piezaCreada());
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

        return post(condicion ->

                assertThat(unCazaEspacial.obtenerCantidadDeTorpedosDeFotones())
                        .as("cantidad de torpedos")
                        .isEqualTo(100)
        );
    }

    @Test
    void buscarEnDireccionUnaSustanciaEspacial() {

        dadoQue(fueCreadoUnCazaEspacialConAntimateriaAlrededor(10, 0, 15, 30));

        comprobarQue(alBuscarAlrededorAntimateriaEncuentra(10, 0, 15, 30));
    }

    private Precondicion fueCreadoUnCazaEspacialConAntimateriaAlrededor(int alNorte, int alSur, int alEste, int alOeste) {

        return pre(condicion ->  {

            unCazaEspacial = piezaCreada();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

            when(CASILLERO_NORTE.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alNorte);
            when(CASILLERO_SUR.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alSur);
            when(CASILLERO_ESTE.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alEste);
            when(CASILLERO_OESTE.buscar(SustanciaEspacial.ANTIMATERIA)).thenReturn(alOeste);
        });
    }

    private Postcondicion alBuscarAlrededorAntimateriaEncuentra(int alNorte, int alSur, int alEste, int alOeste) {

        return post(condicion ->  {

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

        return post(condicion ->

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

        return pre(condicion ->  {

            unCazaEspacial = piezaCreada();
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

    private Postcondicion generaExcepcionPorqueExcedeElLugarDisponible(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
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

        return pre(condicion ->  {

            unCazaEspacial = piezaCreada();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);

        });
    }

    private Postcondicion unaCargaFueEntregada(SustanciaEspacial sustancia, int cantidad, Casillero casillero) {

        return post(condicion ->  verify(casillero).entregar(sustancia.por(cantidad)));
    }

    @Test
    void obtenerNivelDeCarga() {

        final int cantidad = 50;

        dadoQue(fueCreadoUnCazaEspacialRecibiendo(SustanciaEspacial.METAL.por(cantidad)));

        comprobarQue(elNivelDeCargaEs(cantidad));
    }

    private Postcondicion elNivelDeCargaEs(int nivel) {

        return post(condicion ->

                assertThat(unCazaEspacial.obtenerNivelDeCarga())
                        .as("nivel de carga")
                        .isEqualTo(nivel)
        );
    }

    @Test
    void nombrar() {

        final String ALFA_I = "ALFA I";
        dadoQue(fueCreadoUnCazaEspacialConElNombre(ALFA_I));

        comprobarQue(elNombreEs(ALFA_I));
    }

    private Precondicion fueCreadoUnCazaEspacialConElNombre(String nombre) {

        return pre(condicion ->  unCazaEspacial = piezaCreada(Nombre.sera(nombre).obtener()));
    }

    private Postcondicion elNombreEs(String nombreEsperado) {

        return post(condicion ->

                assertThat(unCazaEspacial.nombrar().obtener())
                        .as("nombre")
                        .isEqualTo(nombreEsperado)
        );
    }

    @Test
    void crearSinEspecificarElNombre() {

        unCazaEspacial = piezaCreada();

        comprobarQue(elNombreEs("Algoritmico I"));
    }

    @Test
    void obtenerRumbo() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        comprobarQue(tieneRumbo(Direccion.NORTE));
    }

    private Postcondicion tieneRumbo(Direccion rumboEsperado) {

        return post(condicion -> assertThat(unCazaEspacial.obtenerRumbo()).as("rumbo").isEqualTo(rumboEsperado));
    }

    @Test
    void obtenerRumboDespuesMoverseAlNorte() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.moverEn(Direccion.NORTE);

        comprobarQue(tieneRumbo(Direccion.NORTE));
    }

    @Test
    void obtenerRumboDespuesMoverseAlSur() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.moverEn(Direccion.SUR);

        comprobarQue(tieneRumbo(Direccion.SUR));
    }

    @Test
    void obtenerRumboDespuesMoverseAlEste() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.moverEn(Direccion.ESTE);

        comprobarQue(tieneRumbo(Direccion.ESTE));
    }

    @Test
    void obtenerRumboDespuesMoverseAlOeste() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.moverEn(Direccion.OESTE);

        comprobarQue(tieneRumbo(Direccion.OESTE));
    }

    @Test
    void obtenerRumboDespuesDeAtacarAlNorte() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.atacarEn(Direccion.NORTE);

        comprobarQue(tieneRumbo(Direccion.NORTE));
    }

    @Test
    void obtenerRumboDespuesDeAtacarAlSur() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.atacarEn(Direccion.SUR);

        comprobarQue(tieneRumbo(Direccion.SUR));
    }

    @Test
    void obtenerRumboDespuesDeAtacarAlEste() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.atacarEn(Direccion.ESTE);

        comprobarQue(tieneRumbo(Direccion.ESTE));
    }

    @Test
    void obtenerRumboDespuesDeAtacarAlOeste() {

        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());

        unCazaEspacial.atacarEn(Direccion.OESTE);

        comprobarQue(tieneRumbo(Direccion.OESTE));
    }

    @Test
    void aceptar() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.aceptar(UN_VISITANTE);

        comprobarQue(unVisitanteEsNave());
    }

    private Postcondicion unVisitanteEsNave() {

        return post(condicion -> verify(UN_VISITANTE).siEsNave(unCazaEspacial));
    }
}

