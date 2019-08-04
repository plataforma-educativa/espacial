package espacial.piezas;

import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Visitante;
import espacial.piezas.rasgos.Neutral;
import espacial.piezas.rasgos.PiezaAtacable;

public class BaseDesconocida implements PiezaAtacable, Neutral {

    private final Indicador puntos = new Indicador(200);

    private final Observadores observadores = new Observadores();

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos.decrementarEn(decremento);
    }

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.BASE;
    }

    @Override
    public void aceptar(Visitante visitante) {

    }

    @Override
    public void fueColocadaEn(Casillero casillero) {

        puntos.cuandoSeAgota(() -> {

            casillero.desocupar();
            observadores.fueDestruida(this);
        });
    }

    @Override
    public int obtenerPuntos() {

        return puntos.obtenerValor();
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnaBase();
    }

    @Override
    public void registrar(Observador observador) {

        observadores.agregar(observador);
    }

    @Override
    public String toString() {

        return describir();
    }
}
