package espacial.piezas;

import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

class CazaEspacialEnPartidaTest implements TestDeContrato {

    @Nested
    class CazaEspacialRivalEnPartidaTest extends TestDeCazaEspacialEnPartida {

        @Override
        protected CazaEspacial crear() {

            return new CazaEspacialRival();
        }
    }

    @Nested
    class CazaEspacialAliadoEnPartidaTest extends TestDeCazaEspacialEnPartida {

        @Override
        protected CazaEspacial crear() {

            return new CazaEspacialAliado();
        }
    }
}
