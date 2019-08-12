package espacial.partidas;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Tablero;

public abstract class EscenarioEspacialConBaseCentral extends EscenarioEspacial {

    private BaseEspacial base;

    public EscenarioEspacialConBaseCentral(Tablero tablero) {

        super(tablero);
    }

    @Override
    public void inicializar() {

        base = obtenerTablero().colocarBaseEnCasillero(0, 0);

        super.inicializar();
    }

    @Override
    protected NaveEspacial crearNaveEspacialPara(Participante unParticipante) {

        NaveEspacial naveEspacial = super.crearNaveEspacialPara(unParticipante);

        base.amarrar(naveEspacial);

        return naveEspacial;
    }
}
