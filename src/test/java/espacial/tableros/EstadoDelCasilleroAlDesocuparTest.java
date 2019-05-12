package espacial.tableros;

import org.junit.jupiter.api.Test;

public class EstadoDelCasilleroAlDesocuparTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(generaUnDefecto(() -> estado.alDesocupar()));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        estado.alDesocupar();
        
        comprobarQue(cambioElEstadoDelCasilleroPorVacio());
    }
    
    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        estado.alDesocupar();

        comprobarQue(cambioElEstadoDelCasilleroPorVacio());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        estado.alDesocupar();

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBase());
    }

}
