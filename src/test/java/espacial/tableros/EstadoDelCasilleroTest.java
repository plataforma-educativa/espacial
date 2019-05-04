package espacial.tableros;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.excepciones.Defecto;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public abstract class EstadoDelCasilleroTest<T extends EstadoDelCasillero> implements Prueba {

    protected final CasilleroInterior CASILLERO = mock(CasilleroInterior.class);
    protected final Casillero CASILLERO_ORIGEN = mock(Casillero.class);
    protected final Casillero CASILLERO_DESTINO = mock(Casillero.class);
    protected final Pieza OTRA_PIEZA = mock(Pieza.class);
    
    protected T estado;

    protected Postcondicion alEscanearDevuelve(EspectroEspacial espectro) {

        return postcondicion("al escanear devuelve " + espectro, () -> {
          
            assertThat(estado.alEscanear()).isEqualTo(espectro);
        });
    }

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
}
