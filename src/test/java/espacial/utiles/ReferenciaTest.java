package espacial.utiles;

import espacial.excepciones.Defecto;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ReferenciaTest implements TestDeContrato {

    Referencia<Object> unaReferencia;

    @Nested
    class Requerida {

        private final RuntimeException VALOR_REQUERIDO = new RuntimeException();

        @Test
        void crearConValor() {

            final Object valorReferenciado = new String("VALOR_REFERENCIADO");

            unaReferencia = Referencia.conValor(valorReferenciado);

            comprobarQue(unaReferenciaTiene(valorReferenciado));
        }

        @Test
        void cambiar() {

            final Object valorInicial = new String("VALOR_INICIAL");
            final Object valorNuevo = new String("VALOR_NUEVO");

            unaReferencia = Referencia.conValor(valorInicial);

            unaReferencia.cambiar(valorNuevo);

            comprobarQue(unaReferenciaTiene(valorNuevo));
        }

        @Test
        void crearConValorNulo() {

            unaReferencia = Referencia.conValorNulo();

            comprobarQue(generaUnDefecto(() ->  unaReferencia.obtener()));
        }

        @Test
        void crearConProveedor() {

            final Object valorGenerado = new String("VALOR_GENERADO");
            final Proveedor<Object> proveedor = () -> valorGenerado;

            unaReferencia = Referencia.con(proveedor);

            comprobarQue(unaReferenciaTiene(valorGenerado));
        }

        @Test
        void anular() {

            final Object valorInicial = new String("VALOR_INICIAL");

            unaReferencia = Referencia.conValor(valorInicial);

            unaReferencia.anular();

            comprobarQue(generaUnDefecto(() ->  unaReferencia.obtener()));
        }

        @Test
        void siEsNuloAlObtenerPuedeGenerarUnValor() {

            final Object valorGenerado = new String("VALOR_GENERADO");
            final Proveedor<Object> generarValor = () ->   valorGenerado;
            unaReferencia = Referencia.conValorNulo();

            unaReferencia.siEsNuloAlObtener(generarValor);

            comprobarQue(unaReferenciaTiene(valorGenerado));
        }

        @Test
        void siEsNuloAlObtenerPuedeGenerarExcepcion() {

            final Proveedor<Object> crearExcepcion = () ->   { throw VALOR_REQUERIDO; };
            unaReferencia = Referencia.conValorNulo();

            unaReferencia.siEsNuloAlObtener(crearExcepcion);

            comprobarQue(generaExcepcionUsandoElProveedor(() ->  unaReferencia.obtener()));
        }

        private Postcondicion generaUnDefecto(Operacion operacion) {

            return post(condicion ->

                    assertThatThrownBy(operacion::ejecutar)
                            .as("excepción generada")
                            .isExactlyInstanceOf(Defecto.class)
            );
        }

        private Postcondicion generaExcepcionUsandoElProveedor(Operacion operacion) {

            return post(condicion ->

                    assertThatThrownBy(operacion::ejecutar)
                            .as("excepción generada")
                            .isEqualTo(VALOR_REQUERIDO)
            );
        }

        @Test
        void tieneToStringCuandoEsNulo() {

            unaReferencia = Referencia.conValorNulo();

            assertThat(unaReferencia).hasToString("Referencia -> NULO");
        }

        @Test
        void tieneToStringCuandoTieneValor() {

            unaReferencia = Referencia.conValor("VALOR");

            assertThat(unaReferencia).hasToString("Referencia -> VALOR");
        }
    }

    private Postcondicion unaReferenciaTiene(Object valorReferenciado) {

        return post(condicion ->

                assertThat(unaReferencia.obtener())
                        .as("obtener()")
                        .isSameAs(valorReferenciado)
        );
    }
}