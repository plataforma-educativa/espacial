package espacial.piezas;

import espacial.Amarre;
import espacial.Ataque;
import espacial.BaseEspacial;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.excepciones.LaBaseNoEstaEnUnCasillero;
import espacial.piezas.rasgos.Aliado;
import espacial.piezas.rasgos.BaseDeposito;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.utiles.Referencia;

import java.util.LinkedList;
import java.util.List;

public class EstacionCentral implements BaseEspacial, PiezaAtacable, BaseDeposito, Aliado {

    private final Indicador puntos = new Indicador(200);
    private final Bodega bodega = new Bodega(obtenerCapacidad());
    private final List<Amarre> amarres = new LinkedList<>();
    private final Observadores observadores = new Observadores();
    private final Referencia<Casillero> casillero = Referencia.conValorNulo();

    public EstacionCentral() {

        puntos.cuandoSeAgota(this::fueDestruido);
        puntos.cuandoCambia(this::notificarQueCambioElEstado);
        bodega.cuandoCambiaLaCarga(this::notificarQueCambioElEstado);
        casillero.siEsNuloAlObtener(this::lanzarExcepcionPorqueEstaEnUnCasillero);
    }

    private void fueDestruido() {

        casillero.obtener().desocupar();
        notificarQueFueDestruido();
    }

    private void notificarQueFueDestruido() {

        observadores.fueDestruida(this);
    }

    private void notificarQueCambioElEstado() {

        observadores.cambioElEstadoDe(this);
    }

    private Casillero lanzarExcepcionPorqueEstaEnUnCasillero() {

        throw new LaBaseNoEstaEnUnCasillero();
    }

    @Override
    public void fueColocadaEn(Casillero unCasillero) {
     
        casillero.cambiar(unCasillero);
    }

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }

    @Override
    public int obtenerNivelDeDefensas() {

        return puntos.obtenerNivel();
    }

    @Override
    public void amarrar(NaveEspacial pieza) {
        
        Amarre amarre = new AmarreConBaseEspacial(pieza);
        amarres.add(amarre);
        pieza.fueAmarradaCon(amarre);
    }

    @Override
    public Amarre[] obtenerAmarres() {

        return amarres.toArray(new Amarre[0]);
    }

    @Override
    public boolean tieneNavesAmarradas() {

        return ! amarres.isEmpty();
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsBase(this);
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnaBase();
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
    public Cargamento obtenerAntimateria() {

        return bodega.ANTIMATERIA;
    }

    @Override
    public Cargamento obtenerMetal() {

        return bodega.METAL;
    }

    @Override
    public Cargamento obtenerCristal() {

        return bodega.CRISTAL;
    }

    @Override
    public void registrar(Observador observador) {

        observadores.agregar(observador);
    }

    @Override
    public String toString() {

        return describir();
    }

    private class AmarreConBaseEspacial implements Amarre {
        
        private final NaveEspacial pieza;
        
        private AmarreConBaseEspacial(NaveEspacial unaPieza) {
            
            pieza = unaPieza;
        }
        
        public NaveEspacial obtenerPieza() {
            
            return pieza;
        }
        
        @Override
        public void soltar() {

            if (amarres.remove(this)) {

                casillero.obtener().ocuparCon(pieza);
            }
        }
    }
}
