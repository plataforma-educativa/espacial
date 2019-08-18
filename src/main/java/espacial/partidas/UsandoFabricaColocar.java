package espacial.partidas;

import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

import java.util.List;

public abstract class UsandoFabricaColocar extends UsandoFabrica implements Tablero.EnCasilleros {

    public UsandoFabricaColocar(FabricaDePiezas unaFabrica) {

        super(unaFabrica);
    }

    protected abstract List<Pieza> colocar(final Proveedor<Pieza> proveedor);

    @Override
    public List<Pieza> colocarContenedorDeAntimateria() {

        return colocar(fabrica::crearContenedorDeAntimateria);
    }

    @Override
    public List<Pieza> colocarContenedorDeMetal() {

        return colocar(fabrica::crearContenedorDeMetal);
    }

    @Override
    public List<Pieza> colocarContenedorDeCristal() {

        return colocar(fabrica::crearContenedorDeCristal);
    }

    @Override
    public List<Pieza> colocarAsteroide() {

        return colocar(fabrica::crearAsteroide);
    }

    @Override
    public List<Pieza> colocarAgujeroNegro() {

        return colocar(fabrica::crearAgujeroNegro);
    }

    @Override
    public List<Pieza> colocarBaseDesconocida() {

        return colocar(fabrica::crearBaseDesconocida);
    }
}
