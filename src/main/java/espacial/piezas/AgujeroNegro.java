package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;

public class AgujeroNegro implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public void fueChocadaPor(PiezaMovil piezaMovil) {
        
        piezaMovil.chocoContraUnAgujeroNegro();
    }
}
