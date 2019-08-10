package espacial.piezas;

import espacial.Ataque;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Visitante;
import espacial.piezas.rasgos.Neutral;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.piezas.rasgos.PiezaDeposito;

public abstract class ContenedorDeSustancia implements PiezaDeposito, PiezaAtacable, Neutral {

    private final Indicador puntos = new Indicador(50);

    protected final Observadores observadores = new Observadores();

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.CONTENEDOR;
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

        chocable.chocoContraUnContenedor();
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos.decrementarEn(decremento);
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsContenedor(this);
    }

    @Override
    public int obtenerPuntos() {

        return puntos.obtenerValor();
    }

    @Override
    public void despuesDeSerAtacadoCon(Ataque unAtaque) {

        observadores.fueAtacada(this, unAtaque);
    }

    protected int obtenerCapacidad() {

        return 1000;
    }

    @Override
    public void registrar(Observador observador) {

        observadores.agregar(observador);
    }

    private void notificarQueCambioElEstado() {

        observadores.cambioElEstadoDe(this);
    }

    protected Cargamento crearCargamento() {

        return new CargamentoObservado(new CargamentoDeSustancia(obtenerCapacidad()), this::notificarQueCambioElEstado);
    }

    @Override
    public String toString() {

        return describir();
    }
}
