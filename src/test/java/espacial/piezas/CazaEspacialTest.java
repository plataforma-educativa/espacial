package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class CazaEspacialTest implements PruebaSobrePieza<CazaEspacial> {

    private CazaEspacial unCasaEspacial;
    
    @Override
    public CazaEspacial piezaCreada() {

        return new CazaEspacial();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.NAVE;
    }
    
    @Test
    public void chocoContraUnAsteroide() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        unCasaEspacial.chocoContraUnAsteroide();
        
        comprobarQue(elNivelDeEscudosBajoA(75));
    }

    private Precondicion fueCreadoUnCazaEspacial() {
        
        return precondicion("fue creado unCasaEspacial", () -> {
            
            unCasaEspacial = new CazaEspacial();
        });
    }

    private Postcondicion elNivelDeEscudosBajoA(int nivelDeEscudoEsperado) {

        return postcondicion("el nivel de escudos bajó a " + nivelDeEscudoEsperado, () -> {
            
            assertThat(unCasaEspacial.obtenerNivelDeEscudos()).as("nivel de escudos")
                .isEqualTo(nivelDeEscudoEsperado);
        });
    }
    
    @Test
    public void chocoContraUnContenedor() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        unCasaEspacial.chocoContraUnContenedor();
        
        comprobarQue(elNivelDeEscudosBajoA(90));
    }
}
