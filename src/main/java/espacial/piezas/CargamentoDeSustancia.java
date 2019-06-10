package espacial.piezas;

import espacial.Cargamento;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.excepciones.ExcedeLaCargaDisponible;

public class CargamentoDeSustancia implements Cargamento {

    private final int capacidad;
    private int cantidad;

    public CargamentoDeSustancia(int capacidadTotal) {

        capacidad = capacidadTotal;
        cantidad = 0;
    }

    public void agregar(int cantidadAgregada) {

        int total = cantidad + cantidadAgregada;

        if (total > capacidad) {

            throw  new ExcedeElLugarDisponible(capacidad - cantidad, cantidadAgregada);
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
