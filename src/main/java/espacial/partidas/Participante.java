package espacial.partidas;

import espacial.NaveEspacial;

public abstract class Participante {

    private final PartidaEspacial partida;

    public Participante() {

        partida = PartidaEspacial.obtener();
    }

    protected NaveEspacial crearNaveEspacial() {

        return partida.crearNavePara(this);
    }
}
