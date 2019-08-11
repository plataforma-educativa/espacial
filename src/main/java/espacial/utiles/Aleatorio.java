package espacial.utiles;

import java.util.List;
import java.util.Random;

public abstract class Aleatorio<T> {

    protected final Random implementacion = new Random();

    public abstract T obtener();

    public static Aleatorio<Integer> enRango(int desde, int hasta) {

        return new AleatorioEnRango(desde, hasta);
    }

    @SafeVarargs
    public static <T> Aleatorio<T> enLista(T... valores) {

        return new AleatorioEnLista<>(valores);
    }

    public static <T> Aleatorio<T> consumiendo(List<T> disponibles) {

        return new AleatorioConsumiendo<>(disponibles);
    }
}
