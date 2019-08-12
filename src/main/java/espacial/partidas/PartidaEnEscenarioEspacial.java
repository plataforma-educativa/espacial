package espacial.partidas;

import espacial.PartidaEspacial;
import espacial.Tablero;

public abstract class PartidaEnEscenarioEspacial implements PartidaEspacial {

    protected final EscenarioEspacial escenario;

    public PartidaEnEscenarioEspacial(EscenarioEspacial escenarioEspacial) {

        escenario = escenarioEspacial;
    }

    @Override
    public String obtenerNombre() {

        return escenario.obtenerNombre();
    }

    @Override
    public Tablero obtenerTablero() {

        return escenario.obtenerTablero();
    }
}
