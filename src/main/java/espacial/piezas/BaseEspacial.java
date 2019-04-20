package espacial.piezas;

import espacial.EspectroEspacial;

public class BaseEspacial implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }
}
