package espacial.partidas;

import espacial.Coordenadas;
import espacial.NaveEspacial;

public abstract class Participante {

    private final PartidaEspacial partida;

    public Participante() {

        partida = PartidaEspacial.obtener();
    }

    protected NaveEspacial crearNaveEspacial() {

        return partida.crearNavePara(this);
    }

    protected NaveEspacial crearNaveEspacial(Coordenadas enCoordenadas) {

        return partida.crearNavePara(this, enCoordenadas);
    }

}
