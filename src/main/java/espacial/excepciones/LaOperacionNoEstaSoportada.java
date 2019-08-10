package espacial.excepciones;

public class LaOperacionNoEstaSoportada extends ErrorEspacial {

    private static final long serialVersionUID = 2570110283419323752L;

    public LaOperacionNoEstaSoportada(String operacion) {
        
        super("La operación no está soportada: %s", operacion);
    }
}
