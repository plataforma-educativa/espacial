package espacial.utiles;

import java.util.Arrays;
import java.util.List;

public class AleatorioEnLista<T> extends Aleatorio<T> {

    private final List<T> valores;

    @SafeVarargs
    public AleatorioEnLista(T... valoresPosibles) {

        valores = Arrays.asList(valoresPosibles);
    }

    @Override
    public T obtener() {

        return valores.get(implementacion.nextInt(valores.size()));
    }
}
