package espacial.piezas;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;

public class BaseEspacialTest extends PruebaSobrePieza<BaseEspacial> {

    @Override
    public BaseEspacial piezaCreada() {

        return new BaseEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Test
    @Disabled
    public void fueChocadaPor() {

    }
    
    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra una Base", () -> {
            
        });
    }
    
}
