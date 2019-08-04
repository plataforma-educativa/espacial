package espacial.piezas.rasgos;

import espacial.Faccion;
import espacial.Pertenencia;

public interface Neutral extends Pertenencia {

    @Override
    default Faccion reconocer() {

        return Faccion.NEUTRAL;
    }
}
