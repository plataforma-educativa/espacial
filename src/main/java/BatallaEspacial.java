import java.util.LinkedList;
import java.util.List;

import espacial.Tablero;

public class BatallaEspacial {

    private static BatallaEspacial instancia = null;
    
    private List<Nave> naves = new LinkedList<>();
    
    private Tablero tablero = new Tablero();
    
    public BatallaEspacial() {

        instancia = this;
    }

    public static BatallaEspacial obtener() {

        return instancia;
    }

    public Nave[] obtenerNaves() {

        return naves.toArray(new Nave[naves.size()]);
    }

    public void agregar(Nave nave) {
        
        naves.add(nave);
    }

    public Tablero obtenerTablero() {

        return tablero;
    }
}
