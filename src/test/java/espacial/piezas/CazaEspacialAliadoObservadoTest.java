package espacial.piezas;

class CazaEspacialAliadoObservadoTest extends CazaEspacialObservadoTest {

    @Override
    protected CazaEspacial crear() {

        return new CazaEspacialAliado();
    }
}
