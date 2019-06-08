package espacial.piezas.rasgos;

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
        });
    }

    private Postcondicion laPiezaAtacadaDecrementoSusPuntosEn(int decrementoEsperado) {

        return postcondicion(() ->
                assertThat(puntosDecrementados)
                        .as("puntos decrementados")
                        .isEqualTo(decrementoEsperado));
    }
}
