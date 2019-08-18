import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.excepciones.ErrorEspacial;
import espacial.partidas.PartidaEspacial;
import espacial.tableros.CambiarReferenciaAlSerAgregadaUnaBase;
import espacial.tableros.TableroEspacial;
import espacial.utiles.Referencia;

public class EscenarioEspacial extends PartidaEspacial {

    private Referencia<BaseEspacial> base = Referencia.conValorNulo();

    public EscenarioEspacial(int filas, int columnas) {

        super(new TableroEspacial(filas, columnas));
        base.siEsNuloAlObtener(this::lanzarExcepcionPorqueNoExisteUnaBase);
        tablero.registrar(new CambiarReferenciaAlSerAgregadaUnaBase(base));
    }

    private BaseEspacial lanzarExcepcionPorqueNoExisteUnaBase() {

        throw new ErrorEspacial("No existe ninguna BASE en el EscenarioEspacial");
    }

    @Override
    public String obtenerNombre() {

        return "Escenario Espacial";
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

        base.cambiar(tablero.enCasillero(fila, columna).colocarBase());
    }

    public void colocarAsteroideEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarAsteroide();
    }

    public void colocarContenedorDeAntimateriaEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarContenedorDeAntimateria();
    }

    public void colocarContenedorDeCristalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarContenedorDeCristal();
    }

    public void colocarContenedorDeMetalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarContenedorDeMetal();
    }

    public void colocarAgujeroNegroEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarAgujeroNegro();
    }

    public void colocarBaseDesconocidaEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarBaseDesconocida();
    }

    public void colocarBaseRivalEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarBaseRival();
    }

    public void colocarNaveEn(int fila, int columna) {

        tablero.enCasillero(fila, columna).colocarNave();
    }

    public void colocar(Espacial espacial, int fila, int columna) {

        espacial.colocarEn(tablero, fila, columna);
    }
}
