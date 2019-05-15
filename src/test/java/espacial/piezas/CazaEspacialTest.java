package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.excepciones.LaNaveNoEstaEnLaBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import espacial.Amarre;
import espacial.Casillero;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class CazaEspacialTest extends PruebaSobrePieza<CazaEspacial> {

    private final Amarre AMARRE = mock(Amarre.class, "AMARRE");
    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");
    private final Casillero CASILLERO_NORTE = mock(Casillero.class, "CASILLERO_NORTE");
    private final Casillero CASILLERO_SUR = mock(Casillero.class, "CASILLERO_SUR");
    private final Casillero CASILLERO_ESTE = mock(Casillero.class, "CASILLERO_ESTE");
    private final Casillero CASILLERO_OESTE = mock(Casillero.class, "CASILLERO_OESTE");
            
    private CazaEspacial unCazaEspacial;

    @BeforeEach
    public void simularCasillero() {
        
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.NORTE)).thenReturn(CASILLERO_NORTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.SUR)).thenReturn(CASILLERO_SUR);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.ESTE)).thenReturn(CASILLERO_ESTE);
        when(UN_CASILLERO.obtenerContiguoEn(Direccion.OESTE)).thenReturn(CASILLERO_OESTE);
    }
    
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

        return postcondicion("notificó a la PiezaMovil que chocó contra una Nave", () -> {

            verify(PIEZA_MOVIL).chocoContraUnaNave();
        });
    }

    @Test
    public void moverEnDireccionCuandoNoDespego() {
        
        dadoQue(fueCreadoUnCazaEspacial());
        
        comprobarQue(generaExcepcionLaNaveNoEstaEnUnCasillero(() -> unCazaEspacial.moverEn(Direccion.NORTE) ));
    }
    
    private Postcondicion generaExcepcionLaNaveNoEstaEnUnCasillero(Ejecutable ejecutable) {

        return postcondicion("genera excepción LaNaveNoEstaEnUnCasillero", () -> {
            
            assertThatThrownBy(ejecutable::ejecutar)
                .isInstanceOf(LaNaveNoEstaEnUnCasillero.class);
        });
    }

    @Test
    public void moverEnDireccion() {
        
        dadoQue(fueCreadoUnCazaEspacialColocadoEnUnCasillero());
        
        unCazaEspacial.moverEn(Direccion.NORTE);
        
        comprobarQue(unCazaEspacialSeMovioUnCasilleroEnDireccionNorte());
    }
    
    private Precondicion fueCreadoUnCazaEspacialColocadoEnUnCasillero() {

        return precondicion("fue creado unCazaEspacial colocado en UN_CASILLERO", () -> {
          
            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private Postcondicion unCazaEspacialSeMovioUnCasilleroEnDireccionNorte() {

        return postcondicion("unCazaEspacial se movió un Casillero en Dirección NORTE", () -> {
          
            verify(UN_CASILLERO).moverPiezaA(CASILLERO_NORTE);
        });
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
    public void chocoContraUnaNave() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnaNave();

        comprobarQue(elNivelDeEscudosBajoA(75));
    }

    @Test
    public void chocoContraUnaBase() {

        dadoQue(fueCreadoUnCazaEspacial());

        unCazaEspacial.chocoContraUnaBase();

        comprobarQue(elNivelDeEscudosBajoA(95));
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

    @Test
    public void despegarCuandoNoEstaAmarrada() {

        dadoQue(fueCreadoUnCazaEspacial());

        comprobarQue(generaExcepcionLaNaveNoEstaEnLaBase(() -> unCazaEspacial.despegar()));
    }

    private Postcondicion generaExcepcionLaNaveNoEstaEnLaBase(Ejecutable ejecutable) {

        return postcondicion("genera excepción LaNaveNoEstaEnLaBase", () -> {

            assertThatThrownBy(ejecutable::ejecutar)
                    .isInstanceOf(LaNaveNoEstaEnLaBase.class);
        });
    }
}
