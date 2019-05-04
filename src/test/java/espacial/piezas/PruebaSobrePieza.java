package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public abstract class PruebaSobrePieza<T extends Pieza> implements Prueba {

    protected final PiezaMovil PIEZA_MOVIL = mock(PiezaMovil.class);
    
    protected abstract T piezaCreada();
    
    protected abstract EspectroEspacial espectroEsperado();
    
    protected abstract Postcondicion laPiezaMovilFueNotificadaDelChoque();

    @Test
    public void escanear() {
        
        Pieza pieza = piezaCreada();
        
        EspectroEspacial espectro = pieza.escanear();
        
        comprobarQue(elEspectroEscaneadoEsElEsperado(espectro));
    }

    private Postcondicion elEspectroEscaneadoEsElEsperado(EspectroEspacial espectro) {
        
        return postcondicion("el EspectroEspacial escaneado", () -> {
           
            assertThat(espectro).isEqualTo(espectroEsperado());
        });
    }
    
    @Test
    public void fueChocadaPor() {
        
        Pieza pieza = piezaCreada();

        pieza.fueChocadaPor(PIEZA_MOVIL);
        
        comprobarQue(laPiezaMovilFueNotificadaDelChoque());
    }
}
