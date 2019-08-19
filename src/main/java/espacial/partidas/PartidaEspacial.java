package espacial.partidas;

import espacial.Coordenadas;
import espacial.Espacial;
import espacial.NaveEspacial;
import espacial.Tablero;
import espacial.excepciones.NoExisteBatallaEspacial;
import espacial.utiles.Opcional;

import java.util.LinkedList;
import java.util.List;

public abstract class PartidaEspacial implements espacial.Partida {

    private static Opcional<PartidaEspacial> instancia = Opcional.sinValor();

    protected final Tablero tablero;
    private final List<Participante> participantes = new LinkedList<>();

    public PartidaEspacial(Tablero unTablero) {

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

    protected NaveEspacial crearNavePara(Participante unParticipante) {

        participantes.add(unParticipante);

        return crearNave();
    }

    protected NaveEspacial crearNavePara(Participante unParticipante, Coordenadas enCoordenadas) {

        participantes.add(unParticipante);

        return crearNave(enCoordenadas);
    }

    protected abstract NaveEspacial crearNave();

    protected NaveEspacial crearNave(Coordenadas coordenadas) {

        return tablero.enCasillero(coordenadas.obtenerFila(), coordenadas.obtenerColumna()).colocarNave();
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return obtenerNombre();
    }

    public void inicializar() {

        Espacial.crearInterfaz(this);
    }

    public abstract String obtenerNombre();

    public static PartidaEspacial obtener() {

        return instancia.obtenerPeroSiNoExisteLanzar(NoExisteBatallaEspacial::new);
    }
}
