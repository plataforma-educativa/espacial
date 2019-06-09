package espacial.piezas.rasgos;

import espacial.Cargamento;
import espacial.Deposito;

public interface DepositoDeCristal extends Deposito {

    Cargamento obtenerCristal();

    @Override
    default void cargarCristal(int cantidad) {

        obtenerCristal().agregar(cantidad);
    }

    @Override
    default void descargarCristal(int cantidad) {

        obtenerCristal().retirar(cantidad);
    }

    @Override
    default int contarCristal() {

        return obtenerCristal().contar();
    }
}
