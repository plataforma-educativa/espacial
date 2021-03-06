package espacial.piezas;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.piezas.rasgos.AsteroideAtacable;
import espacial.piezas.rasgos.Neutral;

public class Asteroide implements Pieza, AsteroideAtacable, Neutral {

    private final Indicador puntos = new Indicador(300);

    private final Dureza dureza;

    private final Observadores observadores = new Observadores();

    public Asteroide(int valorDureza) {

        dureza = new Dureza(valorDureza);
    }

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    public Dureza obtenerDureza() {

        return dureza;
    }

    @Override
    public void fueColocadaEn(Casillero casillero) {

        puntos.cuandoSeAgota(() -> {

            casillero.desocupar();
            observadores.fueDestruida(this);
        });
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnAsteroide();
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsAsteroide(this);
    }

    @Override
    public int obtenerPuntos() {

        return puntos.obtenerValor();
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos.decrementarEn(decremento);
    }

    @Override
    public void despuesDeSerAtacadoCon(Ataque unAtaque) {

        observadores.fueAtacada(this, unAtaque);
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
