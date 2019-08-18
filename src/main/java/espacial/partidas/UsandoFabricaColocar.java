package espacial.partidas;

import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

public abstract class UsandoFabricaColocar extends UsandoFabrica implements Tablero.EnCasilleros {

    public UsandoFabricaColocar(FabricaDePiezas unaFabrica) {

        super(unaFabrica);
    }

    protected abstract void colocar(final Proveedor<Pieza> proveedor);

    @Override
    public void colocarContenedorDeAntimateria() {

        colocar(fabrica::crearContenedorDeAntimateria);
    }

    @Override
    public void colocarContenedorDeMetal() {

        colocar(fabrica::crearContenedorDeMetal);
    }

    @Override
    public void colocarContenedorDeCristal() {

        colocar(fabrica::crearContenedorDeCristal);
    }

    @Override
    public void colocarAsteroide() {

        colocar(fabrica::crearAsteroide);
    }

    @Override
    public void colocarAgujeroNegro() {

        colocar(fabrica::crearAgujeroNegro);
    }

    @Override
    public void colocarBaseDesconocida() {

        colocar(fabrica::crearBaseDesconocida);
    }

    @Override
    public void colocarBaseRival() {

        colocar(fabrica::crearBaseRival);
    }
}
