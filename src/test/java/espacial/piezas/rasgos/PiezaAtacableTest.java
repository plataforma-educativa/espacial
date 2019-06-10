package espacial.piezas.rasgos;

import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Visitante;
import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PiezaAtacableTest implements TestDeContrato {

    private int puntosDecrementados;

    private PiezaAtacable piezaAtacada;

    @Test
    public void atacadoConTorpedoDeFotones() {

        dadoQue(laPiezaAtacadaImplementaPiezaAtacable());

        piezaAtacada.atacadoConTorpedoDeFotones();

        comprobarQue(laPiezaAtacadaDecrementoSusPuntosEn(10));

    }

    @Test
    public void atacadoConLaser() {

        dadoQue(laPiezaAtacadaImplementaPiezaAtacable());

        piezaAtacada.atacadoConLaser();

        comprobarQue(laPiezaAtacadaDecrementoSusPuntosEn(5));
    }

    private Precondicion laPiezaAtacadaImplementaPiezaAtacable() {

        return precondicion(() -> piezaAtacada = new PiezaAtacable() {

            @Override
            public void decrementarPuntosEn(int decremento) {

                puntosDecrementados = decremento;
            }

            @Override
            public void fueChocadaPor(Chocable chocable) {

                throw new LaOperacionNoEstaSoportada("fueChocadaPor(Chocable)");
            }

            @Override
            public EspectroEspacial escanear() {

                throw new LaOperacionNoEstaSoportada("escanear()");

            }

            @Override
            public void aceptar(Visitante visitante) {

                throw new LaOperacionNoEstaSoportada("aceptar(Visitante)");
            }

            @Override
            public int obtenerPuntos() {

                throw new LaOperacionNoEstaSoportada("obtenerPuntos()");
            }
        });
    }

    private Postcondicion laPiezaAtacadaDecrementoSusPuntosEn(int decrementoEsperado) {

        return postcondicion(() ->
                assertThat(puntosDecrementados)
                        .as("puntos decrementados")
                        .isEqualTo(decrementoEsperado));
    }
}
