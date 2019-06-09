package espacial.piezas;

import espacial.SustanciaEspacial;

public class ContenedorDeCristalTest extends ContenedorDeSustanciaTest<ContenedorDeCristal> {

    @Override
    protected SustanciaEspacial sustanciaAlmacenada() {

        return SustanciaEspacial.CRISTAL;
    }

    @Override
    protected ContenedorDeCristal piezaCreada() {

        return new ContenedorDeCristal();
    }
}
