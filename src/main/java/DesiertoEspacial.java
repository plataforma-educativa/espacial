import espacial.BaseEspacial;
import espacial.partidas.EscenarioEspacial;
import espacial.tableros.TableroDesierto;

public class DesiertoEspacial extends EscenarioEspacial {

    private final BaseEspacial base;

    public DesiertoEspacial() {

        super(new TableroDesierto());
        base = obtenerTablero().colocarBaseEnCasillero(0, 0);
        cuandoCrearNaveEspacial(base::amarrar);
    }

    @Override
    public String obtenerNombre() {

        return "Desierto Espacial";
    }
}
