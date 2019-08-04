package espacial;

import espacial.utiles.AccionSobre;

public enum Faccion {

    NEUTRAL(Condicional::siEsNeutral),
    ALIADO(Condicional::siEsAliado),
    RIVAL(Condicional::siEsRival);

    private final AccionSobre<Condicional> alEvaluar;

    Faccion(AccionSobre<Condicional> accionAlEvaluar) {

        alEvaluar = accionAlEvaluar;
    }

    public void evaluar(Condicional condicional) {

        alEvaluar.ejecutar(condicional);
    }

    public interface Condicional {

        default void siEsNeutral() {

        }

        default void siEsAliado() {

        }

        default void siEsRival() {

        }
    }
}
