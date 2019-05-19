package espacial.piezas;

import espacial.*;

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
    public void fueAtacadoCon(Ataque ataque) {

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
