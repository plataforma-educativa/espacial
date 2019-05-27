import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import espacial.NaveEspacial;
import espacial.Tablero;
import espacial.excepciones.NoExisteBatallaEspacial;
import espacial.piezas.BaseEspacial;
import espacial.piezas.CazaEspacial;

public class BatallaEspacial {

    private static Optional<BatallaEspacial> instancia = Optional.empty();
    
    private BaseEspacial base;
    
    private List<Nave> naves = new LinkedList<>();
    
    private Tablero tablero = new Tablero();
    
    public BatallaEspacial() {

        instancia = Optional.of(this);
        base = new BaseEspacial();
        tablero.colocarEnCasillero(0, 0, base);
    }

    public static BatallaEspacial obtener() {

        return instancia.orElseThrow(NoExisteBatallaEspacial::new);
    }

    public Nave[] obtenerNaves() {

        return naves.toArray(new Nave[naves.size()]);
    }

    public Tablero obtenerTablero() {

        return tablero;
    }

    public NaveEspacial intervenirCon(Nave unaNave) {

        naves.add(unaNave);

        CazaEspacial cazaEspacial = new CazaEspacial();

        base.amarrar(cazaEspacial);

        return cazaEspacial;
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Batalla Espacial";
    }
}
