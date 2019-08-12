import espacial.partidas.EscenarioEspacialConBaseCentral;
import espacial.tableros.TableroDesierto;

public class DesiertoEspacial extends EscenarioEspacialConBaseCentral {

    public DesiertoEspacial() {

        super(new TableroDesierto());
    }

    @Override
    public String obtenerNombre() {

        return "Desierto Espacial";
    }
}
