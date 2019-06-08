package espacial;

import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.excepciones.ExcedeLaCargaDisponible;

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

        int total = cantidad - cantidadRetirada;

        if (total < 0) {

            throw new ExcedeLaCargaDisponible(cantidad, cantidadRetirada);
        }

        cantidad = total;
    }

    public int contar() {

        return cantidad;
    }
}
