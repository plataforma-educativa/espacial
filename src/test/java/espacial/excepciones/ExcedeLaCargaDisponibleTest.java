package espacial.excepciones;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ExcedeLaCargaDisponibleTest {

    @Test
    public void getMessage() {

        assertThat(new ExcedeLaCargaDisponible(30, 80))
                .hasNoCause()
                .hasMessage("'80' excede la carga disponible de '30'");
    }
}