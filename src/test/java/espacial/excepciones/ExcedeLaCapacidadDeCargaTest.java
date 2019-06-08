package espacial.excepciones;

import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ExcedeLaCapacidadDeCargaTest implements TestDeContrato {

    @Test
    public void getMessage() {

        assertThat(new ExcedeLaCapacidadDeCarga(200, 205))
                .hasNoCause()
                .hasMessage("'205' excede la capacidad de carga de '200'");
    }
}