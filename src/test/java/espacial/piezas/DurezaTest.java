package espacial.piezas;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class DurezaTest implements TestDeContrato {

    private Dureza unaDureza;

    private int ataquePonderado;

    @Test
    void crear() {

        dadoQue(fueCreadaUnaDurezaDe(30));

        comprobarQue(elValorDeUnaDurezaEs(30));
    }

    private Postcondicion elValorDeUnaDurezaEs(int esperado) {

        return post(condicion ->  assertThat(unaDureza.obtener()).as("valor").isEqualTo(esperado));
    }

    @ParameterizedTest
    @MethodSource
    void ponderarAtaqueDe(int dureza, int puntos, int puntosPonderados) {

        dadoQue(fueCreadaUnaDurezaDe(dureza));

        ataquePonderado = unaDureza.ponderarAtaqueDe(puntos);

        comprobarQue(elAtaquePonderadoEsDe(puntosPonderados));
    }

    static Stream<Arguments> ponderarAtaqueDe() {

        return Stream.of(
                arguments(100, 5, 5),
                arguments(100, 1, 1),
                arguments(50, 1, 2),
                arguments(50, 10, 20),
                arguments(25, 10, 40),
                arguments(5, 2, 40),
                arguments(5, 20, 400)
        );
    }

    private Precondicion fueCreadaUnaDurezaDe(int valor) {

        return pre(condicion ->  unaDureza = new Dureza(valor));
    }

    private Postcondicion elAtaquePonderadoEsDe(int valor) {

        return post(condicion ->  assertThat(ataquePonderado).isEqualTo(valor));
    }
}