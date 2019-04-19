import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import espacial.Tablero;
import espacial.excepciones.ErrorPorqueNoExisteBatallaEspacial;

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
    }

    public Tablero obtenerTablero() {

        return tablero;
    }
}
