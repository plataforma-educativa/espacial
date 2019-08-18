package espacial.partidas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.utiles.Proveedor;

import java.util.LinkedList;
import java.util.List;

public class UsandoFabricaColocarEnCasilleros extends UsandoFabricaColocar {

    private final List<Casillero> casilleros;
    private final List<Pieza> piezasCreadas = new LinkedList<>();

    public UsandoFabricaColocarEnCasilleros(FabricaDePiezas unaFabrica, List<Casillero> variosCasilleros) {

        super(unaFabrica);
        casilleros = variosCasilleros;
    }

    @Override
    protected List<Pieza> colocar(final Proveedor<Pieza> proveedor) {

        casilleros.forEach(casillero -> casillero.ocuparCon(nuevaPiezaCreadaCon(proveedor)));

        return piezasCreadas;
    }

    private Pieza nuevaPiezaCreadaCon(Proveedor<Pieza> proveedor) {

        Pieza pieza = proveedor.obtener();
        piezasCreadas.add(pieza);
        return pieza;
    }
}
