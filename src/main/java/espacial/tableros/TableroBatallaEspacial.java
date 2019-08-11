package espacial.tableros;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.ConsumidorDeCasilleros;
import espacial.ConsumidorDeCoordenadas;
import espacial.NaveEspacial;
import espacial.ObservadorDelTablero;
import espacial.Pieza;
import espacial.partidas.FabricaDePiezas;
import espacial.utiles.Proveedor;

/**
 * Implementación del Tablero usado por defecto en la BatallaEspacial.
 *
 * @author Mariano Tugnarelli
 */
public class TableroBatallaEspacial implements TableroContenedor {

    private FabricaDePiezas fabrica = FabricaDePiezas.crear();
    private Casillero borde;
    private Casillero[][] casilleros;
    private ObservadorDelTablero observador;

    public TableroBatallaEspacial() {

        inicializarCasilleros();

        colocarEnCoordenada(-2, -2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(4, 2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(2, -7, fabrica::crearContenedorDeCristal);
        colocarEnCoordenada(10, -24, fabrica::crearContenedorDeMetal);
        colocarEnCoordenada(-10, 14, fabrica::crearBaseDesconocida);
        colocarEnCoordenada(1, -3, fabrica::crearAsteoride);
        colocarEntreCoordenadas(7, -1, 7, 0, fabrica::crearAsteoride);
        colocarEntreCoordenadas(8, -3, 8, 3, fabrica::crearAsteoride);
        colocarEnCoordenada(-6, 0, fabrica::crearAsteoride);
        colocarEnCoordenada(-9, 0, fabrica::crearAsteoride);
        colocarEnCoordenada(-2, 4, fabrica::crearAsteoride);
        colocarEnCoordenada(2, 6, fabrica::crearAsteoride);
        colocarEnCoordenada(2, -5, fabrica::crearAsteoride);
        colocarEnCoordenada(3, -6, fabrica::crearAgujeroNegro);
        colocarEnCoordenada(2, -6, fabrica::crearAgujeroNegro);
        colocarEnCoordenada(1, -6, fabrica::crearAgujeroNegro);
        colocarEnCoordenada(1, -7, fabrica::crearAgujeroNegro);
    }

    private void inicializarCasilleros() {

        borde = new CasilleroBorde(this, Integer.MAX_VALUE, Integer.MAX_VALUE);
        casilleros = new Casillero[contarFilas() + 2][contarColumnas() + 2];

        conCadaCoordenada(this::crearCasilleroInterior);
        conCadaCoordenadaDelBorde(this::crearCasilleroBorde);
    }

    private void crearCasilleroInterior(int fila, int columna) {

        casilleros[indiceFila(fila)][indiceColumna(columna)] = new CasilleroInterior(this, fila, columna);
    }

    private void crearCasilleroBorde(int fila, int columna) {

        casilleros[indiceFila(fila)][indiceColumna(columna)] = new CasilleroBorde(this, fila, columna);
    }

    private void colocarEntreCoordenadas(int filaInicial, int columnaInicial,
                                         int filaFinal, int columnaFinal,
                                         Proveedor<? extends Pieza> proveedorDePieza) {

        conCadaCoordenadaEnRango(filaInicial, columnaInicial, filaFinal, columnaFinal,
                (fila, columna) -> colocarEnCoordenada(fila, columna, proveedorDePieza));
    }

    private void colocarEnCoordenada(int fila, int columna, Proveedor<? extends Pieza> proveedorDePieza) {

        casilleros[indiceFila(fila)][indiceColumna(columna)].ocuparCon(proveedorDePieza.obtener());
    }

    private int indiceColumna(int columna) {

        return columna - obtenerColumnaMinima() + 1;
    }

    private int indiceFila(int fila) {

        return fila - obtenerFilaMinima() + 1;
    }

    @Override
    public void registrar(ObservadorDelTablero unObservador) {

        observador = unObservador;
    }

    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

        if (observador != null) {

            observador.fueAgregadaEn(casillero, unaPieza);
        }
    }

    @Override
    public int contarColumnas() {

        return obtenerColumnaMaxima() - obtenerColumnaMinima() + 1;
    }

    @Override
    public int contarFilas() {

        return obtenerFilaMaxima() - obtenerFilaMinima() + 1;
    }

    @Override
    public int obtenerFilaMaxima() {

        return 10;
    }

    @Override
    public int obtenerFilaMinima() {

        return -10;
    }

    @Override
    public int obtenerColumnaMaxima() {

        return 26;
    }

    @Override
    public int obtenerColumnaMinima() {

        return -26;
    }

    @Override
    public void conCadaCasilleroAceptar(ConsumidorDeCasilleros unConsumidor) {

        for (int fila = 0; fila < casilleros.length; fila++) {

            for (int columa = 0; columa < casilleros[fila].length; columa++) {

                casilleros[fila][columa].aceptar(unConsumidor);
            }
        }
    }

    private void conCadaCoordenadaDelBorde(ConsumidorDeCoordenadas consumidor) {

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
                borde : casilleros[indiceFila(fila)][indiceColumna(columna)];
    }

    private boolean estaEnElBorde(int fila, int columna) {

        return (fila < obtenerFilaMinima()) ||
                (fila > obtenerFilaMaxima()) ||
                (columna < obtenerColumnaMinima()) ||
                (columna > obtenerColumnaMaxima());
    }

    public BaseEspacial colocarBaseEnCasillero(int fila, int columna) {

        BaseEspacial base = fabrica.crearBaseEspacial();

        obtenerCasilleroEn(fila, columna).ocuparCon(base);

        return base;
    }

    @Override
    public NaveEspacial crearNave() {

        return fabrica.crearNaveEspacial();
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
