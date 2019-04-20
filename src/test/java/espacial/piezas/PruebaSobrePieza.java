package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public interface PruebaSobrePieza<T extends Pieza> extends Prueba {

    T piezaCreada();
    
    EspectroEspacial espectroEsperado();

    @Test
    default void escanear() {
        
        Pieza pieza = piezaCreada();
        
        EspectroEspacial espectro = pieza.escanear();
        
        comprobarQue(elEspectroEscaneadoEsElEsperado(espectro));
    }

    default Postcondicion elEspectroEscaneadoEsElEsperado(EspectroEspacial espectro) {
        
        return postcondicion("el EspectroEspacial escaneado", () -> {
           
            assertThat(espectro).isEqualTo(espectroEsperado());
        });
    }

}
