package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;

public class Borde extends Casillero {

    public Borde(Tablero contenedor) {
        
        super(contenedor, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.DESCONOCIDO;
    }
    
    @Override
    public Casillero obtenerContiguoEn(Direccion direccion) {
        
        return this;
    }
    
    @Override
    public void moverPiezaA(Casillero destino) {
        
        throw new LaOperacionNoEstaSoportada("Borde.moverPiezaA(Casillero)");
    }
    
    @Override
    public void ocuparCon(Pieza unaPieza) {
        
        throw new LaOperacionNoEstaSoportada("Borde.ocuparCon(Pieza)");
    }
}