package espacial.tableros;

import static org.mockito.Mockito.*;
import espacial.test.Postcondicion;
import org.junit.jupiter.api.Test;

public class EstadoDelCasilleroAlSerAtacadoConTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(fueAtacadaLaPieza());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(fueAtacadaLaPieza());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alSerAtacadoCon(UN_ATAQUE);

        comprobarQue(fueAtacadaLaNave());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion fueAtacadaLaPieza() {

        return postcondicion(() -> verify(PIEZA).fueAtacadoCon(UN_ATAQUE));
    }

    private Postcondicion fueAtacadaLaNave() {

        return postcondicion(() -> verify(NAVE).fueAtacadoCon(UN_ATAQUE));
    }
}
