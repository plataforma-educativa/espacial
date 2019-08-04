package espacial.piezas.rasgos;

import espacial.Faccion;
import espacial.Participante;

public interface Neutral extends Participante {

    @Override
    default Faccion reconocer() {

        return Faccion.NEUTRAL;
    }
}
