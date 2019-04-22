package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;

public class ContenedorDeAntimateria implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.CONTENEDOR;
    }
}
