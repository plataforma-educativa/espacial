package espacial.piezas;

import espacial.Ataque;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.piezas.rasgos.Neutral;

public class AgujeroNegro implements Pieza, Neutral {

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

        /* No se ve afectado por ningún ataque */
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsAgujeroNegro(this);
    }

    @Override
    public int obtenerPuntos() {

        return PUNTOS_MAXIMOS;
    }

    @Override
    public String toString() {

        return describir();
    }
}
