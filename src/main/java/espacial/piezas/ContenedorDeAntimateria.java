package espacial.piezas;

import espacial.EspectroEspacial;

public class ContenedorDeAntimateria implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.CONTENEDOR;
    }
}
