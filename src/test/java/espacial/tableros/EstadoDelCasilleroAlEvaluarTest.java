package espacial.tableros;

import espacial.Partidario;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlEvaluarTest extends EstadoDelCasilleroTest {

    private final Partidario.Condicional CONDICIONAL = mock(Partidario.Condicional.class, "CONDICIONAL");

    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);

        estado.alEvaluar(CONDICIONAL);

        comprobarQue(alEvaluarCondicionalEjecutoSiEsNeutral());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion alEvaluarCondicionalEjecutoSiEsNeutral() {

        return post(condicion -> verify(CONDICIONAL).siEsNeutral());
    }

    @Test
    void siEstaOcupado() {

        dadoQue(cuandoLaPiezaEsEvaluadaEjecutaSiEsAliado());
        estado = new Ocupado(CASILLERO, PIEZA);

        estado.alEvaluar(CONDICIONAL);

        comprobarQue(alEvaluarCondicionalEjecutoSiEsAliado());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion cuandoLaPiezaEsEvaluadaEjecutaSiEsAliado() {

        return pre(condicion -> {

            doAnswer(invocacion -> { CONDICIONAL.siEsAliado(); return null;})
                .when(PIEZA).evaluar(CONDICIONAL);
        });
    }

    private Postcondicion alEvaluarCondicionalEjecutoSiEsAliado() {

        return post(condicion -> verify(CONDICIONAL).siEsAliado());
    }

    @Test
    void siEstaOcupadoPorUnaBase() {

        dadoQue(cuandoLaPiezaEsEvaluadaEjecutaSiEsRival());
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alEvaluar(CONDICIONAL);

        comprobarQue(alEvaluarCondicionalEjecutoSiEsRival());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion cuandoLaPiezaEsEvaluadaEjecutaSiEsRival() {

        return pre(condicion -> {

            doAnswer(invocacion -> { CONDICIONAL.siEsRival(); return null;})
                    .when(PIEZA).evaluar(CONDICIONAL);
        });
    }

    private Postcondicion alEvaluarCondicionalEjecutoSiEsRival() {

        return post(condicion -> verify(CONDICIONAL).siEsRival());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        dadoQue(cuandoLaPiezaEsEvaluadaEjecutaSiEsAliado());
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alEvaluar(CONDICIONAL);

        comprobarQue(alEvaluarCondicionalEjecutoSiEsAliado());
        comprobarQue(noCambioElEstadoDelCasillero());
    }
}
