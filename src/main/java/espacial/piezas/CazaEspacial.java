package espacial.piezas;

import espacial.Casillero;
import espacial.Coordenada;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;

public class CazaEspacial implements Pieza {

    private Casillero casillero;
    private Coordenada posicion;

    @Override
    public void fueColocadaEn(Casillero casillero) {
        
        this.casillero = casillero;
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.NAVE;
    }

    @Override
    public void posicionar(Coordenada coordenada) {
        
        this.posicion = coordenada;
    }
    
    @Override
    public Coordenada obtenerPosicion() {
        
        return posicion;
    }
    
    @Override
    public void moverEn(Direccion direccion) {
        
        Casillero destino = casillero.obtenerContiguoAl(direccion);
        
        casillero.moverPiezaA(destino);
    }
}
