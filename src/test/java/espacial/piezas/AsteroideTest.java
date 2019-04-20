package espacial.piezas;

import espacial.EspectroEspacial;

public class AsteroideTest implements PruebaSobrePieza<Asteroide> {

    @Override
    public Asteroide piezaCreada() {

        return new Asteroide();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.ASTEROIDE;
    }
}
