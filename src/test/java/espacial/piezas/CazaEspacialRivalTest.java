package espacial.piezas;

import espacial.test.Postcondicion;
import espacial.utiles.Nombre;

class CazaEspacialRivalTest extends CazaEspacialTest {

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

