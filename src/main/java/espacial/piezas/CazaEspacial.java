package espacial.piezas;

import espacial.Amarre;
import espacial.Ataque;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;
import espacial.Visitante;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.piezas.rasgos.DepositoDeAntimateria;
import espacial.piezas.rasgos.DepositoDeCristal;
import espacial.piezas.rasgos.DepositoDeMetal;
import espacial.piezas.rasgos.NaveChocable;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.piezas.rasgos.PiezaDeposito;
import espacial.utiles.Opcional;

public class CazaEspacial implements NaveEspacial, NaveChocable, PiezaAtacable, PiezaDeposito,
        DepositoDeAntimateria, DepositoDeMetal, DepositoDeCristal {

    private final static int CAPACIDAD = 100;

    private final Indicador nivelDeEscudos = new Indicador(100);
    private final Artilleria artilleria = new Artilleria(100);
    private final Cargamento antimateria = new Cargamento(CAPACIDAD);
    private final Cargamento metal = new Cargamento(CAPACIDAD);
    private final Cargamento cristal = new Cargamento(CAPACIDAD);
    private Opcional<Casillero> casillero = Opcional.sinValor();
    private Opcional<Amarre> amarre = Opcional.sinValor();

    public CazaEspacial() {

        nivelDeEscudos.cuandoSeAgota(this::fueDestruido);
    }

    @Override
    public void despegar() {

        Amarre amarreActual = amarre.obtenerPeroSiNoExisteLanzar(LaNaveNoEstaEnLaBase::new);

        amarreActual.soltar();
    }

    @Override
    public void fueColocadaEn(Casillero unCasillero) {
        
        casillero = Opcional.con(unCasillero);
    }
    
    @Override
    public void fueAmarradaCon(Amarre unAmarre) {
    
        amarre = Opcional.con(unAmarre);
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
    }

    private Casillero obtenerCasillero() {

        return casillero.obtenerPeroSiNoExisteLanzar(LaNaveNoEstaEnUnCasillero::new);
    }

    @Override
    public Cargamento obtenerAntimateria() {

        return antimateria;
    }

    @Override
    public Cargamento obtenerMetal() {

        return metal;
    }

    @Override
    public Cargamento obtenerCristal() {

        return cristal;
    }
}
