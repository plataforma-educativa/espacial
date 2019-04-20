package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;

public class AsteroideTest {

    @Test
    public void escanear() {
        
        Pieza pieza = new Asteroide();
        
        assertThat(pieza.escanear()).as("espectro").isEqualTo(EspectroEspacial.ASTEROIDE);
    }
}
