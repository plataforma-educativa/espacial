package espacial.piezas.rasgos;

import espacial.Cargamento;
import espacial.Deposito;

public interface DepositoDeAntimateria extends Deposito {

    Cargamento obtenerAntimateria();

    default void cargarAntimateria(int cantidad) {

        obtenerAntimateria().agregar(cantidad);
    }

    default void descargarAntimateria(int cantidad) {

        obtenerAntimateria().retirar(cantidad);
    }

    default int contarAntimateria() {

        return obtenerAntimateria().contar();
    }
}
