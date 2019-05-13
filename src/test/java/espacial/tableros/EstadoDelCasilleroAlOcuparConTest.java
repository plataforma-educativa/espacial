package espacial.tableros;

import static org.mockito.Mockito.*;
import espacial.Visitante;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

public class EstadoDelCasilleroAlOcuparConTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        dadoQue(laPiezaEsUnaNave());

        estado = new Vacio(CASILLERO);

        estado.alOcuparCon(PIEZA);
        
        comprobarQue(cambioElEstadoDelCasilleroPorOcupado());
    }

    private Precondicion laPiezaEsUnaNave() {

        return precondicion("PIEZA es una Nave", () -> {

            doAnswer(invocation -> {

                invocation.getArgument(0, Visitante.class).siEsNave(PIEZA);
                return null;

            }).when(PIEZA).aceptar(any(Visitante.class));

        });
    }

    @Test
    public void siEstaVacioOcupandoseConUnaBase() {

        dadoQue(laPiezaEsUnaBase());

        estado = new Vacio(CASILLERO);

        estado.alOcuparCon(PIEZA);

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBase());
    }

    private Precondicion laPiezaEsUnaBase() {

        return precondicion("PIEZA es una Base", () -> {

            doAnswer(invocation -> {

                invocation.getArgument(0, Visitante.class).siEsBase(PIEZA);
                return null;

            }).when(PIEZA).aceptar(any(Visitante.class));

        });
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
