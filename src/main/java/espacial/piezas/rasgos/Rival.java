package espacial.piezas.rasgos;

import espacial.Partidario;

public interface Rival extends Partidario {

    @Override
    default void evaluar(Condicional condicional) {

        condicional.siEsRival();
    }
}
