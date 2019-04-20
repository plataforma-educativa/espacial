package espacial.piezas;

import espacial.EspectroEspacial;

public class CazaEspacial implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.NAVE;
    }

}
