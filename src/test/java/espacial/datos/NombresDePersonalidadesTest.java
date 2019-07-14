package espacial.datos;

import espacial.test.TestDeContrato;
import espacial.utiles.Nombre;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

class NombresDePersonalidadesTest implements TestDeContrato {

    @Test
    void listar() {

        List<Nombre> nombres = NombresDePersonalidades.listar();

        assertThat(nombres)
                .as("nombres")
                .hasSize(237)
                .allMatch(nombre -> nombre != null && nombre.obtener() != null && nombre.explicar() != null);

        assertThat(nombres)
                .extracting(Nombre::obtener)
                .isSorted();

        nombres.forEach(nombre -> assertThat(nombre.obtener()).matches(Pattern.compile("^[A-Z][a-z]+$")));
    }
}