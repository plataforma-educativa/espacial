package espacial.test;

public class Postcondicion extends Condicion {

    protected Postcondicion(Enunciado enunciado, Ejecutable ejecutable) {

        super(enunciado, ejecutable);
    }

    @Override
    protected ErrorEnCondicion crearErrorEnCondicion(Throwable causa) {

        return new NoFuePosibleComprobarQue(enunciado, causa);
    }

    private static class NoFuePosibleComprobarQue extends ErrorEnCondicion {

        private static final long serialVersionUID = 4969223836927502213L;

        public NoFuePosibleComprobarQue(Enunciado enunciado, Throwable causa) {

            super(enunciado, causa);
        }
    }
}