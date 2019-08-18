package espacial.piezas;

import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import espacial.utiles.Nombre;
import org.junit.jupiter.api.Nested;

class CazaEspacialTest implements TestDeContrato {

    @Nested
    class CazaEspacialAliadoTest extends TestDeContratoSobreCazaEspacial {

        @Override
        CazaEspacial piezaCreada() {

            return new CazaEspacialAliado();
        }

        @Override
        CazaEspacial piezaCreada(Nombre nombre) {

            return new CazaEspacialAliado(nombre);
        }

        @Override
        Postcondicion evaluoLaCondicionDePartidarioEsperada() {

            return evaluoQueEsAliado();
        }
    }

    @Nested
    class CazaEspacialRivalTest extends TestDeContratoSobreCazaEspacial {

        @Override
        CazaEspacial piezaCreada() {

            return new CazaEspacialRival();
        }

        @Override
        CazaEspacial piezaCreada(Nombre nombre) {

            return new CazaEspacialRival(nombre);
        }

        @Override
        Postcondicion evaluoLaCondicionDePartidarioEsperada() {

            return evaluoQueEsRival();
        }
    }
}
