package espacial.excepciones;

import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ExcedeLaCargaDisponibleTest implements TestDeContrato {

    @Test
    public void getMessage() {

        assertThat(new ExcedeLaCargaDisponible(30, 80))
                .hasNoCause()
                .hasMessage("'80' excede la carga disponible de '30'");
    }
}