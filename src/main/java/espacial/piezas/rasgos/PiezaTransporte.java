package espacial.piezas.rasgos;

import espacial.Carga;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Transporte;

public interface PiezaTransporte extends Pieza, Transporte {

    @Override
    default int buscar(SustanciaEspacial unaSustancia) {

        return unaSustancia.buscar(this);
    }

    @Override
    default void recibir(Carga unaCarga) {

        unaCarga.subirEn(this);
    }
}
