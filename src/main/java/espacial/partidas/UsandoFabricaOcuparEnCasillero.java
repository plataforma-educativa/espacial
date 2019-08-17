package espacial.partidas;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

public class UsandoFabricaOcuparEnCasillero extends UsandoFabricaOcupar implements Tablero.EnCasillero {

    private final Casillero casillero;

    public UsandoFabricaOcuparEnCasillero(FabricaDePiezas unaFabrica, Casillero unCasillero) {

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
    public BaseEspacial ocuparConBase() {

        return ocuparCon(fabrica.crearBase());
    }

    @Override
    public NaveEspacial ocuparConNave() {

        return ocuparCon(fabrica.crearNave());
    }
}
