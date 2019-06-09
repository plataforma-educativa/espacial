package espacial.piezas.rasgos;

import espacial.Cargamento;
import espacial.Deposito;

public interface DepositoDeAntimateria extends Deposito {

    Cargamento obtenerAntimateria();

    @Override
    default void cargarAntimateria(int cantidad) {

        obtenerAntimateria().agregar(cantidad);
    }

    @Override
    default void descargarAntimateria(int cantidad) {

        obtenerAntimateria().retirar(cantidad);
    }

    @Override
    default int contarAntimateria() {

        return obtenerAntimateria().contar();
    }
}
