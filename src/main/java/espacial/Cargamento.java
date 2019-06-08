package espacial;

import espacial.excepciones.ExcedeLaCapacidadDeCarga;

public class Cargamento {

    private final int capacidad;
    private int cantidad;

    public Cargamento(int capacidadTotal) {

        capacidad = capacidadTotal;
        cantidad = 0;
    }

    public void agregar(int cantidadAgregada) {

        int total = cantidad + cantidadAgregada;

        if (total > capacidad) {

            throw  new ExcedeLaCapacidadDeCarga(capacidad, total);
        }

        cantidad = total;
    }

    public void retirar(int cantidadRetirada) {


    }

    public int contar() {

        return cantidad;
    }
}
