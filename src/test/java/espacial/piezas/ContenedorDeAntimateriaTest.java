package espacial.piezas;

import static org.mockito.Mockito.*;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;

public class ContenedorDeAntimateriaTest extends PruebaSobrePieza<ContenedorDeAntimateria> {

    @Override
    public ContenedorDeAntimateria piezaCreada() {

        return new ContenedorDeAntimateria();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.CONTENEDOR;
    }

    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra un Contenedor", () -> {
            
            verify(PIEZA_MOVIL).chocoContraUnContenedor();
        });
    }
}
