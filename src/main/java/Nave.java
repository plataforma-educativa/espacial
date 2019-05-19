import espacial.Direccion;
import espacial.NaveEspacial;

public class Nave {

    private final BatallaEspacial partida;
    private final NaveEspacial pieza;
    
    public Nave() {

        partida = BatallaEspacial.obtener();
        pieza = partida.intervenirCon(this);
    }

    public void despegar() {

        pieza.despegar();
    }

    public void avanzarAlNorte() {
        
        pieza.moverEn(Direccion.NORTE);
    }

    public void avanzarAlSur() {

        pieza.moverEn(Direccion.SUR);
    }

    public void avanzarAlEste() {

        pieza.moverEn(Direccion.ESTE);
    }

    public void avanzarAlOeste() {

        pieza.moverEn(Direccion.OESTE);
    }

    public int consultarNivelDeEscudos() {

        return pieza.obtenerNivelDeEscudos();
    }

    public void atacarAlSur() {

        pieza.atacarEn(Direccion.SUR);
    }

    public void atacarAlOeste() {

        pieza.atacarEn(Direccion.OESTE);
    }
}
