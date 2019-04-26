package espacial.tableros;

import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Tablero;
import espacial.excepciones.LaOperacionNoEstaSoportada;

public class CasilleroBorde implements Casillero {

    public CasilleroBorde(Tablero contenedor) {
        
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
        
        throw new LaOperacionNoEstaSoportada("CasilleroBorde.moverPiezaA(Casillero)");
    }
    
    @Override
    public void ocuparCon(Pieza unaPieza) {
        
        throw new LaOperacionNoEstaSoportada("CasilleroBorde.ocuparCon(Pieza)");
    }

    @Override
    public void desocupar() {

        throw new LaOperacionNoEstaSoportada("CasilleroBorde.desocupar()");
    }

    @Override
    public boolean estaOcupado() {
    
        return false;
    }

    @Override
    public void chocarPiezaCon(Pieza otraPieza) {
        
    }
}