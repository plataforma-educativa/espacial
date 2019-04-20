import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import espacial.Tablero;
import espacial.excepciones.ErrorPorqueNoExisteBatallaEspacial;
import espacial.piezas.CazaEspacial;
import espacial.piezas.Pieza;

public class BatallaEspacial {

    private static Optional<BatallaEspacial> instancia = Optional.empty();
    
    private List<Nave> naves = new LinkedList<>();
    
    private Tablero tablero = new Tablero();
    
    public BatallaEspacial() {

        instancia = Optional.of(this);
    }

    public static BatallaEspacial obtener() {

        return instancia.orElseThrow(ErrorPorqueNoExisteBatallaEspacial::new);
    }

    public Nave[] obtenerNaves() {

        return naves.toArray(new Nave[naves.size()]);
    }

    public void agregar(Nave nave) {
        
        naves.add(nave);
        
        Pieza pieza = new CazaEspacial();
        
        tablero.obtenerCasillero(0, 0).colocar(pieza);
    }

    public Tablero obtenerTablero() {

        return tablero;
    }
}
