import espacial.BaseEspacial;
import espacial.partidas.EscenarioEspacial;
import espacial.tableros.TableroBatallaEspacial;

public class BatallaEspacial extends EscenarioEspacial {

    private final BaseEspacial base;
    
    public BatallaEspacial() {

        super(new TableroBatallaEspacial());
        base = obtenerTablero().colocarBaseEnCasillero(0, 0);
        cuandoCrearNaveEspacial(base::amarrar);
    }

    @Override
    public String obtenerNombre() {

        return "Batalla Espacial";
    }
}
