package espacial;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CargaTest {

    @Test
    public void por() {

        Carga carga = SustanciaEspacial.ANTIMATERIA.por(34);

        assertThat(carga).as("carga").isNotNull();
    }

    @Test
    public void obtenerCantidad() {

        Carga carga = SustanciaEspacial.ANTIMATERIA.por(230);

        assertThat(carga.obtenerCantidad()).as("cantidad").isEqualTo(230);
    }
}
