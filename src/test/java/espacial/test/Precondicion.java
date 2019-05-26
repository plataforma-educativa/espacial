package espacial.test;

public class Precondicion extends Condicion {

    protected Precondicion(Enunciado enunciado, Ejecutable ejecutable) {
        
        super(enunciado, ejecutable);
    }

    @Override
    protected ErrorEnCondicion crearErrorEnCondicion(Throwable causa) {

        return new NoFuePosibleEstablecerQue(enunciado, causa);
    }

    private static class NoFuePosibleEstablecerQue extends ErrorEnCondicion {

        private static final long serialVersionUID = 4734265336999502233L;

        public NoFuePosibleEstablecerQue(Enunciado enunciado, Throwable causa) {

            super(enunciado, causa);
        }
    }
}
