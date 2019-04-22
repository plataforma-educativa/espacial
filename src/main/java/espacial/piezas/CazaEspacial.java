package espacial.piezas;

import espacial.Coordenada;
import espacial.EspectroEspacial;
import espacial.Pieza;

public class CazaEspacial implements Pieza {

    private Coordenada posicion;

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
}
