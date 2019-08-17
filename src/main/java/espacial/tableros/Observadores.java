package espacial.tableros;

import espacial.Casillero;
import espacial.Pieza;
import espacial.Tablero;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Observadores implements Tablero.Observador {

    private List<Tablero.Observador> elementos = new CopyOnWriteArrayList<>();

    public void agregar(Tablero.Observador unObservador) {

        elementos.add(unObservador);
    }

    public void propagar(Notificacion notificacion) {

        elementos.forEach(notificacion::notificar);
    }

    @Override
    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

        propagar(observador -> observador.fueAgregadaEn(casillero, unaPieza));
    }

    @FunctionalInterface
    public interface Notificacion {

        void notificar(Tablero.Observador observador);
    }
}
