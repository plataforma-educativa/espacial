package espacial.partidas;

import espacial.NaveEspacial;
import espacial.Tablero;
import espacial.excepciones.NoExisteBatallaEspacial;
import espacial.utiles.AccionSobre;
import espacial.utiles.Opcional;

import java.util.LinkedList;
import java.util.List;

public abstract class EscenarioEspacial {

    private static Opcional<EscenarioEspacial> instancia = Opcional.sinValor();

    private final Tablero tablero;
    private final List<Participante> participantes = new LinkedList<>();
    private AccionSobre<NaveEspacial> alCrearNaveEspacial = AccionSobre.ninguna();

    public EscenarioEspacial(Tablero usandoTablero) {

        instancia = Opcional.con(this);
        tablero = usandoTablero;
    }

    public static EscenarioEspacial obtener() {

        return instancia.obtenerPeroSiNoExisteLanzar(NoExisteBatallaEspacial::new);
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

        alCrearNaveEspacial.ejecutar(naveEspacial);

        return naveEspacial;
    }

    protected void cuandoCrearNaveEspacial(AccionSobre<NaveEspacial> accion) {

        alCrearNaveEspacial = accion;
    }

    public abstract String obtenerNombre();

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return obtenerNombre();
    }
}
