package espacial.piezas;

import espacial.Ataque;
import espacial.BaseEspacial;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Visitante;
import espacial.excepciones.ErrorEspacial;
import espacial.piezas.rasgos.BaseDeposito;
import espacial.piezas.rasgos.Neutral;
import espacial.piezas.rasgos.PiezaAtacable;

public class BaseDesconocida implements BaseEspacial, PiezaAtacable, BaseDeposito, Neutral {

    private final Indicador puntos = new Indicador(200);
    private final Bodega bodega = new Bodega(obtenerCapacidad());
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

        visitante.siEsBase(this);
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
    public void recibio(Ataque unAtaque) {

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

    @Override
    public Cargamento obtenerAntimateria() {

        return bodega.ANTIMATERIA;
    }

    @Override
    public Cargamento obtenerCristal() {

        return bodega.CRISTAL;
    }

    @Override
    public Cargamento obtenerMetal() {

        return bodega.METAL;
    }

    @Override
    public int obtenerNivelDeDefensas() {

        return puntos.obtenerNivel();
    }

    @Override
    public void amarrar(NaveEspacial pieza) {

        throw new ErrorEspacial("No se puede amarrar una NAVE porque esta BASE está fuera de servicio");
    }
}
