package espacial;

import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DepositoTest implements TestDeContrato {

    private static int CARGA = 1;
    private Deposito deposito;

    private Precondicion fueCreadoUnDeposito() {

        return pre(() -> deposito = new Deposito() {
        });
    }

    private Postcondicion generarExcepcionPorque(Class<?> claseExcepcion, Ejecutable ejecutable) {

        return post(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(claseExcepcion)
        );
    }

    @Test
    void cargarAntimateria() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(generarExcepcionPorque(NoPuedeRecibirUnaCarga.class, () -> deposito.cargarAntimateria(CARGA)));
    }

    @Test
    void cargarMetal() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(generarExcepcionPorque(NoPuedeRecibirUnaCarga.class, () -> deposito.cargarMetal(CARGA)));
    }

    @Test
    void cargarCristal() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(generarExcepcionPorque(NoPuedeRecibirUnaCarga.class, () -> deposito.cargarCristal(CARGA)));
    }

    @Test
    void descargarAntimateria() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(generarExcepcionPorque(NoPuedeEntregarUnaCarga.class, () -> deposito.descargarAntimateria(CARGA)));
    }

    @Test
    void descargarMetal() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(generarExcepcionPorque(NoPuedeEntregarUnaCarga.class, () -> deposito.descargarMetal(CARGA)));
    }

    @Test
    void descargarCristal() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(generarExcepcionPorque(NoPuedeEntregarUnaCarga.class, () -> deposito.descargarCristal(CARGA)));
    }

    @Test
    void contarAntimateria() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(noTieneAntimateria());
    }

    private Postcondicion noTieneAntimateria() {

        return post(() -> assertThat(deposito.contarAntimateria()).isEqualTo(0));
    }

    @Test
    void contarMetal() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(noTieneMetal());
    }

    private Postcondicion noTieneMetal() {

        return post(() -> assertThat(deposito.contarMetal()).isEqualTo(0));
    }

    @Test
    void contarCristal() {

        dadoQue(fueCreadoUnDeposito());

        comprobarQue(noTieneCristal());
    }

    private Postcondicion noTieneCristal() {

        return post(() -> assertThat(deposito.contarCristal()).isEqualTo(0));
    }
}