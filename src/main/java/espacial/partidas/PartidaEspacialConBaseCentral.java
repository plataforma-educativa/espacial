package espacial.partidas;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Tablero;

public abstract class PartidaEspacialConBaseCentral extends PartidaEspacial {

    private BaseEspacial base;

    public PartidaEspacialConBaseCentral(Tablero tablero) {

        super(tablero);
    }

    @Override
    public void inicializar() {

        base = tablero.enCasillero(0, 0).colocarBase();

        super.inicializar();
    }

    @Override
    protected NaveEspacial crearNave() {

        return tablero.enBase(base).amarrarNave();
    }
}
