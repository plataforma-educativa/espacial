package espacial.piezas;

import espacial.excepciones.Defecto;

public class Dureza {

    public static final int MINIMA = 5;
    public static final int MAXIMA = 100;

    private int valor;

    Dureza(int conValor) {

        validar(conValor);

        valor = conValor;
    }

    private void validar(int conValor) {

        if (conValor < MINIMA) {

            throw new Defecto("La dureza de un Asteroide no puede ser menor a " + MINIMA);
        }

        if (conValor > MAXIMA) {

            throw new Defecto("La dureza de un Asteroide no puede ser mayor a " + MAXIMA);
        }
    }

    public int obtener() {

        return valor;
    }
}
