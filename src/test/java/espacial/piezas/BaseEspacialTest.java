package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.PiezaMovil;
import espacial.piezas.BaseEspacial.Amarre;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class BaseEspacialTest extends PruebaSobrePieza<BaseEspacial> {
    
    private final PiezaMovil NAVE = mock(PiezaMovil.class, "NAVE");
    private final PiezaMovil NAVE_ALFA = mock(PiezaMovil.class, "NAVE_ALFA");
    private final PiezaMovil NAVE_BETA = mock(PiezaMovil.class, "NAVE_BETA");
    private final PiezaMovil NAVE_GAMMA = mock(PiezaMovil.class, "NAVE_GAMMA");
    private final Casillero CASILLERO = mock(Casillero.class, "CASILLERO");

    private BaseEspacial unaBase;
    
    @Override
    public BaseEspacial piezaCreada() {

        return new BaseEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Test
    @Disabled
    public void fueChocadaPor() {

    }
    
    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra una Base", () -> {
            
        });
    }
    
    @Test
    public void amarrarUnaNave() {
        
        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());
        
        unaBase.amarrar(NAVE);
        
        comprobarQue(unaBaseTieneAmarradaLaNave());
    }

    private Precondicion unaBaseFueCreadaYColocadaEnCasillero() {
        
        return precondicion("unaBase fue creada", () -> {
          
            unaBase = new BaseEspacial();
            unaBase.fueColocadaEn(CASILLERO);
        });
    }
    
    private Postcondicion unaBaseTieneAmarradaLaNave() {

        return postcondicion("unaBase tiene amarrada la NAVE", () -> {
          
            assertThat(unaBase.obtenerAmarres())
                .as("amarres")
                .extracting(Amarre::obtenerPieza)
                .containsExactly(NAVE);
        });
    }
    
    @Test
    public void amarrarMultiplesNaves() {
        
        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());
        
        unaBase.amarrar(NAVE_ALFA);
        unaBase.amarrar(NAVE_BETA);
        unaBase.amarrar(NAVE_GAMMA);
        
        comprobarQue(unaBaseTieneAmarradasLasNavesAlfaBetaGamma());
    }

    private Postcondicion unaBaseTieneAmarradasLasNavesAlfaBetaGamma() {

        return postcondicion("unaBase tiene amarrada la NAVE", () -> {
            
            assertThat(unaBase.obtenerAmarres())
                .as("amarres")
                .extracting(Amarre::obtenerPieza)
                .containsExactly(NAVE_ALFA, NAVE_BETA, NAVE_GAMMA);
        });
    }
    
}
