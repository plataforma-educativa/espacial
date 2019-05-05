package espacial.piezas;

import java.util.LinkedList;
import java.util.List;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;

public class BaseEspacial implements Pieza {

    private final List<PiezaMovil> amarres;
    
    public BaseEspacial() {

        amarres = new LinkedList<>();
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }

    public void amarrar(PiezaMovil pieza) {
        
        amarres.add(pieza);
    }

    public PiezaMovil[] obtenerAmarres() {

        return amarres.toArray(new PiezaMovil[amarres.size()]);
    }
}
