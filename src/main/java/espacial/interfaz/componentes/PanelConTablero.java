package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.ObservadorDelTablero;
import espacial.Pieza;
import espacial.Tablero;
import espacial.interfaz.ControladorDePartida;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class PanelConTablero extends StackPane implements ObservadorDelTablero {

    private final GridPane grilla = new GridPane();
    private final AnchorPane piezas = new AnchorPane();
    private final ControladorDePartida controlador;
    private final Tablero tablero;

    public PanelConTablero(ControladorDePartida unControlador, Tablero unTablero) {

        getChildren().addAll(grilla, piezas);
        grilla.setVgap(0);
        grilla.setHgap(0);
        controlador = unControlador;
        tablero = unTablero;
        tablero.registrar(this);
        tablero.conCadaCasilleroAceptar(this::agregar);
    }

    private void agregar(Casillero casillero, Pieza... piezas) {

        Posicion posicion = Posicion.de(casillero);

        agregar(casillero, posicion);

        for (Pieza pieza : piezas) {

            agregar(pieza, posicion);
        }
    }

    private void agregar(Pieza pieza, Posicion posicion) {

        piezas.getChildren().add(new PanelConPieza(controlador, pieza).en(posicion));
    }

    private void agregar(Casillero casillero, Posicion posicion) {

        grilla.add(controlador.conDibujante().dibujar(casillero), posicion.enColumna(), posicion.enFila());
    }

    @Override
    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

        Platform.runLater(() -> agregar(unaPieza, Posicion.de(casillero)));
    }
}