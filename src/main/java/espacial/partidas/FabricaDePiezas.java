package espacial.partidas;

import espacial.Pieza;
import espacial.piezas.Asteroide;
import espacial.piezas.ContenedorDeAntimateria;
import espacial.piezas.ContenedorDeCristal;
import espacial.piezas.ContenedorDeMetal;
import espacial.utiles.Aleatorio;

public class FabricaDePiezas {

    private Aleatorio cargaAleatoria;
    private Aleatorio durezaAleatoria;

    public static FabricaDePiezas crear() {

        return new FabricaDePiezas(new Aleatorio(1, 250),
                                   new Aleatorio(5, 100));
    }

    private FabricaDePiezas(Aleatorio cargaAleatoriaDeContenedores, Aleatorio durezaAleatoriaDeAsteroides) {

        cargaAleatoria = cargaAleatoriaDeContenedores;
        durezaAleatoria = durezaAleatoriaDeAsteroides;
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

    public Pieza crearAsteoride() {

        return new Asteroide(durezaAleatoria.obtener());
    }
}
