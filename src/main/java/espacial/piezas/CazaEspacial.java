package espacial.piezas;

import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.PiezaMovil;

public class CazaEspacial implements PiezaMovil {

    private Casillero casillero;
    
    @Override
    public void fueColocadaEn(Casillero casillero) {
        
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
}
