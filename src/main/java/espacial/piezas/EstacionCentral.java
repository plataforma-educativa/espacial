package espacial.piezas;

import espacial.Amarre;
import espacial.BaseEspacial;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Visitante;
import espacial.piezas.rasgos.BaseDeposito;
import espacial.piezas.rasgos.PiezaAtacable;

import java.util.LinkedList;
import java.util.List;

public class EstacionCentral implements BaseEspacial, PiezaAtacable, BaseDeposito {

    private final Indicador puntos = new Indicador(200);
    private final Bodega bodega = new Bodega(obtenerCapacidad());
    private final List<Amarre> amarres = new LinkedList<>();
    private final Observadores observadores = new Observadores();
    private Casillero casillero;

    public EstacionCentral() {

        puntos.cuandoSeAgota(this::fueDestruido);
        bodega.cuandoCambiaLaCarga(this::notificarQueCambioElEstado);
    }

    private void fueDestruido() {

        casillero.desocupar();
        notificarQueFueDestruido();
    }

    private void notificarQueFueDestruido() {

        observadores.fueDestruida(this);
    }

    private void notificarQueCambioElEstado() {

        observadores.cambioElEstadoDe(this);
    }

    @Override
    public void fueColocadaEn(Casillero casillero) {
     
        this.casillero = casillero;
    }

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }

    @Override
    public void amarrar(NaveEspacial pieza) {
        
        Amarre amarre = new AmarreConBaseEspacial(pieza);
        amarres.add(amarre);
        pieza.fueAmarradaCon(amarre);
    }

    public Amarre[] obtenerAmarres() {

        return amarres.toArray(new Amarre[0]);
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

                casillero.ocuparCon(pieza);
            }
        }
    }
}
