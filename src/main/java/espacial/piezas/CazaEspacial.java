package espacial.piezas;

import espacial.Amarre;
import espacial.Ataque;
import espacial.Carga;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;
import espacial.Visitante;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.piezas.rasgos.NaveChocable;
import espacial.piezas.rasgos.NaveDeCarga;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.utiles.Referencia;

public class CazaEspacial implements NaveEspacial, NaveChocable, NaveDeCarga, PiezaAtacable {

    private final Indicador nivelDeEscudos = new Indicador(100);
    private final Artilleria artilleria = new Artilleria(100);
    private final Bodega bodega = new Bodega(obtenerCapacidad());
    private final Referencia<Amarre> amarre = Referencia.conValorNulo();
    private final Referencia<Casillero> casillero = Referencia.conValorNulo();

    public CazaEspacial() {

        nivelDeEscudos.cuandoSeAgota(this::fueDestruido);
        amarre.siEsNuloAlObtener(this::lanzarExcepcionPorqueLaNaveNoEstaEnLaBase);
        casillero.siEsNuloAlObtener(this::lanzarExcepcionPorqueNoDespego);
    }

    @Override
    public void despegar() {

        Amarre amarreActual = amarre.obtener();

        amarreActual.soltar();
    }

    @Override
    public void fueColocadaEn(Casillero unCasillero) {
        
        casillero.cambiar(unCasillero);
    }
    
    @Override
    public void fueAmarradaCon(Amarre unAmarre) {
    
        amarre.cambiar(unAmarre);
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
    public int buscarEn(Direccion direccionElegida, SustanciaEspacial unaSustancia) {

        return obtenerCasillero().obtenerContiguoEn(direccionElegida).buscar(unaSustancia);
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

        ataque.aplicarSobre(this);
    }

    private void fueDestruido() {

        obtenerCasillero().desocupar();

        casillero.anular();
        casillero.siEsNuloAlObtener(this::lanzarExcepcionPorqueFueDestruida);
    }

    private Amarre lanzarExcepcionPorqueLaNaveNoEstaEnLaBase() {

        throw new LaNaveNoEstaEnLaBase();
    }

    private Casillero lanzarExcepcionPorqueNoDespego() {

        throw new LaNaveNoEstaEnUnCasillero("porque no despegó");
    }

    private Casillero lanzarExcepcionPorqueFueDestruida() {

        throw new LaNaveNoEstaEnUnCasillero("porque fue destruida");
    }

    private Casillero obtenerCasillero() {

        return casillero.obtener();
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
    public void cargarDesde(Direccion direccionElegida, Carga unaCarga) {

        Casillero casilleroOrigen = obtenerCasillero().obtenerContiguoEn(direccionElegida);

        casilleroOrigen.entregar(unaCarga);

        recibir(unaCarga);
    }

    @Override
    public int obtenerNivelDeCarga() {

        return bodega.obtenerNivelDeCarga();
    }
}
