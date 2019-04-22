package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;

public class Asteroide implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.ASTEROIDE;
    }
}
