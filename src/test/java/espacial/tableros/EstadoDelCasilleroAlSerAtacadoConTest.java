package espacial.tableros;

import espacial.test.Postcondicion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlSerAtacadoConTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(fueAtacadaLaPieza());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(fueAtacadaLaPieza());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(fueAtacadaLaNave());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion fueAtacadaLaPieza() {

        return post(condicion ->  verify(PIEZA).fueAtacadoCon(UN_ATAQUE));
    }

    private Postcondicion fueAtacadaLaNave() {

        return post(condicion ->  verify(NAVE).fueAtacadoCon(UN_ATAQUE));
    }
}
