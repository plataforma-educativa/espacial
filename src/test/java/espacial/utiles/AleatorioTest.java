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
    private final List<String> valoresString = new LinkedList<>();

    private Aleatorio<Integer> unAleatorio;

    private Aleatorio<String> unStringAleatorio;

    @Test
    void obtenerEnRango() {

        final int desde = 3;
        final int hasta = 90;

        dadoQue(fueCreadoUnAleatorio(desde, hasta));

        for (int i = 0; i < 10_000; i++) {
            valores.add(unAleatorio.obtener());
        }

        comprobarQue(losValoresEstanDistribuidosEnElRango(desde, hasta));
    }

    private Precondicion fueCreadoUnAleatorio(int desde, int hasta) {

        return pre(() -> unAleatorio = Aleatorio.enRango(desde, hasta));
    }

    private Postcondicion losValoresEstanDistribuidosEnElRango(int desde, int hasta) {

        return post(() -> {

            assertThat(valores)
                    .as("valores")
                    .allMatch(valor -> (valor >= desde) && (valor <= hasta));

            assertThat(valores.stream().distinct().count())
                    .as("cantidad de valores diferentes generados")
                    .isEqualTo(hasta - desde + 1);
        });
    }

    @Test
    void obtenerEnRangoSePuedeRestringirEnUnUnicoValor() {

        final int unicoValor = 10;

        dadoQue(fueCreadoUnAleatorio(unicoValor, unicoValor));

        for (int i = 0; i < 10_000; i++) {
            valores.add(unAleatorio.obtener());
        }

        comprobarQue(losValoresObtenidosTodosIgualesA(unicoValor));
    }

    private Postcondicion losValoresObtenidosTodosIgualesA(int unicoValor) {

        return post(() -> assertThat(valores).as("valores").allMatch(valor -> valor == unicoValor));
    }

    @Test
    void obtenerEnLista() {

        final String valor1 = "Luke";
        final String valor2 = "Joda";
        final String valor3 = "Obi-Wan";

        dadoQue(fueCreadoUnAleatorio(valor1, valor2, valor3));

        for (int i = 0; i < 10_000; i++) {
            valoresString.add(unStringAleatorio.obtener());
        }

        comprobarQue(losValoresEstanDistribuidosEntre(valor1, valor2, valor3));

    }

    private Precondicion fueCreadoUnAleatorio(String... valores) {

        return pre(() -> unStringAleatorio = Aleatorio.enLista(valores));
    }

    private Postcondicion losValoresEstanDistribuidosEntre(String... esperados) {

        return post(() -> {

            assertThat(valoresString)
                    .as("valores")
                    .containsOnly(esperados);

            assertThat(valoresString.stream().distinct().count())
                    .as("cantidad de valores diferentes generados")
                    .isEqualTo(esperados.length);
        });
    }
}