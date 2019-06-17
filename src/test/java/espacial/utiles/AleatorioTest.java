package espacial.utiles;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AleatorioTest implements TestDeContrato {

    private final List<Integer> valores = new LinkedList<>();

    private Aleatorio unAleatorio;

    @Test
    void obtener() {

        final int desde = 3;
        final int hasta = 90;

        dadoQue(fueCreadoUnAleatorio(desde, hasta));

        for (int i = 0; i < 10_000; i++) {
            valores.add(unAleatorio.obtener());
        }

        comprobarQue(losValoresEstanDistribuidosEnElRango(desde, hasta));
    }

    private Precondicion fueCreadoUnAleatorio(int desde, int hasta) {

        return precondicion(() -> unAleatorio = new Aleatorio(desde, hasta));
    }

    private Postcondicion losValoresEstanDistribuidosEnElRango(int desde, int hasta) {

        return postcondicion(() -> {

            assertThat(valores)
                    .as("valores")
                    .allMatch(valor -> (valor >= desde) && (valor <= hasta));

            assertThat(valores.stream().distinct().count())
                    .as("cantidad de valores diferentes generados")
                    .isEqualTo(hasta - desde + 1);
        });
    }

    @Test
    void obtenerSePuedeRestringirEnUnUnicoValor() {

        final int unicoValor = 10;

        dadoQue(fueCreadoUnAleatorio(unicoValor, unicoValor));

        for (int i = 0; i < 10_000; i++) {
            valores.add(unAleatorio.obtener());
        }

        comprobarQue(losValoresObtenidosTodosIgualesA(unicoValor));
    }

    private Postcondicion losValoresObtenidosTodosIgualesA(int unicoValor) {

        return postcondicion(() -> assertThat(valores).as("valores").allMatch(valor -> valor == unicoValor));
    }

}