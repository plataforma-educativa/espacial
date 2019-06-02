package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContenedorDeAntimateriaTest extends PruebaSobrePieza<ContenedorDeAntimateria> {

    private ContenedorDeAntimateria unContenedorDeAntimateria;

    @Override
    public ContenedorDeAntimateria piezaCreada() {

        return new ContenedorDeAntimateria();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.CONTENEDOR;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnContenedor());
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnContenedorDeAntimateria());

        comprobarQue(losPuntosInicialesDeUnContenedorDeAntimateriaSonCorrectos());
    }

    private Precondicion fueCreadoUnContenedorDeAntimateria() {

        return precondicion(() -> unContenedorDeAntimateria = new ContenedorDeAntimateria());
    }

    private Postcondicion losPuntosInicialesDeUnContenedorDeAntimateriaSonCorrectos() {

        return postcondicion(() -> assertThat(unContenedorDeAntimateria.obtenerPuntos()).as("puntos").isEqualTo(50));
    }

    @Test
    public void recibirCarga() {

        final int cantidad = 65;

        dadoQue(fueCreadoUnContenedorDeAntimateria());

        unContenedorDeAntimateria.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(elContenedorTiene(SustanciaEspacial.ANTIMATERIA, cantidad));
    }

    private Postcondicion elContenedorTiene(SustanciaEspacial sustanciaEsperada, int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unContenedorDeAntimateria.buscar(sustanciaEsperada))
                    .as("buscar(" + sustanciaEsperada + ")")
                    .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    public void recibirMultiplesCargas() {

        final int cantidadInicial = 20;
        final int cantidad = 90;

        dadoQue(fueCreadoUnContenedorDeAntimateriaRecibiendo(cantidadInicial));

        unContenedorDeAntimateria.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(elContenedorTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial + cantidad));
    }

    private Precondicion fueCreadoUnContenedorDeAntimateriaRecibiendo(int cantidadInicial) {

        return precondicion(() -> {

            unContenedorDeAntimateria = new ContenedorDeAntimateria();
            unContenedorDeAntimateria.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial));
        });
    }

    @Test
    public void recibirLaCargaMaxima() {
        
        final int cantidadMaxima = 1000;
        
        dadoQue(fueCreadoUnContenedorDeAntimateria());
        
        unContenedorDeAntimateria.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadMaxima));

        comprobarQue(elContenedorTiene(SustanciaEspacial.ANTIMATERIA, cantidadMaxima));
    }
    
    @Test
    public void recibirCargaExcediendoLaCapacidad() {

        final int cantidadExcedida = 1001;

        dadoQue(fueCreadoUnContenedorDeAntimateria());

        comprobarQue(generaErrorPorqueExcedeLaCapacidadDeCarga(() ->

                unContenedorDeAntimateria.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadExcedida)))
        );
    }
    
    private Postcondicion generaErrorPorqueExcedeLaCapacidadDeCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeLaCapacidadDeCarga.class)
        );
    }
}
