package espacial.test;

import org.junit.jupiter.api.DisplayNameGeneration;

@DisplayNameGeneration(EstrategiaParaNombrarTestDeContrato.class)
public interface TestDeContrato {

    default void dadoQue(Precondicion precondicion) {
        
        precondicion.ejecutar();
    }
    
    default void comprobarQue(Postcondicion postcondicion) {
        
        postcondicion.ejecutar();
    }
    
    default Precondicion precondicion(String descripcion, Ejecutable ejecutable) {
        
        return new Precondicion(new EnunciadoExplicito(descripcion), ejecutable);
    }

    default Precondicion precondicion(Ejecutable ejecutable) {

        return new Precondicion(new EnunciadoImplicito(), ejecutable);
    }

    default Postcondicion postcondicion(String descripcion, Ejecutable ejecutable) {
        
        return new Postcondicion(new EnunciadoExplicito(descripcion), ejecutable);
    }

    default Postcondicion postcondicion(Ejecutable ejecutable) {


        return new Postcondicion(new EnunciadoImplicito(), ejecutable);
    }
}
