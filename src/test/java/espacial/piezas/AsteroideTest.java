package espacial.piezas;

import static org.mockito.Mockito.*;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;

public class AsteroideTest extends PruebaSobrePieza<Asteroide> {

    @Override
    public Asteroide piezaCreada() {

        return new Asteroide();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra un Asteroide", () -> {
            
            verify(PIEZA_MOVIL).chocoContraUnAsteroide();
        });
    }
}
