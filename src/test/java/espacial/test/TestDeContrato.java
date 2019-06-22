package espacial.test;

import org.junit.jupiter.api.DisplayNameGeneration;

@DisplayNameGeneration(NombrarTestDeContrato.class)
public interface TestDeContrato {

    default void dadoQue(Precondicion precondicion) {
        
        precondicion.ejecutar();
    }
    
    default void comprobarQue(Postcondicion postcondicion) {
        
        postcondicion.ejecutar();
    }
    
    default Precondicion pre(String descripcion, Ejecutable ejecutable) {
        
        return new Precondicion(new EnunciadoExplicito(descripcion), ejecutable);
    }

    default Precondicion pre(Ejecutable ejecutable) {

        return new Precondicion(new EnunciadoImplicito(), ejecutable);
    }

    default Postcondicion post(String descripcion, Ejecutable ejecutable) {
        
        return new Postcondicion(new EnunciadoExplicito(descripcion), ejecutable);
    }

    default Postcondicion post(Ejecutable ejecutable) {

        return new Postcondicion(new EnunciadoImplicito(), ejecutable);
    }

    default void repetir(int veces, Paso paso) {

        for (int i = 0; i < veces; i++) {

            paso.dar(i);
        }
    }
}
