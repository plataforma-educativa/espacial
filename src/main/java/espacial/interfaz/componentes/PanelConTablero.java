package espacial.interfaz.componentes;

import espacial.Tablero;
import javafx.scene.layout.GridPane;

public class PanelConTablero extends GridPane {

    private final Dibujante dibujante = new Dibujante();

    private final Tablero tablero;

    public PanelConTablero(Tablero unTablero) {

        tablero = unTablero;
        disponerPiezas();
    }

    private void disponerPiezas() {

        setVgap(0);
        setHgap(0);

        tablero.conCadaCoordenada(this::agregarCasillero);
        tablero.conCadaCoordenadaDelBorde(this::agregarBorde);
    }

    private void agregarBorde(int fila, int columna) {

        add(dibujante.dibujarBorde(), columnaGrilla(columna), filaGrilla(fila));
    }

    private void agregarCasillero(int fila, int columna) {

        add(dibujante.dibujarCasillero(fila, columna), columnaGrilla(columna), filaGrilla(fila));
    }

    private int columnaGrilla(int columna) {
        
        return columna - (tablero.obtenerColumnaMinima() - 1);
    }
    
    private int filaGrilla(int fila) {
        
        return fila - (tablero.obtenerFilaMinima() - 1);
    }
}
