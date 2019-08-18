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

        return amarrar(fabrica.crearNave());
    }

    @Override
    public NaveEspacial amarrarNaveRival() {

        return amarrar(fabrica.crearNaveRival());
    }

    private NaveEspacial amarrar(NaveEspacial nave) {

        base.amarrar(nave);

        return nave;
    }
}
