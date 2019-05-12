package espacial.tableros;

import org.junit.jupiter.api.Test;

public class EstadoDelCasilleroAlOcuparConTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);

        estado.alOcuparCon(PIEZA);
        
        comprobarQue(cambioElEstadoDelCasilleroPorOcupado());
    }

    @Test
    public void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        comprobarQue(generaUnDefecto(() -> estado.alOcuparCon(OTRA_PIEZA)));

        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alOcuparCon(NAVE);
        
        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        estado.alOcuparCon(OTRA_PIEZA);
        
        comprobarQue(generaUnChoqueEntre(OTRA_PIEZA, NAVE));
    }
}
