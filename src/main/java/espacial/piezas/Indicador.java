package espacial.piezas;

import espacial.utiles.Accion;

public class Indicador {

    private Accion alAgotar = Accion.NINGUNA;
    private Accion alCambiar = Accion.NINGUNA;

    private final int inicial;
    private int valor;

    public Indicador(int valorInicial) {

        inicial = valorInicial;
        valor = valorInicial;
    }

    public int obtenerValor() {

        return valor;
    }

    public void decrementarEn(int decremento) {

        cambiarA((valor > decremento) ? (valor - decremento) : 0);
        siSeAgoto();
    }

    private void cambiarA(int nuevoValor) {

        if (nuevoValor != valor) {

            valor = nuevoValor;
            alCambiar.ejecutar();
        }
    }

    public void cuandoSeAgota(Accion accion) {

        alAgotar = accion;
    }

    public void cuandoCambia(Accion accion) {

        alCambiar = accion;
    }

    private void siSeAgoto() {

        if (valor == 0) {

            alAgotar.ejecutar();
            alAgotar = Accion.NINGUNA;
        }
    }

    public int obtenerNivel() {

        return valor * 100 / inicial;
    }
}
