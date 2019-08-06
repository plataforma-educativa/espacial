package espacial;

public interface Partidario {

    void evaluar(Condicional condicional);

    interface Condicional {

        default void siEsNeutral() {

        }

        default void siEsAliado() {

        }

        default void siEsRival() {

        }
    }
}
