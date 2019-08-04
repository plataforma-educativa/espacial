package espacial.piezas.rasgos;

import espacial.Faccion;
import espacial.Participante;

public interface Aliado extends Participante {

    default Faccion reconocer() {

        return Faccion.ALIADO;
    }
}
