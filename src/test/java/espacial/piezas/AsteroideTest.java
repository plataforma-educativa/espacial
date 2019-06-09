package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.NoPuedeExtraerUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
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

        return postcondicion(() -> assertThat(unAsteroide.obtenerPuntos()).as("puntos").isEqualTo(90));
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

        comprobarQue(generaExcepcionPorqueNoPuedeExtraerCarga(() ->

                unAsteroide.extraer(SustanciaEspacial.ANTIMATERIA.por(100)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeExtraerCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeExtraerUnaCarga.class)
        );
    }
}
