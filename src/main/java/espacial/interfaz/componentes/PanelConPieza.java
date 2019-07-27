package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.Pieza;
import espacial.interfaz.ControladorDePartida;
import javafx.animation.TranslateTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.util.Duration;

public class PanelConPieza extends Group implements Pieza.Observador {

    private final ControladorDePartida controlador;

    private final Pieza pieza;

    public PanelConPieza(ControladorDePartida unControlador, Pieza unaPieza) {

        controlador = unControlador;
        pieza = unaPieza;
        getChildren().add(controlador.conDibujante().dibujar(pieza));
        setCursor(Cursor.HAND);
        setOnMousePressed(event -> unControlador.fueSeleccionada(pieza));
        pieza.registrar(this);
    }

    public PanelConPieza en(Posicion posicion) {

        setTranslateX(posicion.enX());
        setTranslateY(posicion.enY());

        return this;
    }

    @Override
    public void fueMovida(Pieza unaPieza, Casillero casillero) {

        Posicion posicion = Posicion.de(casillero);

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(this);
        transition.setDuration(Duration.millis(1000));
        transition.setToX(posicion.enX());
        transition.setToY(posicion.enY());

        controlador.reproducir(transition);

    }

    @Override
    public void cambioElEstadoDe(Pieza unaPieza) {


    }
}
