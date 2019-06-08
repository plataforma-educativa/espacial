package espacial.piezas.rasgos;

import espacial.Carga;
import espacial.Deposito;
import espacial.Pieza;
import espacial.SustanciaEspacial;

public interface PiezaDeposito extends Pieza, Deposito {

    @Override
    default int buscar(SustanciaEspacial unaSustancia) {

        return unaSustancia.buscar(this);
    }

    @Override
    default void recibir(Carga unaCarga) {

        unaCarga.subirEn(this);
    }
}
