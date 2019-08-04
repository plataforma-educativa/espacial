package espacial.piezas.rasgos;

import espacial.Faccion;
import espacial.Pertenencia;

public interface Rival extends Pertenencia {

    default Faccion reconocer() {

        return Faccion.RIVAL;
    }
}
