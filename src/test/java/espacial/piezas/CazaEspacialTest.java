package espacial.piezas;

import espacial.EspectroEspacial;

public class CazaEspacialTest implements PruebaSobrePieza<CazaEspacial> {

    @Override
    public CazaEspacial piezaCreada() {

        return new CazaEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.NAVE;
    }
}
