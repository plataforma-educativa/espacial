package espacial.piezas;

import java.util.Optional;

import espacial.*;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.piezas.rasgos.NaveChocable;
import espacial.piezas.rasgos.PiezaAtacable;

public class CazaEspacial implements NaveEspacial, NaveChocable, PiezaAtacable {

    private Indicador nivelDeEscudos = new Indicador(100);
    private Artilleria artilleria = new Artilleria(100);
    private Optional<Casillero> casillero = Optional.empty();
    private Optional<Amarre> amarre = Optional.empty();

    public CazaEspacial() {

        nivelDeEscudos.cuandoSeAgota(this::fueDestruido);
    }

    @Override
    public void despegar() {

        Amarre amarreActual = amarre.orElseThrow(LaNaveNoEstaEnLaBase::new);

        amarreActual.soltar();
    }

    @Override
    public void fueColocadaEn(Casillero unCasillero) {
        
        casillero = Optional.of(unCasillero);
    }
    
    @Override
    public void fueAmarradaCon(Amarre unAmarre) {
    
        amarre = Optional.of(unAmarre);
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.NAVE;
    }

    @Override
    public void moverEn(Direccion direccionElegida) {
        
        Casillero origen = obtenerCasillero();
        
        Casillero destino = origen.obtenerContiguoEn(direccionElegida);
        
        origen.moverPiezaA(destino);
    }

    @Override
    public int obtenerNivelDeEscudos() {

        return nivelDeEscudos.obtenerValor();
    }

    @Override
    public int obtenerCantidadDeTorpedosDeFotones() {

        return artilleria.contarTorpedosDeFotones();
    }

    @Override
    public int obtenerPuntos() {

        return obtenerNivelDeEscudos();
    }

    @Override
    public void disminuirNivelDeEscudosEn(int diferencia) {

        nivelDeEscudos.decrementarEn(diferencia);
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        disminuirNivelDeEscudosEn(decremento);
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsNave(this);
    }

    @Override
    public void atacarEn(Direccion direccionElegida) {

        Casillero origen = obtenerCasillero();

        Casillero destino = origen.obtenerContiguoEn(direccionElegida);

        destino.fueAtacadoCon(artilleria.lanzarAtaque());
    }

    @Override
    public EspectroEspacial escanearEn(Direccion direccionElegida) {

        return obtenerCasillero().obtenerContiguoEn(direccionElegida).escanear();
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

        ataque.aplicarSobre(this);
    }

    private void fueDestruido() {

        obtenerCasillero().desocupar();
    }

    private Casillero obtenerCasillero() {

        return casillero.orElseThrow(LaNaveNoEstaEnUnCasillero::new);
    }
}
