package espacial.piezas.rasgos;

import espacial.Carga;
import espacial.Deposito;
import espacial.Pieza;
import espacial.SustanciaEspacial;

public interface PiezaDeposito extends Pieza, Deposito {

    @Override
    default int buscar(SustanciaEspacial unaSustancia) {

        return unaSustancia.buscarEn(this);
    }

    @Override
    default void recibir(Carga unaCarga) {

        unaCarga.subirEn(this);
    }

    @Override
    default void entregar(Carga unaCarga) {

        unaCarga.bajarDe(this);
    }

}
