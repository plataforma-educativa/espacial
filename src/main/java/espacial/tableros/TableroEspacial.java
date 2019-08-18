package espacial.tableros;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Pieza;
import espacial.partidas.FabricaDePiezas;
import espacial.partidas.UsandoFabricaColocarEnCasillero;
import espacial.partidas.UsandoFabricaColocarEnCasilleros;
import espacial.partidas.UsandoFabricaEnBase;

import java.util.LinkedList;
import java.util.List;

public class TableroEspacial implements TableroContenedor {

    protected final FabricaDePiezas fabrica = FabricaDePiezas.crear();
    private final Observadores observadores = new Observadores();
    private final Dimensiones dimensiones;
    private Casillero borde;
    private Casillero[][] casilleros;

    public TableroEspacial(Dimensiones dimensionesDelTablero) {

        dimensiones = dimensionesDelTablero;
        inicializarCasilleros();
        inicializarPiezas();
    }

    public TableroEspacial(int filas, int columnas) {

        this(new Dimensiones(filas, columnas));
    }

    public TableroEspacial(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {

        this(new Dimensiones(filaDesde, filaHasta, columnaDesde, columnaHasta));
    }

    private void inicializarCasilleros() {

        borde = new CasilleroBorde(this, Integer.MAX_VALUE, Integer.MAX_VALUE);
        casilleros = new Casillero[contarFilas() + 2][contarColumnas() + 2];

        conCadaCoordenada(this::crearCasilleroInterior);
        conCadaCoordenadaDelBorde(this::crearCasilleroBorde);
    }

    protected void inicializarPiezas() {

    }

    private void crearCasilleroInterior(int fila, int columna) {

        asignar(fila, columna, new CasilleroInterior(this, fila, columna));
    }

    private void crearCasilleroBorde(int fila, int columna) {

        asignar(fila, columna, new CasilleroBorde(this, fila, columna));
    }

    private void asignar(int fila, int columna, Casillero casillero) {

        casilleros[dimensiones.indexarFila(fila)][dimensiones.indexarColumna(columna)] = casillero;
    }

    @Override
    public void registrar(Observador unObservador) {

        observadores.agregar(unObservador);
    }

    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

        observadores.fueAgregadaEn(casillero, unaPieza);
    }

    @Override
    public void conCadaCasillero(Casillero.Consumidor unConsumidor) {

        for (int indiceFila = 0; indiceFila < casilleros.length; indiceFila++) {

            for (int indiceColumna = 0; indiceColumna < casilleros[indiceFila].length; indiceColumna++) {

                casilleros[indiceFila][indiceColumna].aceptar(unConsumidor);
            }
        }
    }

    private void conCadaCoordenada(Coordenadas.Consumidor consumidor) {

        conCadaCoordenadaEnRango(obtenerFilaMinima(), obtenerColumnaMinima(),
                obtenerFilaMaxima(), obtenerColumnaMaxima(),
                consumidor);
    }

    private void conCadaCoordenadaEnRango(int filaInicial, int columnaInicial,
                                          int filaFinal, int columnaFinal,
                                          Coordenadas.Consumidor consumidor) {

        for (int fila = filaInicial; fila <= filaFinal; fila++) {

            for (int columna = columnaInicial; columna <= columnaFinal; columna++) {

                consumidor.aceptar(fila, columna);
            }
        }
    }

    private void conCadaCoordenadaDelBorde(Coordenadas.Consumidor consumidor) {

        final int filaMinima = obtenerFilaMinima();
        final int filaMaxima = obtenerFilaMaxima();
        final int columnaMinima = obtenerColumnaMinima();
        final int columnaMaxima = obtenerColumnaMaxima();

        conCadaCoordenadaEnRango(filaMinima - 1, columnaMinima - 1, filaMinima - 1, columnaMaxima + 1, consumidor);
        conCadaCoordenadaEnRango(filaMaxima + 1, columnaMinima - 1, filaMaxima + 1, columnaMaxima + 1, consumidor);
        conCadaCoordenadaEnRango(filaMinima, columnaMinima - 1, filaMaxima, columnaMinima - 1, consumidor);
        conCadaCoordenadaEnRango(filaMinima, columnaMaxima + 1, filaMaxima, columnaMaxima + 1, consumidor);
    }

    public Casillero obtenerCasilleroEn(int fila, int columna) {

        return estaEnElBorde(fila, columna) ?
                borde :casilleros[dimensiones.indexarFila(fila)][dimensiones.indexarColumna(columna)];
    }


    private List<Casillero> obtenerCasillerosEnRango(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {

        List<Casillero> casilleros = new LinkedList<>();

        conCadaCoordenadaEnRango(filaInicial, columnaInicial, filaFinal, columnaFinal,
                (fila, columna) -> casilleros.add(obtenerCasilleroEn(fila, columna)));

        return casilleros;
    }

    private boolean estaEnElBorde(int fila, int columna) {

        return (fila < obtenerFilaMinima()) ||
                (fila > obtenerFilaMaxima()) ||
                (columna < obtenerColumnaMinima()) ||
                (columna > obtenerColumnaMaxima());
    }

    @Override
    public EnCasillero enCasillero(int fila, int columna) {

        Casillero casillero = obtenerCasilleroEn(fila, columna);

        return new UsandoFabricaColocarEnCasillero(fabrica, casillero);
    }

    @Override
    public EnBase enBase(BaseEspacial base) {

        return new UsandoFabricaEnBase(fabrica, base);
    }

    @Override
    public int contarFilas() {

        return dimensiones.contarFilas();
    }

    @Override
    public EnCasilleros enCasilleros(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {

        List<Casillero> casilleros = obtenerCasillerosEnRango(filaInicial, columnaInicial, filaFinal, columnaFinal);

        return new UsandoFabricaColocarEnCasilleros(fabrica, casilleros);
    }

    @Override
    public int contarColumnas() {

        return dimensiones.contarColumnas();
    }

    @Override
    public int obtenerFilaMinima() {

        return dimensiones.obtenerFilaMinima();
    }

    @Override
    public int obtenerColumnaMinima() {

        return dimensiones.obtenerColumnaMinima();
    }

    @Override
    public int obtenerFilaMaxima() {

        return dimensiones.obtenerFilaMaxima();
    }

    @Override
    public int obtenerColumnaMaxima() {

        return dimensiones.obtenerColumnaMaxima();
    }

    @Override
    public String toString() {

        return String.format("Tablero[%d..%d][%d..%d]",
                obtenerFilaMinima(),
                obtenerFilaMaxima(),
                obtenerColumnaMinima(),
                obtenerColumnaMaxima());
    }
}
