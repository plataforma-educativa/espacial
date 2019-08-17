package espacial.partidas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.utiles.Proveedor;

import java.util.List;

public class UsandoFabricaColocarEnCasilleros extends UsandoFabricaColocar {

    private final List<Casillero> casilleros;

    public UsandoFabricaColocarEnCasilleros(FabricaDePiezas unaFabrica, List<Casillero> variosCasilleros) {

        super(unaFabrica);
        casilleros = variosCasilleros;
    }

    protected void colocar(final Proveedor<Pieza> proveedor) {

        casilleros.forEach(casillero -> casillero.ocuparCon(proveedor.obtener()));
    }
}
