package espacial.partidas;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

import java.util.Collections;
import java.util.List;

public class UsandoFabricaColocarEnCasillero extends UsandoFabricaColocar implements Tablero.EnCasillero {

    private final Casillero casillero;

    public UsandoFabricaColocarEnCasillero(FabricaDePiezas unaFabrica, Casillero unCasillero) {

        super(unaFabrica);
        casillero = unCasillero;
    }

    protected List<Pieza> colocar(final Proveedor<Pieza> proveedor) {

        return Collections.singletonList(ocuparCon(proveedor.obtener()));
    }

    private <T extends Pieza> T ocuparCon(final T pieza) {

        casillero.ocuparCon(pieza);

        return pieza;
    }

    @Override
    public BaseEspacial colocarBase() {

        return ocuparCon(fabrica.crearBase());
    }

    @Override
    public BaseEspacial colocarBaseRival() {

        return ocuparCon(fabrica.crearBaseRival());
    }

    @Override
    public NaveEspacial colocarNave() {

        return ocuparCon(fabrica.crearNave());
    }

    @Override
    public NaveEspacial colocarNaveRival() {

        return ocuparCon(fabrica.crearNaveRival());
    }
}
