package espacial.test;

public abstract class Condicion {

    protected final String descripcion;
    
    protected final Ejecutable ejecutable;

    public Condicion(String descripcion, Ejecutable ejecutable) {

        this.descripcion = descripcion;
        this.ejecutable = ejecutable;
    }
    
    public void ejecutar() {
        
        try {

            ejecutable.ejecutar();
            
        } catch (Throwable throwable) {
            
            throw new AssertionError(describir(throwable), throwable);
        }
    }
    
    protected String describir(Throwable e) {
        
        return new StringBuilder()
                    .append(describir())
                    .append('\n')
                    .append("Detalle: ")
                    .append(e.getMessage())
                    .toString();
    }

    protected String describir() {
        
        return descripcion; 
    }
}