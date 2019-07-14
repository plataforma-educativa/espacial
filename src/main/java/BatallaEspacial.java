import espacial.NaveEspacial;
import espacial.Tablero;
import espacial.excepciones.NoExisteBatallaEspacial;
import espacial.partidas.Nomenclador;
import espacial.piezas.BaseEspacial;
import espacial.piezas.CazaEspacial;
import espacial.utiles.Opcional;

import java.util.LinkedList;
import java.util.List;

public class BatallaEspacial {

    private static Opcional<BatallaEspacial> instancia = Opcional.sinValor();

    private final Nomenclador nomenclador = new Nomenclador();

    private final BaseEspacial base;
    
    private final List<Nave> naves = new LinkedList<>();
    
    private final Tablero tablero = new Tablero();

    public BatallaEspacial() {

        instancia = Opcional.con(this);
        base = new BaseEspacial();
        tablero.colocarEnCasillero(0, 0, base);
    }

    public static BatallaEspacial obtener() {

        return instancia.obtenerPeroSiNoExisteLanzar(NoExisteBatallaEspacial::new);
    }

    public Nave[] obtenerNaves() {

        return naves.toArray(new Nave[0]);
    }

    public Tablero obtenerTablero() {

        return tablero;
    }

    public NaveEspacial intervenirCon(Nave unaNave) {

        naves.add(unaNave);

        CazaEspacial cazaEspacial = new CazaEspacial(nomenclador.nombrarNave());

        base.amarrar(cazaEspacial);

        return cazaEspacial;
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Batalla Espacial";
    }
}
