package espacial.test;

public interface Prueba {

    default void dadoQue(Precondicion precondicion) {
        
        precondicion.ejecutar();
    }
    
    default void comprobarQue(Postcondicion postcondicion) {
        
        postcondicion.ejecutar();
    }
    
    default Precondicion precondicion(String descripcion, Ejecutable ejecutable) {
        
        return new Precondicion(descripcion, ejecutable);
    }

    default Postcondicion postcondicion(String descripcion, Ejecutable ejecutable) {
        
        return new Postcondicion(descripcion, ejecutable);
    }
}
