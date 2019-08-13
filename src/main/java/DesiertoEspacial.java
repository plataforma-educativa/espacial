import espacial.partidas.PartidaEspacialConBaseCentral;
import espacial.tableros.TableroDesierto;

public class DesiertoEspacial extends PartidaEspacialConBaseCentral {

    public DesiertoEspacial() {

        super(new TableroDesierto());
    }

    @Override
    public String obtenerNombre() {

        return "Desierto Espacial";
    }
}
