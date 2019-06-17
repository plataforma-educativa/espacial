package espacial.tableros;

import org.junit.jupiter.api.Test;

class EstadoDelCasilleroAlDesocuparTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(generaUnDefecto(() -> estado.alDesocupar()));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        estado.alDesocupar();
        
        comprobarQue(cambioElEstadoDelCasilleroPorVacio());
    }
    
    @Test
    void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        estado.alDesocupar();

        comprobarQue(cambioElEstadoDelCasilleroPorVacio());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        estado.alDesocupar();

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBase());
    }

}
