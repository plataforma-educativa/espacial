import espacial.Direccion;
import espacial.Pieza;

public class Nave {

    private final BatallaEspacial partida;
    private final Pieza pieza;
    
    public Nave() {

        partida = BatallaEspacial.obtener();
        pieza = partida.agregar(this);
    }

    public void avanzarAlNorte() {
        
        pieza.moverEn(Direccion.NORTE);
    }
}
