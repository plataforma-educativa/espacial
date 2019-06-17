package espacial.piezas;

import espacial.SustanciaEspacial;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

class ContenedorDeSustanciaTest implements TestDeContrato {

    @Nested
    class AntimateriaTest extends TestDeContratoSobreContenedor<ContenedorDeAntimateria> {

        @Override
        protected SustanciaEspacial sustanciaAlmacenada() {

            return SustanciaEspacial.ANTIMATERIA;
        }

        @Override
        protected ContenedorDeAntimateria piezaCreada() {

            return new ContenedorDeAntimateria();
        }

        @Override
        protected ContenedorDeAntimateria piezaCreadaConCarga(int cantidadInicial) {

            return new ContenedorDeAntimateria(cantidadInicial);
        }
    }

    @Nested
    class MetalTest extends TestDeContratoSobreContenedor<ContenedorDeMetal> {

        @Override
        protected SustanciaEspacial sustanciaAlmacenada() {

            return SustanciaEspacial.METAL;
        }

        @Override
        protected ContenedorDeMetal piezaCreada() {

            return new ContenedorDeMetal();
        }

        @Override
        protected ContenedorDeMetal piezaCreadaConCarga(int cantidadInicial) {

            return new ContenedorDeMetal(cantidadInicial);
        }
    }

    @Nested
    class CristalTest extends TestDeContratoSobreContenedor<ContenedorDeCristal> {

        @Override
        protected SustanciaEspacial sustanciaAlmacenada() {

            return SustanciaEspacial.CRISTAL;
        }

        @Override
        protected ContenedorDeCristal piezaCreada() {

            return new ContenedorDeCristal();
        }

        @Override
        protected ContenedorDeCristal piezaCreadaConCarga(int cantidadInicial) {

            return new ContenedorDeCristal(cantidadInicial);
        }
    }
}
