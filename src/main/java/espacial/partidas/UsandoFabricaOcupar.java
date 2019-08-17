package espacial.partidas;

import espacial.Pieza;
import espacial.Tablero;
import espacial.utiles.Proveedor;

public abstract class UsandoFabricaOcupar extends UsandoFabrica implements Tablero.EnCasilleros {

    public UsandoFabricaOcupar(FabricaDePiezas unaFabrica) {

        super(unaFabrica);
    }

    protected abstract void ocuparCon(final Proveedor<Pieza> proveedor);

    public void ocuparConContenedorDeAntimateria() {

        ocuparCon(fabrica::crearContenedorDeAntimateria);
    }

    public void ocuparConContenedorDeMetal() {

        ocuparCon(fabrica::crearContenedorDeMetal);
    }

    public void ocuparConContenedorDeCristal() {

        ocuparCon(fabrica::crearContenedorDeCristal);
    }

    public void ocuparConAsteroide() {

        ocuparCon(fabrica::crearAsteroide);
    }

    public void ocuparConAgujeroNegro() {

        ocuparCon(fabrica::crearAgujeroNegro);
    }

    public void ocuparConBaseDesconocida() {

        ocuparCon(fabrica::crearBaseDesconocida);
    }
}
