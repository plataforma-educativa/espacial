package espacial.piezas.rasgos;

import espacial.Partidario;

public interface Aliado extends Partidario {

    @Override
    default void evaluar(Condicional condicional) {

        condicional.siEsAliado();
    }
}
