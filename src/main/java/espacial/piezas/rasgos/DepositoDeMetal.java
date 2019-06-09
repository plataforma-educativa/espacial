package espacial.piezas.rasgos;

import espacial.Cargamento;
import espacial.Deposito;

public interface DepositoDeMetal extends Deposito {

    Cargamento obtenerMetal();

    @Override
    default void cargarMetal(int cantidad) {

        obtenerMetal().agregar(cantidad);
    }

    @Override
    default void descargarMetal(int cantidad) {

        obtenerMetal().retirar(cantidad);
    }

    @Override
    default int contarMetal() {

        return obtenerMetal().contar();
    }
}
