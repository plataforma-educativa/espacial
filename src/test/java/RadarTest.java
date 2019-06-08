import espacial.SustanciaEspacial;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class RadarTest implements TestDeContrato {

    private static Condition<Integer> CERO = new Condition<>(valor -> valor == 0, "igual a 0");
    private static Condition<Integer> MAYOR_A_CERO = new Condition<>(valor -> valor > 0, "mayor a 0");

    private static final int NADA = 0;

    private Radar unRadar;
    private Espectro escaneadoAlNorte;
    private Espectro escaneadoAlSur;
    private Espectro escaneadoAlEste;
    private Espectro escaneadoAlOeste;
    private int cantidadAlNorte;
    private int cantidadAlSur;
    private int cantidadAlEste;
    private int cantidadAlOeste;

    @Test
    public void escanarSinHaberDespegado() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveQueNoDespego());

        comprobarQue(generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(() -> unRadar.escanearNorte()));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveQueNoDespego() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();

            unRadar = nave.obtenerRadar();
        });
    }

    private Postcondicion generaExcepcionPorqueLaNaveNoEstaEnUnCasillero(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(LaNaveNoEstaEnUnCasillero.class)
        );
    }

    @Test
    public void escanearEnLaBase() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveEnLaBase());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.VACIO, Espectro.VACIO, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveEnLaBase() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();

            unRadar = nave.obtenerRadar();
        });
    }

    private void escanearConUnRadarAlrededor() {

        escaneadoAlNorte = unRadar.escanearNorte();
        escaneadoAlSur = unRadar.escanearSur();
        escaneadoAlEste = unRadar.escanearEste();
        escaneadoAlOeste = unRadar.escanearOeste();
    }

    private Postcondicion losEspectrosEscaneadosSonLosEsperados(Espectro alNorte, Espectro alSur,
                                                                Espectro alEste, Espectro alOeste) {

        return postcondicion(() -> {

            assertThat(escaneadoAlNorte).as("espectro escaneado al NORTE").isEqualTo(alNorte);
            assertThat(escaneadoAlSur).as("espectro escaneado al SUR").isEqualTo(alSur);
            assertThat(escaneadoAlEste).as("espectro escaneado al ESTE").isEqualTo(alEste);
            assertThat(escaneadoAlOeste).as("espectro escaneado al OESTE").isEqualTo(alOeste);
        });
    }


    @Test
    public void escanearAlOesteDeUnContenedor() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedorConAntimateria());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(
                Espectro.VACIO,
                Espectro.VACIO,
                Espectro.CONTENEDOR,
                Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedorConAntimateria() {

        return precondicion(() -> {

            BatallaEspacial batalla = new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 4).forEach(n -> nave.avanzarAlNorte());
            nave.avanzarAlEste();

            batalla.obtenerTablero()
                    .obtenerCasilleroEn(4, 2)
                    .obtenerPieza()
                    .recibir(SustanciaEspacial.ANTIMATERIA.por(100));

            unRadar = nave.obtenerRadar();
        });
    }

    @Test
    public void escanearAlNorteDeUnaBase() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlNorteDeUnaBase());

        escanearConUnRadarAlrededor();

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.VACIO, Espectro.BASE, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlNorteDeUnaBase() {

        return precondicion(() -> {

            new BatallaEspacial();
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

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.ASTEROIDE, Espectro.VACIO, Espectro.VACIO, Espectro.VACIO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlSurDeUnAsteroide() {

        return precondicion(() -> {

            new BatallaEspacial();
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

        comprobarQue(losEspectrosEscaneadosSonLosEsperados(Espectro.ASTEROIDE, Espectro.VACIO, Espectro.VACIO, Espectro.DESCONOCIDO));
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlEsteDeDesconocido() {

        return precondicion(() -> {

            new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 5).forEach(n -> nave.avanzarAlOeste());
            nave.avanzarAlNorte();

            unRadar = nave.obtenerRadar();
        });
    }

    @Test
    public void evaluarVariable() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveEnLaBase());

        comprobarQue(alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro());
    }

    private Postcondicion alEvaluarUnaVariableEnElInterpreteSeMuestraUnMensajeClaro() {

        return postcondicion(() -> assertThat(unRadar).hasToString("Radar de la Nave"));
    }

    @Test
    public void buscarAlNorteAntimateriaLaEncuentra() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlSurDeUnContenedorConAntimateria());

        buscarAntimateriaConUnRadarAlrededor();

        comprobarQue(lasCantidadesEncontradasSonLasEsperadas(MAYOR_A_CERO, CERO, CERO, CERO));
    }

    @Test
    public void buscarAlSurAntimateriaLaEncuentra() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlNorteDeUnContenedorConAntimateria());

        buscarAntimateriaConUnRadarAlrededor();

        comprobarQue(lasCantidadesEncontradasSonLasEsperadas(CERO, MAYOR_A_CERO, CERO, CERO));
    }

    @Test
    public void buscarAlEsteAntimateriaLaEncuentra() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlOesteDeUnContenedorConAntimateria());

        buscarAntimateriaConUnRadarAlrededor();

        comprobarQue(lasCantidadesEncontradasSonLasEsperadas(CERO, CERO, MAYOR_A_CERO, CERO));
    }

    @Test
    public void buscarAlOesteAntimateriaLaEncuentra() {

        dadoQue(fueObtenidoUnRadarDeUnaNaveAlEsteDeUnContenedorConAntimateria());

        buscarAntimateriaConUnRadarAlrededor();

        comprobarQue(lasCantidadesEncontradasSonLasEsperadas(CERO, CERO, CERO, MAYOR_A_CERO));
    }

    private void buscarAntimateriaConUnRadarAlrededor() {

        cantidadAlNorte = unRadar.buscarAlNorte(Sustancia.ANTIMATERIA);
        cantidadAlSur = unRadar.buscarAlSur(Sustancia.ANTIMATERIA);
        cantidadAlEste = unRadar.buscarAlEste(Sustancia.ANTIMATERIA);
        cantidadAlOeste = unRadar.buscarAlOeste(Sustancia.ANTIMATERIA);
    }

    private Postcondicion lasCantidadesEncontradasSonLasEsperadas(Condition<Integer> esperadoAlNorte,
                                                                  Condition<Integer> esperadoAlSur,
                                                                  Condition<Integer> esperadoAlEste,
                                                                  Condition<Integer> esperadoAlOeste) {

        return postcondicion(() -> {

            assertThat(cantidadAlNorte).as("cantidad al NORTE").is(esperadoAlNorte);
            assertThat(cantidadAlSur).as("cantidad al SUR").is(esperadoAlSur);
            assertThat(cantidadAlEste).as("cantidad al ESTE").is(esperadoAlEste);
            assertThat(cantidadAlOeste).as("cantidad al OESTE").is(esperadoAlOeste);
        });
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlEsteDeUnContenedorConAntimateria() {

        return precondicion(() -> {

            BatallaEspacial batalla = new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 2).forEach(n -> nave.avanzarAlSur());
            IntStream.range(0, 1).forEach(n -> nave.avanzarAlOeste());

            batalla.obtenerTablero()
                    .obtenerCasilleroEn(-2, -2)
                    .obtenerPieza()
                    .recibir(SustanciaEspacial.ANTIMATERIA.por(100));

            unRadar = nave.obtenerRadar();
        });
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlNorteDeUnContenedorConAntimateria() {

        return precondicion(() -> {

            BatallaEspacial batalla = new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 2).forEach(n -> nave.avanzarAlOeste());
            IntStream.range(0, 1).forEach(n -> nave.avanzarAlSur());

            batalla.obtenerTablero()
                    .obtenerCasilleroEn(-2, -2)
                    .obtenerPieza()
                    .recibir(SustanciaEspacial.ANTIMATERIA.por(100));

            unRadar = nave.obtenerRadar();
        });
    }

    private Precondicion fueObtenidoUnRadarDeUnaNaveAlSurDeUnContenedorConAntimateria() {

        return precondicion(() -> {

            BatallaEspacial batalla = new BatallaEspacial();
            Nave nave = new Nave();
            nave.despegar();
            IntStream.range(0, 2).forEach(n -> nave.avanzarAlEste());
            IntStream.range(0, 3).forEach(n -> nave.avanzarAlNorte());

            batalla.obtenerTablero()
                    .obtenerCasilleroEn(4, 2)
                    .obtenerPieza()
                    .recibir(SustanciaEspacial.ANTIMATERIA.por(100));

            unRadar = nave.obtenerRadar();
        });
    }

}
