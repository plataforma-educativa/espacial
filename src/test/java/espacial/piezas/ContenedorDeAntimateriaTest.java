package espacial.piezas;

import espacial.SustanciaEspacial;

public class ContenedorDeAntimateriaTest extends ContenedorDeSustanciaTest<ContenedorDeAntimateria> {

    @Override
    protected SustanciaEspacial sustanciaAlmacenada() {

        return SustanciaEspacial.ANTIMATERIA;
    }

    @Override
    protected ContenedorDeAntimateria piezaCreada() {

        return new ContenedorDeAntimateria();
    }
}
