package espacial.piezas;

import espacial.Cargamento;
import espacial.utiles.Accion;

public class CargamentoObservado implements Cargamento {

    private final Cargamento implementacion;
    private final Accion alCambiar;

    public CargamentoObservado(Cargamento cargamento, Accion accionAlCambiar) {

        implementacion = cargamento;
        alCambiar = accionAlCambiar;
    }

    @Override
    public void agregar(int cantidadAgregada) {

        implementacion.agregar(cantidadAgregada);
        alCambiar.ejecutar();
    }

    @Override
    public void retirar(int cantidadRetirada) {

        implementacion.retirar(cantidadRetirada);
        alCambiar.ejecutar();
    }

    @Override
    public int contar() {

        return implementacion.contar();
    }
}
