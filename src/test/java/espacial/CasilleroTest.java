package espacial;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import espacial.piezas.Asteroide;
import espacial.piezas.CazaEspacial;
import espacial.piezas.ContenedorDeAntimateria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class CasilleroTest implements Prueba {

    private Tablero tablero;
    
    private final Pieza CONTENEDOR = new ContenedorDeAntimateria();
    
    private final Pieza ASTEROIDE = new Asteroide();
    
    private final Pieza NAVE = new CazaEspacial();
  
    @BeforeEach
    public void crearTablero() {
        
        tablero = new Tablero();
    }
    
    @Test
    public void escanearCuandoNoTienePieza() {
        
        Casillero casillero = tablero.obtenerCasilleroEn(5, 2);
        
        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.VACIO);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnContenedor() {
        
        Casillero casillero = tablero.obtenerCasilleroEn(-9, 1);
        
        casillero.ocuparCon(CONTENEDOR);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.CONTENEDOR);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnAsteroide() {
        
        Casillero casillero = tablero.obtenerCasilleroEn(3, 10);
        
        casillero.ocuparCon(ASTEROIDE);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.ASTEROIDE);
    }

    @Test
    public void moverPiezaEntreCasilleros() {
        
        Casillero origen = tablero.obtenerCasilleroEn(1, 1);
        origen.ocuparCon(NAVE);
        
        Casillero destino = tablero.obtenerCasilleroEn(0, 1);
        
        origen.moverPiezaA(destino);
        
        comprobarQue(laPiezaFueMovidaEntreCasilleros(origen, destino));
    }

    private Postcondicion laPiezaFueMovidaEntreCasilleros(Casillero origen, Casillero destino) {

        return postcondicion("la Nave fue movida entre los casilleros", () -> {
            
            assertThat(origen.escanear()).as("espectro del Casillero origen")
                .isEqualTo(EspectroEspacial.VACIO);
            assertThat(destino.escanear()).as("espectro del Casillero destino")
                .isEqualTo(EspectroEspacial.NAVE);
        });
    }
    
    @Test
    public void desocupar() {
        
        Casillero casillero = tablero.obtenerCasilleroEn(4, 9);
        casillero.ocuparCon(ASTEROIDE);
        
        casillero.desocupar();

        comprobarQue(quedoDesocupado(casillero));
    }

    private Postcondicion quedoDesocupado(Casillero casillero) {

        return postcondicion("el Casillero quedó desocupado", () -> {
            
            assertThat(casillero.escanear()).as("espectro del casillero")
                .isEqualTo(EspectroEspacial.VACIO);
        });
    }
    
    @ParameterizedTest
    @MethodSource("contiguosPorDireccion")
    public void obtenerContiguo(int fila, int columna, Direccion direccionElegida, 
                                int filaEsperada, int columnaEsperada) {
        
        Casillero casillero = tablero.obtenerCasilleroEn(fila, columna);
        
        Casillero contiguo = casillero.obtenerContiguoEn(direccionElegida);
        
        comprobarQue(elCasilleroEsEl(filaEsperada, columnaEsperada, contiguo));
    }
    
    public static Stream<Arguments> contiguosPorDireccion() {
        
        return Stream.of(
                arguments(  0,  0, Direccion.NORTE,  1,  0 ),
                arguments(  0,  0, Direccion.SUR,   -1,  0 ),
                arguments(  0,  0, Direccion.ESTE,   0,  1 ),
                arguments(  0,  0, Direccion.OESTE,  0, -1 ),
                arguments(  5, 10, Direccion.OESTE,  5,  9 ),
                arguments(  1, -6, Direccion.SUR,    0, -6 ),
                arguments( -8, -7, Direccion.NORTE, -7, -7 ),
                arguments(  2,  6, Direccion.ESTE,   2,  7 )
        );
    }
    
    private Postcondicion elCasilleroEsEl(int fila, int columna, Casillero casillero) {
        
        return postcondicion("el Casillero es el esperado", () -> {
            
            assertThat(casillero).as("casillero en [%d, %d]", fila, columna)
                .isSameAs(tablero.obtenerCasilleroEn(fila, columna));
        });
    }

    @ParameterizedTest
    @MethodSource("contiguosPorDireccionEnElLimiteDelTablero")
    public void obtenerContiguoCuandoEstaEnElLimiteDelTablero(int fila, int columna, Direccion direccionElegida) {
        
        Casillero casillero = tablero.obtenerCasilleroEn(fila, columna);
        
        Casillero contiguo = casillero.obtenerContiguoEn(direccionElegida);
        
        comprobarQue(elCasilleroEsMargen(contiguo));
    }
    
    public static Stream<Arguments> contiguosPorDireccionEnElLimiteDelTablero() {
        
        return Stream.of(
                arguments( 10,  0, Direccion.NORTE),
                arguments( 10,-26, Direccion.NORTE),
                arguments( 10, 26, Direccion.NORTE),
                arguments(-10,  0, Direccion.SUR),
                arguments(-10,-26, Direccion.SUR),
                arguments(-10, 26, Direccion.SUR),
                arguments(  0, 26, Direccion.ESTE),
                arguments( 10, 26, Direccion.ESTE),
                arguments(-10, 26, Direccion.ESTE),
                arguments(  0,-26, Direccion.OESTE),
                arguments( 10,-26, Direccion.OESTE),
                arguments(-10,-26, Direccion.OESTE)
        );
    }

    private Postcondicion elCasilleroEsMargen(Casillero contiguo) {

        return postcondicion("el casillero esperado está en el margen", () -> {
            
            assertThat(contiguo.escanear()).isEqualTo(EspectroEspacial.DESCONOCIDO);
        });
    }
}
