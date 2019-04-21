package espacial.excepciones;

public class ErrorPorqueLaOperacionNoEstaSoportada extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 2570110283419323752L;

    public ErrorPorqueLaOperacionNoEstaSoportada(String operacion) {
        
        super("La operación no está soportada: " + operacion);
    }
}
