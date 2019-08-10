package espacial.piezas;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Obstaculo;
import espacial.Pieza;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Observadores implements Pieza.Observador {

    private List<Pieza.Observador> elementos = new CopyOnWriteArrayList<>();

    public void agregar(Pieza.Observador unObservador) {

        elementos.add(unObservador);
    }

    public void propagar(Notificacion notificacion) {

        elementos.forEach(notificacion::notificar);
    }

    @Override
    public void fueMovida(Pieza unaPieza, Casillero aCasillero) {

        propagar(observador -> observador.fueMovida(unaPieza, aCasillero));
    }

    @Override
    public void fueAtacada(Pieza unaPieza, Ataque conAtaque) {

        propagar(observador -> observador.fueAtacada(unaPieza, conAtaque));
    }

    @Override
    public void fueChocada(Pieza unaPieza, Obstaculo contraObstaculo) {

        propagar(observador -> observador.fueChocada(unaPieza, contraObstaculo));
    }

    @Override
    public void cambioElEstadoDe(Pieza unaPieza) {

        propagar(observador -> observador.cambioElEstadoDe(unaPieza));
    }

    @Override
    public void fueDestruida(Pieza unaPieza) {

        propagar(observador -> observador.fueDestruida(unaPieza));
    }

    @FunctionalInterface
    public interface Notificacion {

        void notificar(Pieza.Observador observador);
    }
}
