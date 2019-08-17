package espacial.partidas;

import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

public abstract class UsandoFabricaColocar extends UsandoFabrica implements Tablero.EnCasilleros {

    public UsandoFabricaColocar(FabricaDePiezas unaFabrica) {

        super(unaFabrica);
    }

    protected abstract void colocar(final Proveedor<Pieza> proveedor);

    public void colocarContenedorDeAntimateria() {

        colocar(fabrica::crearContenedorDeAntimateria);
    }

    public void colocarContenedorDeMetal() {

        colocar(fabrica::crearContenedorDeMetal);
    }

    public void colocarContenedorDeCristal() {

        colocar(fabrica::crearContenedorDeCristal);
    }

    public void colocarAsteroide() {

        colocar(fabrica::crearAsteroide);
    }

    public void colocarAgujeroNegro() {

        colocar(fabrica::crearAgujeroNegro);
    }

    public void colocarBaseDesconocida() {

        colocar(fabrica::crearBaseDesconocida);
    }
}
