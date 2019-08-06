package espacial.piezas.rasgos;

import espacial.Partidario;

public interface Neutral extends Partidario {

    @Override
    default void evaluar(Condicional condicional) {

        condicional.siEsNeutral();
    }
}
