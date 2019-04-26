package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;

public class Asteroide implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.ASTEROIDE;
    }
    
    @Override
    public void fueChocadaPor(PiezaMovil otraPieza) {

        otraPieza.chocoContraUnAsteroide();
    }
}
