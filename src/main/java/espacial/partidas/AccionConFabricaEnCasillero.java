package espacial.partidas;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

public class AccionConFabricaEnCasillero extends AccionConFabrica implements Tablero.AccionSingular {

    private final Casillero casillero;

    public AccionConFabricaEnCasillero(FabricaDePiezas unaFabrica, Casillero unCasillero) {

        super(unaFabrica);
        casillero = unCasillero;
    }

    protected void ocuparCon(final Proveedor<Pieza> proveedor) {

        ocuparCon(proveedor.obtener());
    }

    private <T extends Pieza> T ocuparCon(final T pieza) {

        casillero.ocuparCon(pieza);

        return pieza;
    }

    @Override
    public BaseEspacial crearBase() {

        return ocuparCon(fabrica.crearBase());
    }

    @Override
    public NaveEspacial crearNave() {

        return ocuparCon(fabrica.crearNave());
    }
}
