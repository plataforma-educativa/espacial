package espacial;

import espacial.excepciones.ParametroInvalido;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CargaTest implements TestDeContrato {

    @Test
    void por() {

        Carga carga = SustanciaEspacial.ANTIMATERIA.por(34);

        assertThat(carga).as("carga").isNotNull();
    }

    @Test
    void obtenerCantidad() {

        Carga carga = SustanciaEspacial.ANTIMATERIA.por(230);

        assertThat(carga.obtenerCantidad()).as("cantidad").isEqualTo(230);
    }

    @Test
    void tieneToString() {

        Carga carga = SustanciaEspacial.ANTIMATERIA.por(42);

        assertThat(carga).hasToString("42 ANTIMATERIA");
    }

    @Test
    void porCantidadNula() {

        assertThatThrownBy(() -> SustanciaEspacial.ANTIMATERIA.por(0))
                .as("excepción generada")
                .isInstanceOf(ParametroInvalido.class)
                .hasMessage("La cantidad de Sustancia debe ser mayor a 0");
    }

    @Test
    void porCantidadNegativa() {

        assertThatThrownBy(() -> SustanciaEspacial.ANTIMATERIA.por(-234))
                .as("excepción generada")
                .isInstanceOf(ParametroInvalido.class)
                .hasMessage("La cantidad de Sustancia debe ser mayor a 0");
    }
}
