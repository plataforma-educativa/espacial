import espacial.partidas.PartidaEspacialConBaseCentral;
import espacial.tableros.TableroBatallaEspacial;

public class BatallaEspacial extends PartidaEspacialConBaseCentral {

    public BatallaEspacial() {

        super(new TableroBatallaEspacial());
    }

    @Override
    public String obtenerNombre() {

        return "Batalla Espacial";
    }
}
