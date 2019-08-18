package espacial.piezas;

import espacial.test.Postcondicion;
import espacial.utiles.Nombre;

class CazaEspacialAliadoTest extends CazaEspacialTest {

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

