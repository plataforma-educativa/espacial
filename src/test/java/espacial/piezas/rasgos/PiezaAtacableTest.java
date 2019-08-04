package espacial.piezas.rasgos;

import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Faccion;
import espacial.Visitante;
import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PiezaAtacableTest implements TestDeContrato {

    private int puntosDecrementados;

    private PiezaAtacable piezaAtacada;

    @Test
    void atacadoConTorpedoDeFotones() {

        dadoQue(laPiezaAtacadaImplementaPiezaAtacable());

        piezaAtacada.atacadoConTorpedoDeFotones();

        comprobarQue(laPiezaAtacadaDecrementoSusPuntosEn(10));

    }

    @Test
    void atacadoConLaser() {

        dadoQue(laPiezaAtacadaImplementaPiezaAtacable());

        piezaAtacada.atacadoConLaser();

        comprobarQue(laPiezaAtacadaDecrementoSusPuntosEn(5));
    }

    private Precondicion laPiezaAtacadaImplementaPiezaAtacable() {

        return pre(condicion ->  piezaAtacada = new PiezaAtacable() {

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
            public Faccion reconocer() {

                throw new LaOperacionNoEstaSoportada("reconocer()");
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

        return post(condicion ->
                assertThat(puntosDecrementados)
                        .as("puntos decrementados")
                        .isEqualTo(decrementoEsperado));
    }
}
