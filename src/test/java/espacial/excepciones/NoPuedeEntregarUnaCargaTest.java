package espacial.excepciones;

import espacial.Carga;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoPuedeEntregarUnaCargaTest implements TestDeContrato {

    private final Pieza UNA_PIEZA = mock(Pieza.class, "UNA_PIEZA");
    private final Carga UNA_CARGA = SustanciaEspacial.ANTIMATERIA.por(15);

    @BeforeEach
    void configurarMocks() {

        when(UNA_PIEZA.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
    }

    @Test
    void getMessage() {

        assertThat(new NoPuedeEntregarUnaCarga(UNA_PIEZA, UNA_CARGA))
                .hasNoCause()
                .hasMessage("ASTEROIDE no puede entregar una carga de '15 ANTIMATERIA'");
    }
}