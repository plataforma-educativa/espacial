import espacial.partidas.EscenarioEspacialConBaseCentral;
import espacial.tableros.TableroBatallaEspacial;

public class BatallaEspacial extends EscenarioEspacialConBaseCentral {

    public BatallaEspacial() {

        super(new TableroBatallaEspacial());
    }

    @Override
    public String obtenerNombre() {

        return "Batalla Espacial";
    }
}
