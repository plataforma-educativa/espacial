package espacial.piezas;

import static org.mockito.Mockito.*;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;

public class AgujeroNegroTest extends PruebaSobrePieza<AgujeroNegro> {

    @Override
    public AgujeroNegro piezaCreada() {

        return new AgujeroNegro();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra un AgujeroNegro", () -> {
            
            verify(PIEZA_MOVIL).chocoContraUnAgujeroNegro();
        });
    }
}
