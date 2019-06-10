package espacial.piezas;

import espacial.Cargamento;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.excepciones.ExcedeLaCargaDisponible;

public abstract class CargamentoFinito implements Cargamento {

    private int cantidad = 0;

    protected abstract int contarLugar();

    private void verificarQueSePuedaAgregar(int cantidadAgregada) {

        if (cantidadAgregada > contarLugar()) {

            throw new ExcedeElLugarDisponible(contarLugar(), cantidadAgregada);
        }
    }

    private void verificarQueSePuedaRetirar(int cantidadRetirada) {

        if (cantidadRetirada > contar()) {

            throw new ExcedeLaCargaDisponible(contar(), cantidadRetirada);
        }
    }

    @Override
    public void agregar(int cantidadAgregada) {

        verificarQueSePuedaAgregar(cantidadAgregada);

        cantidad += cantidadAgregada;
    }

    @Override
    public void retirar(int cantidadRetirada) {

        verificarQueSePuedaRetirar(cantidadRetirada);

        cantidad -= cantidadRetirada;
    }

    @Override
    public int contar() {

        return cantidad;
    }
}
