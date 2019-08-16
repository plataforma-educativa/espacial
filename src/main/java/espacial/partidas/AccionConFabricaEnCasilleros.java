package espacial.partidas;

import espacial.Casillero;
import espacial.Pieza;
import espacial.utiles.Proveedor;

import java.util.List;

public class AccionConFabricaEnCasilleros extends AccionConFabrica {

    private final List<Casillero> casilleros;

    public AccionConFabricaEnCasilleros(FabricaDePiezas unaFabrica, List<Casillero> variosCasilleros) {

        super(unaFabrica);
        casilleros = variosCasilleros;
    }

    protected void ocuparCon(final Proveedor<Pieza> proveedor) {

        casilleros.forEach(casillero -> casillero.ocuparCon(proveedor.obtener()));
    }
}
