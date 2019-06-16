package espacial;

import espacial.partidas.FabricaDePiezas;
import espacial.piezas.AgujeroNegro;
import espacial.piezas.Asteroide;
import espacial.piezas.BaseEspacial;
import espacial.piezas.ContenedorDeAntimateria;
import espacial.piezas.ContenedorDeCristal;
import espacial.tableros.CasilleroBorde;
import espacial.tableros.CasilleroInterior;
import espacial.utiles.Proveedor;

import java.util.Random;

/**
 * El Tablero mantiene el ordenamiento relativo de las Piezas en una Partida.
 * Cada Casillero está ocupado exclusivamente por una Pieza.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Tablero {

    private FabricaDePiezas fabrica = FabricaDePiezas.crear();
    private Casillero borde;
    private Casillero[][] casilleros;
    
    public Tablero() {
        
        inicializarCasilleros();

        colocarEnCoordenada(-2, -2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(4, 2, fabrica::crearContenedorDeAntimateria);
        colocarEnCoordenada(2, -7, this::contenedorDeCristal);
        colocarEnCoordenada(10, -24, fabrica::crearContenedorDeMetal);
        colocarEnCoordenada(1, -3, Asteroide::new);
        colocarEntreCoordenadas(7, -1, 7, 0, Asteroide::new);
        colocarEntreCoordenadas(8, -3, 8, 3, Asteroide::new);
        colocarEnCoordenada(-6, 0, Asteroide::new);
        colocarEnCoordenada(-9, 0, Asteroide::new);
        colocarEnCoordenada(-2, 4, Asteroide::new);
        colocarEnCoordenada(2, 6, Asteroide::new);
        colocarEnCoordenada(2, -5, Asteroide::new);
        colocarEnCoordenada(3, -6, AgujeroNegro::new);
        colocarEnCoordenada(2, -6, AgujeroNegro::new);
        colocarEnCoordenada(1, -6, AgujeroNegro::new);
        colocarEnCoordenada(1, -7, AgujeroNegro::new);
    }

    private void inicializarCasilleros() {

        borde = new CasilleroBorde(this);
        casilleros = new CasilleroInterior[contarFilas()][contarColumnas()];
        
        conCadaCoordenada((fila, columna) ->
            casilleros[indiceFila(fila)][indiceColumna(columna)] = new CasilleroInterior(this, fila, columna)
        );
    }

    private Pieza contenedorDeCristal() {

        ContenedorDeCristal contenedor = new ContenedorDeCristal();

        Random random = new Random();

        contenedor.cargarCristal(random.nextInt(250) + 1);

        return contenedor;
    }

    private Pieza contenedorDeAntimateria() {

        ContenedorDeAntimateria contenedor = new ContenedorDeAntimateria();

        Random random = new Random();

        contenedor.cargarAntimateria(random.nextInt(250) + 1);

        return contenedor;
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

    public Casillero obtenerCasilleroEn(Coordenada coordenada) {

        return obtenerCasilleroEn(coordenada.obtenerFila(), coordenada.obtenerColumna());
    }

    public void colocarEnCasillero(int fila, int columna, BaseEspacial baseEspacial) {

        obtenerCasilleroEn(fila, columna).ocuparCon(baseEspacial);
    }
}
