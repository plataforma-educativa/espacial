import espacial.Direccion;
import espacial.PiezaMovil;

public class Nave {

    private final BatallaEspacial partida;
    private final PiezaMovil pieza;
    
    public Nave() {

        partida = BatallaEspacial.obtener();
        pieza = partida.agregar(this);
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
}
