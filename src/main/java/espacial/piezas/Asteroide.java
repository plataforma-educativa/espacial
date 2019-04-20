package espacial.piezas;

import espacial.EspectroEspacial;

public class Asteroide implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.ASTEROIDE;
    }
}
