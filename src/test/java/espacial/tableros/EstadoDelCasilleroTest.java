package espacial.tableros;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Pieza;
import espacial.excepciones.Defecto;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public abstract class EstadoDelCasilleroTest implements Prueba {

    protected final CasilleroInterior CASILLERO = mock(CasilleroInterior.class, "CASILLERO");
    protected final Casillero CASILLERO_ORIGEN = mock(Casillero.class, "CASILLERO_ORIGEN");
    protected final Casillero CASILLERO_DESTINO = mock(Casillero.class, "CASILLERO_DESTINO");
    protected final Pieza PIEZA = mock(Pieza.class, "PIEZA");
    protected final Pieza NAVE = mock(Pieza.class, "NAVE");
    protected final Pieza OTRA_PIEZA = mock(Pieza.class, "OTRA_PIEZA");
    protected final Pieza PIEZA_EN_ORIGEN = mock(Pieza.class, "PIEZA_EN_ORIGEN");
    protected final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");
    
    protected EstadoDelCasillero estado;

    protected Postcondicion noCambioElEstadoDelCasillero() {

        return postcondicion("no cambió el estado del casillero", () -> {
          
            verify(CASILLERO, never()).cambiarA(any());
        });
    }
    
    protected Postcondicion cambioElEstadoDelCasilleroPorVacio() {

        return cambioElEstadoDelCasilleroPor(Vacio.class);
    }
    
    protected Postcondicion cambioElEstadoDelCasilleroPorOcupado() {

        return cambioElEstadoDelCasilleroPor(Ocupado.class);
    }
    
    protected Postcondicion cambioElEstadoDelCasilleroPorOcupadoPorUnaBase() {

        return cambioElEstadoDelCasilleroPor(OcupadoPorUnaBase.class);
    }
    
    protected Postcondicion cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras() {

        return cambioElEstadoDelCasilleroPor(OcupadoPorUnaBaseConNaveEnManiobras.class);
    }

    protected Postcondicion cambioElEstadoDelCasilleroPor(Class<? extends EstadoDelCasillero> estado) {

        return postcondicion("cambió el estado del casillero por " + estado.getSimpleName(), () -> {
          
            verify(CASILLERO).cambiarA(any(estado));
        });
    }
    
    protected Postcondicion generaUnDefecto(Ejecutable ejecutable) {
        
        return postcondicion("genera un Defecto", () -> {
          
            assertThatThrownBy(ejecutable::ejecutar).isInstanceOf(Defecto.class);
        });
    }

    protected Postcondicion generaUnChoqueEntre(Pieza unaPieza, Pieza otraPieza) {
        
        return postcondicion("genera un choque entre " + unaPieza + " y " + otraPieza, () -> {

            verify(unaPieza).chocarCon(otraPieza);
        });
    }
    
    public abstract void siEstaVacio();
    public abstract void siEstaOcupado();
    public abstract void siEstaOcupadoPorUnaBase();
    public abstract void siEstaOcupadoPorUnaBaseConNaveEnManiobras();
}
