package espacial.partidas;

import espacial.Pieza;
import espacial.piezas.ContenedorDeAntimateria;
import espacial.piezas.ContenedorDeCristal;
import espacial.piezas.ContenedorDeMetal;
import espacial.utiles.Aleatorio;

public class FabricaDePiezas {

    private Aleatorio cargaAleatoria;

    public static FabricaDePiezas crear() {

        return new FabricaDePiezas(new Aleatorio(1, 300));
    }

    public FabricaDePiezas(Aleatorio cargaAleatoriaDeContenedores) {

        cargaAleatoria = cargaAleatoriaDeContenedores;
    }

    public Pieza crearContenedorDeAntimateria() {

        return new ContenedorDeAntimateria(cargaAleatoria.obtener());
    }

    public Pieza crearContenedorDeMetal() {

        return new ContenedorDeMetal(cargaAleatoria.obtener());
    }

    public Pieza crearContenedorDeCristal() {

        return new ContenedorDeCristal(cargaAleatoria.obtener());
    }
}
