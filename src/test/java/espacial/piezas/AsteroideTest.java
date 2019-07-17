package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.Defecto;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Operacion;
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

class AsteroideTest extends TestDeContratoSobrePieza<Asteroide> {

    private Asteroide unAsteroide;

    @Override
    Asteroide piezaCreada() {

        return new Asteroide(Dureza.MAXIMA);
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return post(condicion ->  verify(NAVE_ESPACIAL).chocoContraUnAsteroide());
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadoUnAsteroide());
        comprobarQue(losPuntosInicialesDeUnAsteroideSonCorrectos());
    }

    private Precondicion fueCreadoUnAsteroide() {

        return pre(condicion ->  unAsteroide = new Asteroide(Dureza.MAXIMA));
    }

    private Postcondicion losPuntosInicialesDeUnAsteroideSonCorrectos() {

        return post(condicion ->  assertThat(unAsteroide.obtenerPuntos()).as("puntos").isEqualTo(300));
    }

    @Test
    void recibirUnaCarga() {

        dadoQue(fueCreadoUnAsteroide());

        comprobarQue(generaExcepcionPorqueNoPuedeRecirCarga(() ->

                unAsteroide.recibir(SustanciaEspacial.ANTIMATERIA.por(34)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeRecirCarga(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(NoPuedeRecibirUnaCarga.class)
        );
    }

    @Test
    void extraerUnaCarga() {

        dadoQue(fueCreadoUnAsteroide());

        comprobarQue(generaExcepcionPorqueNoPuedeEntregarUnaCarga(() ->

                unAsteroide.entregar(SustanciaEspacial.ANTIMATERIA.por(100)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeEntregarUnaCarga(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeEntregarUnaCarga.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void atacadoConLaser(int dureza, int puntosLuegoDelAtaqueConLaser) {

        dadoQue(fueCreadoUnAsteroideConDureza(dureza));

        unAsteroide.atacadoConLaser();

        comprobarQue(losPuntosDeUnAsteroideSon(puntosLuegoDelAtaqueConLaser));
    }

    static Stream<Arguments> atacadoConLaser() {

        return Stream.of(
                arguments(100, 295),
                arguments(90, 295),
                arguments(70, 293),
                arguments(50, 290),
                arguments(25, 280),
                arguments(10, 250),
                arguments(5, 200)
        );
    }

    private Precondicion fueCreadoUnAsteroideConDureza(int valorDureza) {

        return pre(condicion ->  unAsteroide = new Asteroide(valorDureza));
    }

    private Postcondicion losPuntosDeUnAsteroideSon(int esperados) {

        return post(condicion ->  assertThat(unAsteroide.obtenerPuntos()).as("puntos").isEqualTo(esperados));
    }

    @ParameterizedTest
    @MethodSource
    void atacadoConUnTopedoDeFotones(int dureza, int puntosLuegoDelAtaqueConUnTorpedoDeFotones) {

        dadoQue(fueCreadoUnAsteroideConDureza(dureza));

        unAsteroide.atacadoConTorpedoDeFotones();

        comprobarQue(losPuntosDeUnAsteroideSon(puntosLuegoDelAtaqueConUnTorpedoDeFotones));
    }

    static Stream<Arguments> atacadoConUnTopedoDeFotones() {

        return Stream.of(
                arguments(100, 290),
                arguments(90, 289),
                arguments(70, 286),
                arguments(50, 280),
                arguments(25, 260),
                arguments(10, 200),
                arguments(5, 100)
        );
    }

    @Test
    void crearConDurezaMinima() {

        final int dureza = Dureza.MINIMA;

        unAsteroide = new Asteroide(dureza);

        comprobarQue(unAsteroideTieneLaDurezaEsperada(dureza));
    }

    @Test
    void crearConDurezaMaxima() {

        final int dureza = Dureza.MAXIMA;

        unAsteroide = new Asteroide(dureza);

        comprobarQue(unAsteroideTieneLaDurezaEsperada(dureza));
    }

    @Test
    void crearConDurezaMenorAlMinimo() {

        comprobarQue(generaDefecto(() ->  new Asteroide(Dureza.MINIMA - 1)));
    }

    @Test
    void crearConDurezaManorAlMaximo() {

        comprobarQue(generaDefecto(() ->  new Asteroide(Dureza.MAXIMA + 1)));
    }

    private Postcondicion unAsteroideTieneLaDurezaEsperada(int dureza) {

        return post(condicion ->  assertThat(unAsteroide.obtenerDureza().obtener()).as("dureza").isEqualTo(dureza));
    }

    private Postcondicion generaDefecto(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(Defecto.class)
        );
    }
}
