package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.ObservadorDelTablero;
import espacial.Pieza;
import espacial.Tablero;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class PanelConTablero extends GridPane implements ObservadorDelTablero {

    private final Dibujante dibujante = new Dibujante();

    private final Tablero tablero;

    public PanelConTablero(Tablero unTablero) {

        tablero = unTablero;
        tablero.registrar(this);
        disponerCasilleros();
    }

    private void disponerCasilleros() {

        setVgap(0);
        setHgap(0);

        tablero.conCadaCasilleroAceptar(this::agregar);
        //tablero.conCadaCoordenadaDelBorde(this::agregarBorde);
    }

    private void agregar(Casillero casillero, Pieza... piezas) {

        Indices indices = Indices.para(casillero);

        agregar(casillero, indices);

        for (Pieza pieza : piezas) {

            agregar(pieza, indices);
        }
    }

    private void agregar(Pieza pieza, Indices indices) {

        Group panel = new PanelConPieza(pieza);
        add(panel, indices.deColumna(), indices.deFila());
        GridPane.setValignment(panel, VPos.CENTER);
        GridPane.setHalignment(panel, HPos.CENTER);
    }

    private void agregarBorde(int fila, int columna) {

        //add(dibujante.dibujarBorde(), columnaGrilla(columna), filaGrilla(fila));
    }

    private void agregar(Casillero casillero, Indices indices) {

        add(dibujante.dibujar(casillero), indices.deColumna(), indices.deFila());
    }

    @Override
    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

    }
}