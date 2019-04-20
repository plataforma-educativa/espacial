package espacial;

public class Tablero {

    private EspectroEspacial[][] casilleros;
    
    public Tablero() {
        
        casilleros = new EspectroEspacial[contarFilas()][contarColumnas()];

        colocarEntreCoordenadas(obtenerFilaMinima(), obtenerColumnaMinima(), 
                                obtenerFilaMaxima(), obtenerColumnaMaxima(), 
                                EspectroEspacial.VACIO);
        colocarEnCoordenada(0, 0, EspectroEspacial.BASE);
        colocarEnCoordenada(-2, -2, EspectroEspacial.CONTENEDOR);
        colocarEnCoordenada(4, 2, EspectroEspacial.CONTENEDOR);
        colocarEnCoordenada(2, -7, EspectroEspacial.CONTENEDOR);
        colocarEnCoordenada(1, -3, EspectroEspacial.ASTEROIDE);
        colocarEntreCoordenadas(7, -1, 7, 0, EspectroEspacial.ASTEROIDE);
        colocarEntreCoordenadas(8, -3, 8, 3, EspectroEspacial.ASTEROIDE);
        colocarEnCoordenada(-6, 0, EspectroEspacial.ASTEROIDE);
        colocarEnCoordenada(-9, 0, EspectroEspacial.ASTEROIDE);
        colocarEnCoordenada(-2, 4, EspectroEspacial.ASTEROIDE);
        colocarEnCoordenada(2, 6, EspectroEspacial.ASTEROIDE);
        colocarEnCoordenada(2, -5, EspectroEspacial.ASTEROIDE);
    }

    private void colocarEntreCoordenadas(int filaInicial, int columnaInicial,
                                         int filaFinal, int columnaFinal,
                                         EspectroEspacial espectro) {
        
        conCadaCoordenadaEnRango(filaInicial, columnaInicial, filaFinal, columnaFinal,
                                 (fila, columna) -> colocarEnCoordenada(fila, columna, espectro));
    }

    private void colocarEnCoordenada(int fila, int columna, EspectroEspacial espectro) {

        casilleros[subindiceFila(fila)][subindiceColumna(columna)] = espectro;
    }

    private int subindiceColumna(int columna) {
        
        return columna - obtenerColumnaMinima();
    }

    private int subindiceFila(int fila) {
        
        return fila - obtenerFilaMinima();
    }
    
    public int contarColumnas() {

        return obtenerColumnaMaxima() - obtenerColumnaMinima() + 1;
    }

    public int contarFilas() {

        return obtenerFilaMaxima() - obtenerFilaMinima() + 1;
    }

    public EspectroEspacial obtener(int fila, int columna) {

        return casilleros[subindiceFila(fila)][subindiceColumna(columna)]; 
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
}
