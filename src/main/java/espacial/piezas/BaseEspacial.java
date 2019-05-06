package espacial.piezas;

import java.util.LinkedList;
import java.util.List;

import espacial.Amarre;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;

public class BaseEspacial implements Pieza {

    private Casillero casillero;
    private final List<Amarre> amarres;
    
    public BaseEspacial() {

        amarres = new LinkedList<>();
    }
    
    @Override
    public void fueColocadaEn(Casillero casillero) {
     
        this.casillero = casillero;
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }

    public void amarrar(PiezaMovil pieza) {
        
        Amarre amarre = new AmarreConBaseEspacial(pieza);
        amarres.add(amarre);
        pieza.fueAmarradaCon(amarre);
    }

    public Amarre[] obtenerAmarres() {

        return amarres.toArray(new Amarre[amarres.size()]);
    }
    
    private class AmarreConBaseEspacial implements Amarre {
        
        private final PiezaMovil pieza;
        
        private AmarreConBaseEspacial(PiezaMovil unaPieza) {
            
            pieza = unaPieza;
        }
        
        public PiezaMovil obtenerPieza() {
            
            return pieza;
        }
        
        @Override
        public void soltar() {

            if (amarres.remove(this)) {
                
                pieza.fueColocadaEn(casillero);
            }
        }
    }
}
