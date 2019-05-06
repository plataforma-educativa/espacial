package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import espacial.Amarre;
import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class CazaEspacialTest extends PruebaSobrePieza<CazaEspacial> {

    private final Amarre AMARRE = mock(Amarre.class, "AMARRE");
    private CazaEspacial unCazaEspacial;
    
    @Override
    public CazaEspacial piezaCreada() {

        return new CazaEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.NAVE;
    }
    
    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return null;
    }

    @Test
    @Disabled
    public void fueChocadaPor() {

    }
    
    @Test
    public void chocoContraUnAsteroide() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        unCazaEspacial.chocoContraUnAsteroide();
        
        comprobarQue(elNivelDeEscudosBajoA(75));
    }

    private Precondicion fueCreadoUnCazaEspacial() {
        
        return precondicion("fue creado unCasaEspacial", () -> {
            
            unCazaEspacial = new CazaEspacial();
        });
    }

    private Postcondicion elNivelDeEscudosBajoA(int nivelDeEscudoEsperado) {

        return postcondicion("el nivel de escudos bajó a " + nivelDeEscudoEsperado, () -> {
            
            assertThat(unCazaEspacial.obtenerNivelDeEscudos()).as("nivel de escudos")
                .isEqualTo(nivelDeEscudoEsperado);
        });
    }
    
    @Test
    public void chocoContraUnContenedor() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        unCazaEspacial.chocoContraUnContenedor();
        
        comprobarQue(elNivelDeEscudosBajoA(90));
    }
    
    @Test
    public void chocoContraElBordeDelTablero() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        unCazaEspacial.chocoContraElBordeDelTablero();
        
        comprobarQue(elNivelDeEscudosBajoA(50));
    }
    
    @Test
    public void chocoContraUnAgujeroNegro() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        unCazaEspacial.chocoContraUnAgujeroNegro();

        comprobarQue(elNivelDeEscudosBajoA(25));
    }
    
    @Test
    public void despegar() {
    
        dadoQue(fueCreadoUnCazaEspacial());
        dadoQue(unCazaEspacialFueAmarrado());

        unCazaEspacial.despegar();
        
        comprobarQue(unCazaEspacialSoltoElAmarre());
    }

    private Precondicion unCazaEspacialFueAmarrado() {

        return precondicion("unCazaEspacial fue amarrado", () -> {
           
            unCazaEspacial.fueAmarradaCon(AMARRE);
        });
    }

    private Postcondicion unCazaEspacialSoltoElAmarre() {

        return postcondicion("unCazaEspacial soltó el AMARRE", () -> { 
          
            verify(AMARRE).soltar();
        });
    }
    
}
