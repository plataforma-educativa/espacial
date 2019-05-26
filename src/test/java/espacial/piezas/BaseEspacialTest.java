package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.NaveEspacial;
import org.junit.jupiter.api.Test;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Amarre;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class BaseEspacialTest extends PruebaSobrePieza<BaseEspacial> {
    
    private final NaveEspacial NAVE = mock(NaveEspacial.class, "NAVE");
    private final NaveEspacial NAVE_ALFA = mock(NaveEspacial.class, "NAVE_ALFA");
    private final NaveEspacial NAVE_BETA = mock(NaveEspacial.class, "NAVE_BETA");
    private final NaveEspacial NAVE_GAMMA = mock(NaveEspacial.class, "NAVE_GAMMA");
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

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion("notificó a la NaveEspacial que chocó contra una Base", () -> {

            verify(NAVE_ESPACIAL).chocoContraUnaBase();
        });
    }

    @Test
    public void amarrarUnaNave() {
        
        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());
        
        unaBase.amarrar(NAVE);
        
        comprobarQue(unaBaseTieneAmarradaLaNave());
    }

    private Precondicion unaBaseFueCreadaYColocadaEnCasillero() {
        
        return precondicion(() -> {
          
            unaBase = new BaseEspacial();
            unaBase.fueColocadaEn(CASILLERO);
        });
    }
    
    private Postcondicion unaBaseTieneAmarradaLaNave() {

        return postcondicion(() -> {
          
            Amarre amarre = unaBase.obtenerAmarres()[0];
            
            assertThat(unaBase.obtenerAmarres())
                .as("amarres")
                .extracting(Amarre::obtenerPieza)
                .containsExactly(NAVE);
            
            verify(NAVE).fueAmarradaCon(amarre);
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

        return postcondicion(() -> {
            
            assertThat(unaBase.obtenerAmarres())
                .as("amarres")
                .extracting(Amarre::obtenerPieza)
                .containsExactly(NAVE_ALFA, NAVE_BETA, NAVE_GAMMA);
        });
    }
    
    @Test
    public void soltarAmarreDeUnaNave() {
        
        dadoQue(unaBaseFueCreadaYColocadaEnCasillero());
        dadoQue(lasNavesAlfaBetaGammaEstanAmarradasAUnaBase());
        
        unaBase.obtenerAmarres()[1].soltar();
        
        comprobarQue(unaBaseYaNoTieneMasAmarradaLaNaveBeta());
        comprobarQue(naveBetaFueColocadaEnElCasilleroDeLaBase());
    }

    private Precondicion lasNavesAlfaBetaGammaEstanAmarradasAUnaBase() {
        
        return precondicion(() -> {
          
            unaBase.amarrar(NAVE_ALFA);
            unaBase.amarrar(NAVE_BETA);
            unaBase.amarrar(NAVE_GAMMA);
        });
    }

    private Postcondicion unaBaseYaNoTieneMasAmarradaLaNaveBeta() {

        return postcondicion(() -> {
            
            assertThat(unaBase.obtenerAmarres())
                .as("amarres")
                .extracting(Amarre::obtenerPieza)
                .containsExactly(NAVE_ALFA, NAVE_GAMMA);
            
        });
    }
    
    private Postcondicion naveBetaFueColocadaEnElCasilleroDeLaBase() {

        return postcondicion("NAVE_BETA fue colocada en el CASILLERO", () -> {

            verify(CASILLERO).ocuparCon(NAVE_BETA);
        });
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadaUnaBase());

        comprobarQue(losPuntosInicialesDeUnaBaseSonCorrectos());
    }

    private Precondicion fueCreadaUnaBase() {

        return precondicion(() -> unaBase = new BaseEspacial());
    }

    private Postcondicion losPuntosInicialesDeUnaBaseSonCorrectos() {

        return postcondicion(() -> assertThat(unaBase.obtenerPuntos()).as("puntos").isEqualTo(200));
    }

}
