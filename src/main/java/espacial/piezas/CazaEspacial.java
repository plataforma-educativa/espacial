package espacial.piezas;

import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;
import espacial.tableros.CasilleroInterior;

public class CazaEspacial implements PiezaMovil {

    private int nivelDeEscudos = 100;
    private CasilleroInterior casillero;
    
    @Override
    public void fueColocadaEn(CasilleroInterior casillero) {
        
        this.casillero = casillero;
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.NAVE;
    }

    @Override
    public void moverEn(Direccion direccionElegida) {
        
        Casillero destino = casillero.obtenerContiguoEn(direccionElegida);
        
        casillero.moverPiezaA(destino);
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
    public void chocarCon(Pieza pieza) {
        
        pieza.fueChocadaPor(this);
    }
}
