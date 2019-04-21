package espacial.excepciones;

public class LaOperacionNoEstaSoportada extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 2570110283419323752L;

    public LaOperacionNoEstaSoportada(String operacion) {
        
        super("La operación no está soportada: " + operacion);
    }
}
