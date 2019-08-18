package espacial.piezas;

class CazaEspacialAliadoEnPartidaTest extends CazaEspacialEnPartidaTest {

    @Override
    protected CazaEspacial crear() {

        return new CazaEspacialAliado();
    }
}
