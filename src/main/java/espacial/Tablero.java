package espacial;

import espacial.partidas.FabricaDePiezas;
import espacial.piezas.AgujeroNegro;
import espacial.piezas.BaseEspacial;
import espacial.tableros.CasilleroBorde;
import espacial.tableros.CasilleroInterior;
import espacial.utiles.Proveedor;

/**
 * El Tablero mantiene el ordenamiento relativo de las Piezas en una Partida.
 * Cada Casillero está ocupado exclusivamente por una Pieza.
 *
 * @author Mariano Tugnarelli
 */
public class Tablero {

    private FabricaDePiezas fabrica = FabricaDePiezas.crear();
    private Casillero borde;
    private Casillero[][] casilleros;
    private ObservadorDelTablero observador;

    public Tablero() {

        inicializarCasilleros();

        colocarEnCoordenada(-2, -2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(4, 2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(2, -7, fabrica::crearContenedorDeCristal);
        colocarEnCoordenada(10, -24, fabrica::crearContenedorDeMetal);
        colocarEnCoordenada(1, -3, fabrica::crearAsteoride);
        colocarEntreCoordenadas(7, -1, 7, 0, fabrica::crearAsteoride);
        colocarEntreCoordenadas(8, -3, 8, 3, fabrica::crearAsteoride);
        colocarEnCoordenada(-6, 0, fabrica::crearAsteoride);
        colocarEnCoordenada(-9, 0, fabrica::crearAsteoride);
        colocarEnCoordenada(-2, 4, fabrica::crearAsteoride);
        colocarEnCoordenada(2, 6, fabrica::crearAsteoride);
        colocarEnCoordenada(2, -5, fabrica::crearAsteoride);
        colocarEnCoordenada(3, -6, AgujeroNegro::new);
        colocarEnCoordenada(2, -6, AgujeroNegro::new);
        colocarEnCoordenada(1, -6, AgujeroNegro::new);
        colocarEnCoordenada(1, -7, AgujeroNegro::new);
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

    public void registrar(ObservadorDelTablero unObservador) {

        observador = unObservador;
    }

    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

        if (observador != null) {

            observador.fueAgregadaEn(casillero, unaPieza);
        }
    }
    
    public int contarColumnas() {

        return obtenerColumnaMaxima() - obtenerColumnaMinima() + 1;
    }

    public int contarFilas() {

        return obtenerFilaMaxima() - obtenerFilaMinima() + 1;
    }

    public EspectroEspacial escanearEn(int fila, int columna) {

        return obtenerCasilleroEn(fila, columna).escanear();
    }

    public int obtenerFilaMaxima() {

        return 10;
    }

    public int obtenerFilaMinima() {

        return -10;
    }

    public int obtenerColumnaMaxima() {

        return 26;
    }

    public int obtenerColumnaMinima() {

        return -26;
    }

    public void conCadaCasilleroAceptar(ConsumidorDeCasilleros unConsumidor) {

        for (int fila = 0; fila < casilleros.length; fila++) {

            for (int columa = 0; columa < casilleros[fila].length; columa++) {

                casilleros[fila][columa].aceptar(unConsumidor);
            }
        }
    }

    public void conCadaCoordenada(ConsumidorDeCoordenadas consumidor) {

        conCadaCoordenadaEnRango(obtenerFilaMinima(), obtenerColumnaMinima(),
                obtenerFilaMaxima(), obtenerColumnaMaxima(),
                consumidor);
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

    private void conCadaCoordenadaEnRango(int filaInicial, int columnaInicial,
                                          int filaFinal, int columnaFinal,
                                          ConsumidorDeCoordenadas consumidor) {

        for (int fila = filaInicial; fila <= filaFinal; fila++) {

            for (int columna = columnaInicial; columna <= columnaFinal; columna++) {

                consumidor.aceptar(fila, columna);
            }
        }
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

    public Casillero obtenerCasilleroEn(Coordenadas coordenadas) {

        return obtenerCasilleroEn(coordenadas.obtenerFila(), coordenadas.obtenerColumna());
    }

    public void colocarEnCasillero(int fila, int columna, BaseEspacial baseEspacial) {

        obtenerCasilleroEn(fila, columna).ocuparCon(baseEspacial);
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
