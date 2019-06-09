package espacial.piezas;

import espacial.SustanciaEspacial;

public class ContenedorDeMetalTest extends ContenedorDeSustanciaTest<ContenedorDeMetal> {

    @Override
    protected SustanciaEspacial sustanciaAlmacenada() {

        return SustanciaEspacial.METAL;
    }

    @Override
    protected ContenedorDeMetal piezaCreada() {

        return new ContenedorDeMetal();
    }
}
