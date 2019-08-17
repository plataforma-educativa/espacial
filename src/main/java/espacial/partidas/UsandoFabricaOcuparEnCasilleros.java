package espacial.partidas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.utiles.Proveedor;

import java.util.List;

public class UsandoFabricaOcuparEnCasilleros extends UsandoFabricaOcupar {

    private final List<Casillero> casilleros;

    public UsandoFabricaOcuparEnCasilleros(FabricaDePiezas unaFabrica, List<Casillero> variosCasilleros) {

        super(unaFabrica);
        casilleros = variosCasilleros;
    }

    protected void ocuparCon(final Proveedor<Pieza> proveedor) {

        casilleros.forEach(casillero -> casillero.ocuparCon(proveedor.obtener()));
    }
}
