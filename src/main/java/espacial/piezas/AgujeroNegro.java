package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;

public class AgujeroNegro implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.DESCONOCIDO;
    }

}
