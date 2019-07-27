package espacial.piezas;

import espacial.Pieza;

import java.util.LinkedList;
import java.util.List;

public class Observadores {

    private List<Pieza.Observador> elementos = new LinkedList<>();

    public void registrar(Pieza.Observador unObservador) {

        elementos.add(unObservador);
    }

    public void propagar(Notificacion notificacion) {

        elementos.forEach(notificacion::notificar);
    }

    @FunctionalInterface
    public interface Notificacion {

        void notificar(Pieza.Observador observador);
    }
}
