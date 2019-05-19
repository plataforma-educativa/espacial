package espacial.piezas.rasgos;

import static org.assertj.core.api.Assertions.*;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

public class PiezaAtacableTest implements Prueba {

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

        return precondicion("la piezaAtacada implementa PiezaAtacable", () -> {

             piezaAtacada = new PiezaAtacable() {

                @Override
                public void decrementarPuntosEn(int decremento) {

                    puntosDecrementados = decremento;
                }
            };
        });
    }

    private Postcondicion laPiezaAtacadaDecrementoSusPuntosEn(int decrementoEsperado) {

        return postcondicion("la piezaAtacada decrementó sus puntos en " + decrementoEsperado, () -> {

            assertThat(puntosDecrementados).as("puntos decrementados")
                    .isEqualTo(decrementoEsperado);
        });
    }
}
