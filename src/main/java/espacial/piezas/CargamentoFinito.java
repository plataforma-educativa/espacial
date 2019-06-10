package espacial.piezas;

import espacial.Cargamento;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.excepciones.ExcedeLaCargaDisponible;

public abstract class CargamentoFinito implements Cargamento {

    private int cantidad = 0;

    protected abstract int contarLugar();

    @Override
    public void agregar(int cantidadAgregada) {

        if (cantidadAgregada > contarLugar()) {

            throw new ExcedeElLugarDisponible(contarLugar(), cantidadAgregada);
        }

        cantidad += cantidadAgregada;
    }

    @Override
    public void retirar(int cantidadRetirada) {

        if (cantidadRetirada > cantidad) {

            throw new ExcedeLaCargaDisponible(cantidad, cantidadRetirada);
        }

        cantidad -= cantidadRetirada;
    }

    @Override
    public int contar() {

        return cantidad;
    }
}
