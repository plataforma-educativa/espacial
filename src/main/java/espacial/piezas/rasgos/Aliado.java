package espacial.piezas.rasgos;

import espacial.Faccion;
import espacial.Pertenencia;

public interface Aliado extends Pertenencia {

    default Faccion reconocer() {

        return Faccion.ALIADO;
    }
}
