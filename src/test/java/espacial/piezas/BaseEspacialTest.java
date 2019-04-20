package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;

public class BaseEspacialTest {

    @Test
    public void escanear() {
        
        Pieza pieza = new BaseEspacial();
        
        assertThat(pieza.escanear()).as("espectro").isEqualTo(EspectroEspacial.BASE);
    }
}
