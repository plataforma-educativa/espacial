package espacial.piezas;

import espacial.Accion;

public class Indicador {

    private Accion alAgotar = Accion.NINGUNA;

    private int valor;

    public Indicador(int valorInicial) {

        valor = valorInicial;
    }

    public int obtenerValor() {

        return valor;
    }

    public void decrementarEn(int decremento) {

        valor = (valor > decremento) ? (valor - decremento) : 0;

        siSeAgoto();
    }

    public void cuandoSeAgota(Accion accion) {

        alAgotar = accion;
    }

    private void siSeAgoto() {

        if (valor == 0) {

            alAgotar.ejecutar();
            alAgotar = Accion.NINGUNA;
        }
    }
}
