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

    public void colocarContenedorDeAntimateriaEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConContenedorDeAntimateria();
    }

    public void colocarContenedorDeCristalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConContenedorDeCristal();
    }

    public void colocarContenedorDeMetalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConContenedorDeMetal();
    }

    public void colocarAgujeroNegroEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConAgujeroNegro();
    }

    public void colocarBaseDesconocidaEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConBaseDesconocida();
    }

    public void colocarNaveEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).ocuparConNave();
    }

    public void colocar(Espacial espacial, int fila, int columna) {

        espacial.colocarEn(tablero, fila, columna);
    }
}
