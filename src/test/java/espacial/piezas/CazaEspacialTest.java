package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;

public class CazaEspacialTest {

    @Test
    public void escanear() {
        
        Pieza pieza = new CazaEspacial();
        
        assertThat(pieza.escanear()).as("espectro").isEqualTo(EspectroEspacial.NAVE);
    }
}
