package espacial.partidas;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.piezas.AgujeroNegro;
import espacial.piezas.Asteroide;
import espacial.piezas.BaseDesconocida;
import espacial.piezas.CazaEspacial;
import espacial.piezas.ContenedorDeAntimateria;
import espacial.piezas.ContenedorDeCristal;
import espacial.piezas.ContenedorDeMetal;
import espacial.piezas.EstacionCentral;
import espacial.utiles.Aleatorio;

public class FabricaDePiezas {

    private final Nomenclador nomenclador = new Nomenclador();
    private final Aleatorio<Integer> cargaAleatoria;
    private final Aleatorio<Integer> durezaAleatoria;
    private final Aleatorio<SustanciaEspacial> sustanciaEspacialAleatoria;

    public static FabricaDePiezas crear() {

        return new FabricaDePiezas(
                Aleatorio.enRango(1, 250),
                Aleatorio.enRango(5, 100),
                Aleatorio.enLista(SustanciaEspacial.values())
        );
    }

    private FabricaDePiezas(Aleatorio<Integer> cargaAleatoriaDeContenedores,
                            Aleatorio<Integer> durezaAleatoriaDeAsteroides,
                            Aleatorio<SustanciaEspacial> sustanciaEspacialAleatoriaBaseDesconocida) {

        cargaAleatoria = cargaAleatoriaDeContenedores;
        durezaAleatoria = durezaAleatoriaDeAsteroides;
        sustanciaEspacialAleatoria = sustanciaEspacialAleatoriaBaseDesconocida;
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

    public Pieza crearAgujeroNegro() {

        return new AgujeroNegro();
    }

    public NaveEspacial crearNaveEspacial() {

        return new CazaEspacial(nomenclador.nombrarNave());
    }

    public BaseEspacial crearBaseEspacial() {

        return new EstacionCentral();
    }

    public Pieza crearBaseDesconocida() {

        BaseDesconocida base = new BaseDesconocida();

        base.recibir(sustanciaEspacialAleatoria.obtener().por(cargaAleatoria.obtener()));
        base.recibir(sustanciaEspacialAleatoria.obtener().por(cargaAleatoria.obtener()));

        return base;
    }
}
