package espacial.excepciones;

import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ExcedeElLugarDisponibleTest implements TestDeContrato {

    @Test
    public void getMessage() {

        assertThat(new ExcedeElLugarDisponible(200, 205))
                .hasNoCause()
                .hasMessage("'205' excede el lugar disponible de '200'");
    }
}