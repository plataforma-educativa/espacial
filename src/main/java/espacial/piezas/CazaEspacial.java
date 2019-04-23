package espacial.piezas;

import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.PiezaMovil;
import espacial.tableros.CasilleroInterior;

public class CazaEspacial implements PiezaMovil {

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
        
        if (destino.estaDesocupado()) {
            
            casillero.moverPiezaA(destino);
        }
    }
}
