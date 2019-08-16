package espacial.partidas;

import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

public abstract class AccionConFabrica implements Tablero.Accion {

    protected final FabricaDePiezas fabrica;

    public AccionConFabrica(FabricaDePiezas unaFabrica) {

        fabrica = unaFabrica;
    }

    protected abstract void ocuparCon(final Proveedor<Pieza> proveedor);

    public void crearContenedorDeAntimateria() {

        ocuparCon(fabrica::crearContenedorDeAntimateria);
    }

    public void crearContenedorDeMetal() {

        ocuparCon(fabrica::crearContenedorDeMetal);
    }

    public void crearContenedorDeCristal() {

        ocuparCon(fabrica::crearContenedorDeCristal);
    }

    public void crearAsteroide() {

        ocuparCon(fabrica::crearAsteroide);
    }

    public void crearAgujeroNegro() {

        ocuparCon(fabrica::crearAgujeroNegro);
    }

    public void crearBaseDesconocida() {

        ocuparCon(fabrica::crearBaseDesconocida);
    }
}
