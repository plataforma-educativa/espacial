package espacial.test;

public abstract class Condicion {

    protected final Enunciado enunciado;
    
    private final Ejecutable ejecutable;

    public Condicion(Enunciado enunciado, Ejecutable ejecutable) {

        this.enunciado = enunciado;
        this.ejecutable = ejecutable;
    }

    public void ejecutar() {
        
        try {

            ejecutable.ejecutar();
            
        } catch (Throwable excepcion) {

            throw crearErrorEnCondicion(excepcion);
        }
    }

    protected abstract ErrorEnCondicion crearErrorEnCondicion(Throwable causa);

    public interface Enunciado {

        String describirFallo(Throwable causa);
    }
}