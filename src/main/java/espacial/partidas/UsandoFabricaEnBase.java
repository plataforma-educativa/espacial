package espacial.partidas;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Tablero;

public class UsandoFabricaEnBase extends UsandoFabrica implements Tablero.EnBase {

    private final BaseEspacial base;

    public UsandoFabricaEnBase(FabricaDePiezas fabrica, BaseEspacial baseEspacial) {

        super(fabrica);
        base = baseEspacial;
    }

    @Override
    public NaveEspacial amarrarNave() {

        NaveEspacial nave = fabrica.crearNave();

        base.amarrar(nave);

        return nave;
    }
}
