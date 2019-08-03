package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.Pieza;
import espacial.interfaz.ControladorDePartida;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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

    @Override
    public void fueDestruida(Pieza unaPieza) {

        controlador.reproducir(enParalelo(agrandar(), desvanecer()), ocultar());
    }

    private Animation enParalelo(Animation... animaciones) {

        return new ParallelTransition(animaciones);
    }

    private Animation agrandar() {

        ScaleTransition agrandar = new ScaleTransition(Duration.millis(500), this);
        agrandar.setByX(5);
        agrandar.setByY(5);
        agrandar.setAutoReverse(true);

        return agrandar;
    }

    private Animation desvanecer() {

        FadeTransition desvanecer = new FadeTransition(Duration.millis(500), this);
        desvanecer.setInterpolator(Interpolator.EASE_OUT);
        desvanecer.setFromValue(1);
        desvanecer.setToValue(0);

        return desvanecer;
    }

    private Animation ocultar() {

        return new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(this.visibleProperty(), false)));
    }
}
