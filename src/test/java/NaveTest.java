import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;

public class NaveTest implements Prueba {

    private BatallaEspacial batallaEspacial;
    private Nave unaNave;
    
    @Test
    public void crearUnObjetoDeTipoNaveDejandoloEnLaBase() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        
        Nave unaNave = new Nave();
        
        comprobarQue(existeEnLaBase(unaNave));
    }

    private Postcondicion existeEnLaBase(Nave unaNave) {
        
        return postcondicion("existe en la Base una Nave", () -> {
            
            assertThat(batallaEspacial.obtenerNaves()).as("naves de la BatallaEspacial")
                .hasSize(1)
                .containsExactly(unaNave);

//            assertThat(batallaEspacial.obtenerTablero()).tieneBase().en(0, 0);
        });
    }
    
    private Precondicion fueCreadaLaBatallaEspacial() {
        
        return precondicion("fue creada la BatallaEspacial", () -> {
           
            batallaEspacial = new BatallaEspacial();
        });
    }
    
    @Test
    public void crearTresObjetosDeTipoNaveDejandolosEnLaBase() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        
        Nave primerNave = new Nave();
        Nave segundaNave = new Nave();
        Nave tercerNave = new Nave();
        
        comprobarQue(existenEnLaBase(primerNave, segundaNave, tercerNave));
    }

    private Postcondicion existenEnLaBase(Nave primerNave, Nave segundaNave, Nave tercerNave) {

        return postcondicion("existen en la Base tres Naves", () -> {
          
            assertThat(batallaEspacial.obtenerNaves()).as("naves de la BatallaEspacial")
                .hasSize(3)
                .containsExactly(primerNave, segundaNave, tercerNave);
        });
    }
 
    @Test
    @Disabled
    public void noPuedeAvanzarAlNorteSiNoDespegoAntes() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());
        
        comprobarQue(generaElErrorLaNaveNoDespego(() -> unaNave.avanzarAlNorte()));
    }
    
    private Postcondicion generaElErrorLaNaveNoDespego(Ejecutable ejecutable) {

        return postcondicion("genera el error LaNaveNoDespego", () -> {
            
            assertThatThrownBy(ejecutable::ejecutar).isInstanceOf(LaNaveNoEstaEnUnCasillero.class);
        });
    }

    @Test
    public void avanzarAlNorte() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unaNave.avanzarAlNorte();
        
        comprobarQue(unaNaveEstaAlNorteDeLaBase());
    }
    
    private Precondicion fueCreadaUnaNave() {

        return precondicion("fue creada una Nave", () -> { 
          
            unaNave = new Nave();
        });
    }

    private Postcondicion unaNaveEstaAlNorteDeLaBase() {
        
        return postcondicion("la Nave está al NORTE de la Base", () -> {
        
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave().en(1, 0)
                .tieneVacio().en(0, 0);
        });
    }
    
    @Test
    public void avanzarAlNorteTresVeces() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unaNave.avanzarAlNorte();
        unaNave.avanzarAlNorte();
        unaNave.avanzarAlNorte();

        comprobarQue(unaNaveEstaDosCasillerosAlNorteDeLaBase());
    }

    private Postcondicion unaNaveEstaDosCasillerosAlNorteDeLaBase() {

        return postcondicion("la Nave está a dos casilleros al NORTE de la Base", () -> {
            
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave().en(3, 0)
                .tieneVacio().en(0, 0).en(1, 0).en(2, 0);
        });
    }
    
    @Test
    public void avanzarAlSur() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unaNave.avanzarAlSur();
        
        comprobarQue(unaNaveEstaAlSurDeLaBase());
    }
    
    private Postcondicion unaNaveEstaAlSurDeLaBase() {
        
        return postcondicion("la Nave está al SUR de la Base", () -> {
        
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave().en(-1, 0)
                .tieneVacio().en(0, 0);
        });
    }

    @Test
    public void avanzarAlEste() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unaNave.avanzarAlEste();
        
        comprobarQue(unaNaveEstaAlEsteDeLaBase());
    }
    
    private Postcondicion unaNaveEstaAlEsteDeLaBase() {
        
        return postcondicion("la Nave está al ESTE de la Base", () -> {
        
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave().en(0, 1)
                .tieneVacio().en(0, 0);
        });
    }

    @Test
    public void avanzarAlOeste() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());

        unaNave.avanzarAlOeste();
        
        comprobarQue(unaNaveEstaAlOesteDeLaBase());
    }
    
    private Postcondicion unaNaveEstaAlOesteDeLaBase() {
        
        return postcondicion("la Nave está al OESTE de la Base", () -> {
        
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave().en(0, -1)
                .tieneVacio().en(0, 0);
        });
    }
    
    @Test
    public void noPuedeAvanzarSiExisteUnAsteroideEnElCasilleroDestino() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlEsteDeUnAsteroide());
        
        unaNave.avanzarAlOeste();
        
        comprobarQue(unaNaveQuedoEnElCasillero(1,-2));
        comprobarQue(unaNaveSufrioElChoqueContraElAsteroide());
    }

    private Precondicion unaNaveEstaAlEsteDeUnAsteroide() {

        return precondicion("unNave está al ESTE de un ASTEROIDE", () -> {
          
            unaNave = new Nave();
            unaNave.avanzarAlNorte();
            unaNave.avanzarAlOeste();
            unaNave.avanzarAlOeste();
        });
    }
    
    private Postcondicion unaNaveSufrioElChoqueContraElAsteroide() {

        return postcondicion("unaNave sufrió el choque contra el ASTEROIDE", () -> {
          
            assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos")
                .isEqualTo(75);
        });
    }
    
    @Test
    public void consultarNivelDeEscudos() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(fueCreadaUnaNave());
        
        int escudos = unaNave.consultarNivelDeEscudos();

        comprobarQue(estaAlMaximoElNivel(escudos));
    }

    private Postcondicion estaAlMaximoElNivel(int escudos) {

        return postcondicion("esta al maximo su nivel de escudos", () -> {
          
            assertThat(escudos).isEqualTo(100);
        });
    }
    
    @Test
    public void noPuedeAvanzarSiExisteUnContenedorEnElCasilleroDestino() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlSurDeUnAsteroide());
        
        unaNave.avanzarAlNorte();
        
        comprobarQue(unaNaveQuedoEnElCasillero(3,2));
        comprobarQue(unaNaveSufrioElChoqueContraElContenedor());
    }

    private Precondicion unaNaveEstaAlSurDeUnAsteroide() {

        return precondicion("unaNave está al SUR de un ASTEROIDE", () -> {
          
            unaNave = new Nave();
            IntStream.range(0, 2).forEach(n -> unaNave.avanzarAlEste());
            IntStream.range(0, 3).forEach(n -> unaNave.avanzarAlNorte());
        });
    }

    private Postcondicion unaNaveQuedoEnElCasillero(int fila, int columna) {

        return postcondicion("unaNave quedó en el casillero [" + fila + "," + columna+ "]", () -> {
          
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave().en(fila, columna);
        });
    }
    
    private Postcondicion unaNaveSufrioElChoqueContraElContenedor() {

        return postcondicion("unaNave sufrió el choque contra el CONTENEDOR", () -> {
          
            assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos")
                .isEqualTo(90);
        });
    }
    
    @Test
    public void noPuedeAvanzarSiElCasilleroDestinoEsElBordeDelTablero() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaEnElBordeSurDelTablero());
        
        unaNave.avanzarAlSur();
        
        comprobarQue(unaNaveQuedoEnElCasillero(-10, 0));
        comprobarQue(unaNaveSufrioElChoqueContraElBorde());
    }

    private Precondicion unaNaveEstaEnElBordeSurDelTablero() {

        return precondicion("una Nave en el borde SUR del Tablero", () -> {
          
            unaNave = new Nave();
            unaNave.avanzarAlOeste();
            IntStream.range(0, 10).forEach(n -> unaNave.avanzarAlSur());
            unaNave.avanzarAlEste();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElBorde() {

        return postcondicion("unaNave sufrió el choque contra el Borde", () -> {
            
            assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos")
                .isEqualTo(50);
        });
    }

    @Test
    public void noPuedeAvanzarSiExisteUnAgujeroNegroEnElCasilleroDestino() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        dadoQue(unaNaveEstaAlEsteDeUnAgujeroNegro());
        
        unaNave.avanzarAlOeste();
        
        comprobarQue(unaNaveQuedoEnElCasillero(1, -5));
        comprobarQue(unaNaveSufrioElChoqueContraElAgujeroNegro());
    }
    
    private Precondicion unaNaveEstaAlEsteDeUnAgujeroNegro() {

        return precondicion("unaNave está al ESTE de un AgujeroNegro (DESCONOCIDO)", () -> {
          
            unaNave = new Nave();
            IntStream.range(0, 5).forEach(n -> unaNave.avanzarAlOeste());
            unaNave.avanzarAlNorte();
        });
    }

    private Postcondicion unaNaveSufrioElChoqueContraElAgujeroNegro() {
    
        return postcondicion("unaNave sufrió el choque contra el AgujeroNegro", () -> {

            assertThat(unaNave.consultarNivelDeEscudos()).as("nivel de escudos")
                .isEqualTo(25);
        });
    }
    
}
