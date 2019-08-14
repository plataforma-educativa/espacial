import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EscenarioEspacialTest extends TestDeBatallaEspacial implements TestDeContrato {

    private EscenarioEspacial unEscenario;

    @Test
    public void crearConLasDimensionesEspecificadas() {

        dadoQue(unEscenarioFueCreadoCon(10,10));

        comprobarQue(unEscenarioTieneCasillerosEntre(1, 10, 1, 10));
    }

    private Precondicion unEscenarioFueCreadoCon(int filas, int columnas) {

        return pre(condicion -> unEscenario = new EscenarioEspacial(filas, columnas));
    }

    private Postcondicion unEscenarioTieneCasillerosEntre(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        return post(condicion -> {

            assertThat(unEscenario.obtenerFilaMinima()).as("fila mínima").isEqualTo(filaDesde);
            assertThat(unEscenario.obtenerFilaMaxima()).as("fila máxima").isEqualTo(filaHasta);
            assertThat(unEscenario.obtenerColumnaMinima()).as("columna mínima").isEqualTo(columnaDesde);
            assertThat(unEscenario.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(columnaHasta);
        });
    }
}
