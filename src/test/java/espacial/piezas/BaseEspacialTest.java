package espacial.piezas;

import espacial.EspectroEspacial;

public class BaseEspacialTest implements PruebaSobrePieza<BaseEspacial> {

    @Override
    public BaseEspacial piezaCreada() {

        return new BaseEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }
}
