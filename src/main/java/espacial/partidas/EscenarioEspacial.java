package espacial.partidas;

import espacial.NaveEspacial;
import espacial.PartidaEspacial;
import espacial.Tablero;
import espacial.excepciones.NoExisteBatallaEspacial;
import espacial.utiles.Opcional;

import java.util.LinkedList;
import java.util.List;

public abstract class EscenarioEspacial {

    private static Opcional<EscenarioEspacial> instancia = Opcional.sinValor();

    private final Tablero tablero;
    private final List<Participante> participantes = new LinkedList<>();

    public EscenarioEspacial(Tablero unTablero) {

        tablero = unTablero;
        instancia = Opcional.con(this);
        inicializar();
    }

    public Tablero obtenerTablero() {

        return tablero;
    }

    public Participante[] obtenerParticipantes() {

        return participantes.toArray(new Participante[0]);
    }

    protected NaveEspacial crearNaveEspacialPara(Participante unParticipante) {

        participantes.add(unParticipante);

        NaveEspacial naveEspacial = tablero.crearNave();

        return naveEspacial;
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return obtenerNombre();
    }

    public void inicializar() {

        PartidaEspacial.iniciar(this);
    }

    public abstract String obtenerNombre();

    public static EscenarioEspacial obtener() {

        return instancia.obtenerPeroSiNoExisteLanzar(NoExisteBatallaEspacial::new);
    }
}
