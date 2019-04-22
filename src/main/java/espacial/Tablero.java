package espacial;

import espacial.piezas.Asteroide;
import espacial.piezas.BaseEspacial;
import espacial.piezas.ContenedorDeAntimateria;

/**
 * El Tablero mantiene el ordenamiento relativo de las Piezas en una Partida.
 * Cada Casillero está ocupado exclusivamente por una Pieza.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Tablero {

    private Casillero margen;
    private Casillero[][] casilleros;
    
    public Tablero() {
        
        inicializarCasilleros();

        colocarEnCoordenada(0, 0, BaseEspacial::new);
        colocarEnCoordenada(-2, -2, ContenedorDeAntimateria::new);
        colocarEnCoordenada(4, 2, ContenedorDeAntimateria::new);
        colocarEnCoordenada(2, -7, ContenedorDeAntimateria::new);
        colocarEnCoordenada(1, -3, Asteroide::new);
        colocarEntreCoordenadas(7, -1, 7, 0, Asteroide::new);
        colocarEntreCoordenadas(8, -3, 8, 3, Asteroide::new);
        colocarEnCoordenada(-6, 0, Asteroide::new);
        colocarEnCoordenada(-9, 0, Asteroide::new);
        colocarEnCoordenada(-2, 4, Asteroide::new);
        colocarEnCoordenada(2, 6, Asteroide::new);
        colocarEnCoordenada(2, -5, Asteroide::new);
    }

    private void inicializarCasilleros() {

        margen = new Borde(this);
        casilleros = new Casillero[contarFilas()][contarColumnas()];
        
        conCadaCoordenada((fila, columna) -> {
            casilleros[indiceFila(fila)][indiceColumna(columna)] = new Casillero(this, fila, columna);
        });
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
        
        return columna - obtenerColumnaMinima();
    }

    private int indiceFila(int fila) {
        
        return fila - obtenerFilaMinima();
    }
    
    public int contarColumnas() {

        return obtenerColumnaMaxima() - obtenerColumnaMinima() + 1;
    }

    public int contarFilas() {

        return obtenerFilaMaxima() - obtenerFilaMinima() + 1;
    }

    public EspectroEspacial escanearEn(int fila, int columna) {

        return obtenerCasillero(fila, columna).escanear(); 
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

    public void conCadaCoordenada(ConsumidorDeCoordenadas consumidor) {
        
        conCadaCoordenadaEnRango(obtenerFilaMinima(), obtenerColumnaMinima(),
                                 obtenerFilaMaxima(), obtenerColumnaMaxima(),
                                 consumidor);
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

    public Casillero obtenerCasillero(int fila, int columna) {
        
        return estaEnElMargen(fila, columna) ? 
                margen : casilleros[indiceFila(fila)][indiceColumna(columna)];
    }
    
    private boolean estaEnElMargen(int fila, int columna) {
        
        return (fila < obtenerFilaMinima()) || 
               (fila > obtenerFilaMaxima()) ||
               (columna < obtenerColumnaMinima()) || 
               (columna > obtenerColumnaMaxima());
    }

    public Casillero obtenerCasillero(Coordenada coordenada) {

        return obtenerCasillero(coordenada.obtenerFila(), coordenada.obtenerColumna());
    }
}
