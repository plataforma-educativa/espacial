package espacial.piezas;

import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

class CazaEspacialObservadoTest implements TestDeContrato {

    @Nested
    class CazaEspacialAliadoObservadoTest extends TestDeCazaEspacialObservado {

        @Override
        protected CazaEspacial crear() {

            return new CazaEspacialAliado();
        }
    }

    @Nested
    class CazaEspacialRivalObservadoTest extends TestDeCazaEspacialObservado {

        @Override
        protected CazaEspacial crear() {

            return new CazaEspacialRival();
        }
    }
}
