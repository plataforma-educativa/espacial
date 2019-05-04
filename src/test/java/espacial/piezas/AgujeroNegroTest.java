package espacial.piezas;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;
import espacial.test.Postcondicion;

public class AgujeroNegroTest implements PruebaSobrePieza<AgujeroNegro> {

    private final PiezaMovil PIEZA_MOVIL = mock(PiezaMovil.class);

    @Override
    public AgujeroNegro piezaCreada() {

        return new AgujeroNegro();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.DESCONOCIDO;
    }
    
    @Test
    public void fueChocadaPor() {
        
        Pieza pieza = piezaCreada();

        pieza.fueChocadaPor(PIEZA_MOVIL);
        
        comprobarQue(notificoALaPiezaMovilQueChocoContraUnAgujeroNegro());
    }

    private Postcondicion notificoALaPiezaMovilQueChocoContraUnAgujeroNegro() {

        return postcondicion("notificó a la PiezaMovil que chocó contra un AgujeroNegro", () -> {
            
            verify(PIEZA_MOVIL).chocoContraUnAgujeroNegro();
        });
    }
}
