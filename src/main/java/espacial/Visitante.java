package espacial;

public interface Visitante {

    default void siEsBase(BaseEspacial pieza) {

    }

    default void siEsNave(NaveEspacial pieza) {

    }

    default void siEsContenedor(Pieza pieza) {

    }

    default void siEsAsteroide(Pieza pieza) {

    }

    default void siEsAgujeroNegro(Pieza pieza) {

    }
}
