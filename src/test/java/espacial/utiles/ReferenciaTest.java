package espacial.utiles;

import espacial.excepciones.Defecto;
import espacial.test.Ejecutable;
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

            unaReferencia = ReferenciaRequerida.conValor(valorReferenciado);

            comprobarQue(unaReferenciaTiene(valorReferenciado));
        }

        @Test
        void cambiar() {

            final Object valorInicial = new String("VALOR_INICIAL");
            final Object valorNuevo = new String("VALOR_NUEVO");

            unaReferencia = ReferenciaRequerida.conValor(valorInicial);

            unaReferencia.cambiar(valorNuevo);

            comprobarQue(unaReferenciaTiene(valorNuevo));
        }

        @Test
        void crearConValorNulo() {

            unaReferencia = ReferenciaRequerida.conValorNulo();

            comprobarQue(generaUnDefecto(() -> unaReferencia.obtener()));
        }

        @Test
        void siEsNuloAlObtener() {

            unaReferencia = ReferenciaRequerida.conValorNulo().siEsNuloAlObtener(() -> VALOR_REQUERIDO);

            comprobarQue(generaExcepcionUsandoElProveedor(() -> unaReferencia.obtener()));

        }

        private Postcondicion generaUnDefecto(Ejecutable ejecutable) {

            return post(() ->

                    assertThatThrownBy(ejecutable::ejecutar)
                            .as("excepción generada")
                            .isExactlyInstanceOf(Defecto.class)
            );
        }

        private Postcondicion generaExcepcionUsandoElProveedor(Ejecutable ejecutable) {

            return post(() ->

                    assertThatThrownBy(ejecutable::ejecutar)
                            .as("excepción generada")
                            .isEqualTo(VALOR_REQUERIDO)
            );
        }
    }

    private Postcondicion unaReferenciaTiene(Object valorReferenciado) {

        return post(() ->

                assertThat(unaReferencia.obtener())
                        .as("obtener()")
                        .isSameAs(valorReferenciado)
        );
    }
}