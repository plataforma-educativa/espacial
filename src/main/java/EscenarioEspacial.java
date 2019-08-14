import espacial.partidas.PartidaEspacial;
import espacial.tableros.TableroProgramable;

public class EscenarioEspacial extends PartidaEspacial {

    public EscenarioEspacial(int filas, int columnas) {

        super(new TableroProgramable(filas, columnas));
    }

    @Override
    public String obtenerNombre() {

        return null;
    }

    public int obtenerFilaMinima() {

        return obtenerTablero().obtenerFilaMinima();
    }

    public int obtenerFilaMaxima() {

        return obtenerTablero().obtenerFilaMaxima();
    }

    public int obtenerColumnaMinima() {

        return obtenerTablero().obtenerColumnaMinima();
    }

    public int obtenerColumnaMaxima() {

        return obtenerTablero().obtenerColumnaMaxima();
    }
}
