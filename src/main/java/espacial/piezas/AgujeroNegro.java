package espacial.piezas;

import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Visitante;

public class AgujeroNegro implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {
        
        chocable.chocoContraUnAgujeroNegro();
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
