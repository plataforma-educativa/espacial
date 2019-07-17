package espacial.partidas;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

class NomencladorTest implements TestDeContrato {

    private Nomenclador unNomenclador;

    @Test
    void nombrarNave() {

        dadoQue(fueCreadoUnNomenclador());

        String nombre = unNomenclador.nombrarNave().obtener();

        comprobarQue(laNaveFueNombradaCorrectamente(nombre));
    }

    private Postcondicion laNaveFueNombradaCorrectamente(String nombre) {

        return post(condicion ->

                assertThat(nombre).as("nombre")
                        .doesNotContainAnyWhitespaces()
                        .containsPattern(Pattern.compile("^[A-Z][a-z]+$"))
                        .isNotNull()
        );
    }

    private Precondicion fueCreadoUnNomenclador() {

        return pre(condicion ->  unNomenclador = new Nomenclador());
    }

    @Test
    void nombrarMultiplesNaves() {

        dadoQue(fueCreadoUnNomenclador());

        List<String> nombres = new LinkedList<>();
        for (int i = 0; i < 100; i++) {

            nombres.add(unNomenclador.nombrarNave().obtener());
        }

        comprobarQue(todasLasNavesFueronNombradasCorrectamente(nombres));
    }

    private Postcondicion todasLasNavesFueronNombradasCorrectamente(List<String> nombres) {

        return post(condicion ->  assertThat(nombres).as("nombres").doesNotHaveDuplicates());
    }
}