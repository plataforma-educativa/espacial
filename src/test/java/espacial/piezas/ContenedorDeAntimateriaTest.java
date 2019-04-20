package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;

public class ContenedorDeAntimateriaTest {

    @Test
    public void escanear() {
        
        Pieza pieza = new ContenedorDeAntimateria();
        
        assertThat(pieza.escanear()).as("espectro").isEqualTo(EspectroEspacial.CONTENEDOR);
    }
}
