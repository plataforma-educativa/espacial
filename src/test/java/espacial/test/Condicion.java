package espacial.test;

public abstract class Condicion {

    protected Enunciado enunciado;
    
    private final Ejecutable ejecutable;

    public Condicion(Enunciado conEnunciado, Ejecutable conEjecutable) {

        enunciado = conEnunciado;
        ejecutable = conEjecutable;
    }

    public void es(String descripcion, Object... parametros) {

        enunciado = new EnunciadoExplicito(descripcion, parametros);
    }

    protected void ejecutar() {
        
        try {

            ejecutable.ejecutar(this);
            
        } catch (Throwable excepcion) {

            throw crearErrorEnCondicion(excepcion);
        }
    }

    protected abstract ErrorEnCondicion crearErrorEnCondicion(Throwable causa);

    public interface Enunciado {

        String describirFallo(Throwable causa);
    }
}