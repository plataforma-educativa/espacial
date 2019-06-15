package espacial.partidas;

import espacial.Pieza;
import espacial.piezas.ContenedorDeAntimateria;
import espacial.piezas.ContenedorDeCristal;
import espacial.piezas.ContenedorDeMetal;

public class FabricaDePiezas {

    public Pieza crearContenedorDeAntimateria() {

        return new ContenedorDeAntimateria(1);
    }

    public Pieza crearContenedorDeMetal() {

        return new ContenedorDeMetal(1);
    }

    public Pieza crearContenedorDeCristal() {

        return new ContenedorDeCristal(1);
    }
}
