package espacial;

import espacial.utiles.Accion;

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

    static Condicional siEsAliado(Accion accion) {

        return new Condicional() {

            @Override
            public void siEsAliado() {

                accion.ejecutar();
            }
        };
    }

    static Condicional siEsNeutral(Accion accion) {

        return new Condicional() {

            @Override
            public void siEsNeutral() {

                accion.ejecutar();
            }
        };
    }

    static Condicional siEsRival(Accion accion) {

        return new Condicional() {

            @Override
            public void siEsRival() {

                accion.ejecutar();
            }
        };
    }
}
