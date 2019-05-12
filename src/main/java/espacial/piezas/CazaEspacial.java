package espacial.piezas;

import java.util.Optional;

import espacial.Amarre;
import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Obstaculo;
import espacial.PiezaMovil;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;

public class CazaEspacial implements PiezaMovil {

    private int nivelDeEscudos = 100;
    private Optional<Casillero> casillero = Optional.empty();
    private Optional<Amarre> amarre = Optional.empty();

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
        
        Casillero origen = casillero.orElseThrow(LaNaveNoEstaEnUnCasillero::new);
        
        Casillero destino = origen.obtenerContiguoEn(direccionElegida);
        
        origen.moverPiezaA(destino);
    }

    @Override
    public int obtenerNivelDeEscudos() {
        
        return nivelDeEscudos;
    }

    @Override
    public void chocoContraUnAsteroide() {

        nivelDeEscudos -= 25;
    }

    @Override
    public void chocoContraUnContenedor() {

        nivelDeEscudos -= 10;
    }
    
    @Override
    public void chocoContraElBordeDelTablero() {
        
        nivelDeEscudos -= 50;
    }
    
    @Override
    public void chocoContraUnAgujeroNegro() {
        
        nivelDeEscudos -= 75;
    }

    @Override
    public void chocarCon(Obstaculo obstaculo) {
        
        obstaculo.fueChocadaPor(this);
    }

}
