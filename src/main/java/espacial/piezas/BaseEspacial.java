package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;

public class BaseEspacial implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }
}
