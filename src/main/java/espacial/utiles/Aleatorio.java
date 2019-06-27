package espacial.utiles;

import java.util.Random;

public abstract class Aleatorio<T> {

    protected final Random implementacion = new Random();

    public abstract T obtener();

    public static Aleatorio<Integer> enRango(int desde, int hasta) {

        return new AleatorioEnRango(desde, hasta);
    }
}
