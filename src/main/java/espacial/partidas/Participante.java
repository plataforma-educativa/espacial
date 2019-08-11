package espacial.partidas;

import espacial.NaveEspacial;

public abstract class Participante {

    private final EscenarioEspacial escenario;

    public Participante() {

        escenario = EscenarioEspacial.obtener();
    }

    protected NaveEspacial crearNaveEspacial() {

        return escenario.crearNaveEspacialPara(this);
    }
}
