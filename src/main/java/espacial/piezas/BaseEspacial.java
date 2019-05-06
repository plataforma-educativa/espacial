package espacial.piezas;

import java.util.LinkedList;
import java.util.List;

import espacial.Amarre;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;

public class BaseEspacial implements Pieza {

    private final List<Amarre> amarres;
    
    public BaseEspacial() {

        amarres = new LinkedList<>();
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }

    public void amarrar(PiezaMovil pieza) {
        
        amarres.add(new AmarreConBaseEspacial(pieza));
    }

    public Amarre[] obtenerAmarres() {

        return amarres.toArray(new Amarre[amarres.size()]);
    }
    
    public class AmarreConBaseEspacial implements Amarre {
        
        private final PiezaMovil pieza;
        
        private AmarreConBaseEspacial(PiezaMovil unaPieza) {
            
            pieza = unaPieza;
        }
        
        public PiezaMovil obtenerPieza() {
            
            return pieza;
        }
        
        @Override
        public void soltar() {

            amarres.remove(this);
        }
    }
}
