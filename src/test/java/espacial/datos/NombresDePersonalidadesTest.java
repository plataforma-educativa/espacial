package espacial.datos;

import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NombresDePersonalidadesTest implements TestDeContrato {

    @Test
    void listar() {

        assertThat(NombresDePersonalidades.listar())
                .as("nombres")
                .hasSize(237)
                .allMatch(nombre -> nombre != null && nombre.obtener() != null && nombre.explicar() != null);
    }
}