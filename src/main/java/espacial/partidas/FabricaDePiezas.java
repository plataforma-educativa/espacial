package espacial.partidas;

import espacial.Pieza;
import espacial.piezas.Asteroide;
import espacial.piezas.ContenedorDeAntimateria;
import espacial.piezas.ContenedorDeCristal;
import espacial.piezas.ContenedorDeMetal;
import espacial.utiles.Aleatorio;

public class FabricaDePiezas {

    private Aleatorio<Integer> cargaAleatoria;
    private Aleatorio<Integer> durezaAleatoria;

    public static FabricaDePiezas crear() {

        return new FabricaDePiezas(Aleatorio.enRango(1, 250),
                                   Aleatorio.enRango(5, 100));
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
