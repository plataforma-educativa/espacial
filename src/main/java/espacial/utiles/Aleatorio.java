package espacial.utiles;

import java.util.Random;

public class Aleatorio {

    private final int valorInicial;
    private final int valorFinal;
    private final Random implementacion = new Random();

    public Aleatorio(int desde, int hasta) {

        valorInicial = desde;
        valorFinal = hasta;
    }

    public int obtener() {

        return implementacion.nextInt(valorFinal - valorInicial + 1) + valorInicial;
    }
}
