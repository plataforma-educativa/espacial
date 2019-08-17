import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.excepciones.ErrorEspacial;
import espacial.partidas.PartidaEspacial;
import espacial.tableros.TableroProgramable;
import espacial.utiles.Referencia;

public class EscenarioEspacial extends PartidaEspacial {

    private Referencia<BaseEspacial> base = Referencia.conValorNulo();

    public EscenarioEspacial(int filas, int columnas) {

        super(new TableroProgramable(filas, columnas));
        base.siEsNuloAlObtener(this::lanzarExcepcionPorqueNoExisteUnaBase);
    }

    private BaseEspacial lanzarExcepcionPorqueNoExisteUnaBase() {

        throw new ErrorEspacial("No existe ninguna BASE en el EscenarioEspacial");
    }

    @Override
    public String obtenerNombre() {

        return null;
    }

    @Override
    protected NaveEspacial crearNave() {

        return tablero.enBase(base.obtener()).amarrarNave();
    }

    public int obtenerFilaMinima() {

        return tablero.obtenerFilaMinima();
    }

    public int obtenerFilaMaxima() {

        return tablero.obtenerFilaMaxima();
    }

    public int obtenerColumnaMinima() {

        return tablero.obtenerColumnaMinima();
    }

    public int obtenerColumnaMaxima() {

        return tablero.obtenerColumnaMaxima();
    }

    public void colocarBaseEn(int fila, int columna) {

        base.cambiar(tablero.enCasillero(fila, columna).ocuparConBase());
    }

    public void colocarAsteroideEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConAsteroide();
    }
}
