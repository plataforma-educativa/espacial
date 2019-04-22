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
}
