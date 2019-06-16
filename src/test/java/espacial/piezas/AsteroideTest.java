package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.*;

public class AsteroideTest extends TestDeContratoSobrePieza<Asteroide> {

    private Asteroide unAsteroide;

    @Override
    public Asteroide piezaCreada() {

        return new Asteroide();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnAsteroide());
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnAsteroide());
        comprobarQue(losPuntosInicialesDeUnAsteroideSonCorrectos());
    }

    private Precondicion fueCreadoUnAsteroide() {

        return precondicion(() -> unAsteroide = new Asteroide());
    }

    private Postcondicion losPuntosInicialesDeUnAsteroideSonCorrectos() {

        return postcondicion(() -> assertThat(unAsteroide.obtenerPuntos()).as("puntos").isEqualTo(300));
    }

    @Test
    public void recibirUnaCarga() {

        dadoQue(fueCreadoUnAsteroide());

        comprobarQue(generaExcepcionPorqueNoPuedeRecirCarga(() ->

                unAsteroide.recibir(SustanciaEspacial.ANTIMATERIA.por(34)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeRecirCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(NoPuedeRecibirUnaCarga.class)
        );
    }

    @Test
    public void extraerUnaCarga() {

        dadoQue(fueCreadoUnAsteroide());

        comprobarQue(generaExcepcionPorqueNoPuedeEntregarUnaCarga(() ->

                unAsteroide.entregar(SustanciaEspacial.ANTIMATERIA.por(100)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeEntregarUnaCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeEntregarUnaCarga.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void atacadoConLaser(int dureza, int puntosLuegoDelAtaqueConLaser) {

        dadoQue(fueCreadoUnAsteroideConDureza(dureza));

        unAsteroide.atacadoConLaser();

        comprobarQue(losPuntosDeUnAsteroideSon(puntosLuegoDelAtaqueConLaser));
    }

    public static Stream<Arguments> atacadoConLaser() {

        return Stream.of(
                arguments(100, 295),
                arguments(90, 295),
                arguments(70, 293),
                arguments(50, 290),
                arguments(25, 280),
                arguments(10, 250),
                arguments(5, 200),
                arguments(1, 0)
        );
    }

    private Precondicion fueCreadoUnAsteroideConDureza(int valorDureza) {

        return precondicion(() -> unAsteroide = new Asteroide(valorDureza));
    }

    private Postcondicion losPuntosDeUnAsteroideSon(int esperados) {

        return postcondicion(() -> assertThat(unAsteroide.obtenerPuntos()).as("puntos").isEqualTo(esperados));
    }

    @ParameterizedTest
    @MethodSource
    public void atacadoConUnTopedoDeFotones(int dureza, int puntosLuegoDelAtaqueConUnTorpedoDeFotones) {

        dadoQue(fueCreadoUnAsteroideConDureza(dureza));

        unAsteroide.atacadoConTorpedoDeFotones();

        comprobarQue(losPuntosDeUnAsteroideSon(puntosLuegoDelAtaqueConUnTorpedoDeFotones));
    }

    public static Stream<Arguments> atacadoConUnTopedoDeFotones() {

        return Stream.of(
                arguments(100, 290),
                arguments(90, 289),
                arguments(70, 286),
                arguments(50, 280),
                arguments(25, 260),
                arguments(10, 200),
                arguments(5, 100),
                arguments(1, 0)
        );
    }

}
