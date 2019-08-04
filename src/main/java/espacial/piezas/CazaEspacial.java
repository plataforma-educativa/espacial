package espacial.piezas;

import espacial.Amarre;
import espacial.Carga;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Visitante;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.piezas.rasgos.NaveChocable;
import espacial.piezas.rasgos.NaveDeCarga;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.utiles.Nombre;
import espacial.utiles.Referencia;

public class CazaEspacial implements NaveEspacial, NaveChocable, NaveDeCarga, PiezaAtacable {

    private final Nombre nombre;
    private final Indicador nivelDeEscudos = new Indicador(100);
    private final Artilleria artilleria = new Artilleria(100);
    private final Bodega bodega = new Bodega(obtenerCapacidad());
    private final Referencia<Amarre> amarre = Referencia.conValorNulo();
    private final Referencia<Casillero> casillero = Referencia.conValorNulo();
    private final Observadores observadores = new Observadores();
    private final Rumbo rumbo = new Rumbo();

    public CazaEspacial() {

        this(Nombre.sera("Algoritmico I").porque("Nombre por defecto").obtener());
    }

    public CazaEspacial(Nombre unNombre) {

        nombre = unNombre;
        nivelDeEscudos.cuandoSeAgota(this::fueDestruido);
        artilleria.cuandoCambianLasMuniciones(this::notificarQueCambioElEstado);
        bodega.cuandoCambiaLaCarga(this::notificarQueCambioElEstado);
        rumbo.cuandoCambia(this::notificarQueCambioElEstado);
        amarre.siEsNuloAlObtener(this::lanzarExcepcionPorqueLaNaveNoEstaEnLaBase);
        casillero.siEsNuloAlObtener(this::lanzarExcepcionPorqueNoDespego);
    }

    @Override
    public void despegar() {

        amarre.obtener().soltar();

        amarre.anular();
    }

    @Override
    public void fueColocadaEn(Casillero unCasillero) {
        
        casillero.cambiar(unCasillero);

        notificarQueFueMovidaA(unCasillero);
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

        rumbo.tomar(direccionElegida);

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

        notificarQueCambioElEstado();
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

        rumbo.tomar(direccionElegida);

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

    private void fueDestruido() {

        obtenerCasillero().desocupar();

        casillero.anular();
        casillero.siEsNuloAlObtener(this::lanzarExcepcionPorqueFueDestruida);

        notificarQueFueDestruido();
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

    private void notificarQueCambioElEstado() {

        observadores.cambioElEstadoDe(this);
    }

    private void notificarQueFueMovidaA(Casillero unCasillero) {

        observadores.fueMovida(this, unCasillero);
    }

    private void notificarQueFueDestruido() {

        observadores.fueDestruida(this);
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
    public void descargarEn(Direccion direccionElegida, Carga unaCarga) {

        Casillero casilleroDestino = obtenerCasillero().obtenerContiguoEn(direccionElegida);

        entregar(unaCarga);

        casilleroDestino.recibir(unaCarga);
    }

    @Override
    public int obtenerNivelDeCarga() {

        return bodega.obtenerNivelDeCarga();
    }

    @Override
    public String toString() {

        return describir();
    }

    @Override
    public Nombre nombrar() {

        return nombre;
    }

    @Override
    public void registrar(Pieza.Observador unObservador) {

        observadores.agregar(unObservador);
    }

    public Direccion obtenerRumbo() {

        return rumbo.obtener();
    }
}
