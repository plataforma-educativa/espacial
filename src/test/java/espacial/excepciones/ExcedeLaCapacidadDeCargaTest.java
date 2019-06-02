package espacial.excepciones;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ExcedeLaCapacidadDeCargaTest {

    @Test
    public void getMessage() {

        assertThat(new ExcedeLaCapacidadDeCarga(200, 205))
                .hasNoCause()
                .hasMessage("La carga de '205' excede la capacidad de '200'");
    }
}