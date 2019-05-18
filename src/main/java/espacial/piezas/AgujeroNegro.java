package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;
import espacial.Visitante;

public class AgujeroNegro implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public void fueChocadaPor(PiezaMovil piezaMovil) {
        
        piezaMovil.chocoContraUnAgujeroNegro();
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsAgujeroNegro(this);
    }

    @Override
    public int obtenerPuntos() {

        return PUNTOS_MAXIMOS;
    }
}
