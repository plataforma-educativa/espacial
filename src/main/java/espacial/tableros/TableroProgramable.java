package espacial.tableros;

public class TableroProgramable extends TableroEspacial {

    public TableroProgramable(int filas, int columnas) {

        super(new Dimensiones(filas, columnas));
    }

    public TableroProgramable(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        super(new Dimensiones(filaDesde, filaHasta, columnaDesde, columnaHasta));
    }

    @Override
    protected void inicializarPiezas() {

    }
}
