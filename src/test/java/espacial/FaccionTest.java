package espacial;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FaccionTest implements TestDeContrato {

    private final Faccion.Condicional UN_CONDICIONAL = mock(Faccion.Condicional.class, "UN_CONDICIONAL");

    private Faccion unaFaccion;

    @Test
    void siEsNeutral() {

        dadoQue(unaFaccionEsNeutral());

        unaFaccion.evaluar(UN_CONDICIONAL);

        comprobarQue(unCondicionalSeEvaluoComoNeutral());
    }

    private Precondicion unaFaccionEsNeutral() {

        return pre(condicion -> unaFaccion = Faccion.NEUTRAL);
    }

    private Postcondicion unCondicionalSeEvaluoComoNeutral() {

        return post(condicion -> verify(UN_CONDICIONAL).siEsNeutral());
    }

    @Test
    void siEsAliado() {

        dadoQue(unaFaccionEsAliado());

        unaFaccion.evaluar(UN_CONDICIONAL);

        comprobarQue(unCondicionalSeEvaluoComoAliado());
    }

    private Precondicion unaFaccionEsAliado() {

        return pre(condicion -> unaFaccion = Faccion.ALIADO);
    }

    private Postcondicion unCondicionalSeEvaluoComoAliado() {

        return post(condicion -> verify(UN_CONDICIONAL).siEsAliado());
    }

    @Test
    void siEsRival() {

        dadoQue(unaFaccionEsRival());

        unaFaccion.evaluar(UN_CONDICIONAL);

        comprobarQue(unCondicionalSeEvaluoComoRival());
    }

    private Precondicion unaFaccionEsRival() {

        return pre(condicion -> unaFaccion = Faccion.RIVAL);
    }

    private Postcondicion unCondicionalSeEvaluoComoRival() {

        return post(condicion -> verify(UN_CONDICIONAL).siEsRival());
    }
}