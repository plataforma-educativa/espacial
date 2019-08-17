package espacial.tableros;

import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.Partidario;
import espacial.Pieza;
import espacial.Tablero;
import espacial.Visitante;
import espacial.utiles.Referencia;

public class CambiarReferenciaAlSerAgregadaUnaBase implements Tablero.Observador, Visitante {

    private final Referencia<BaseEspacial> baseReferenciada;

    public CambiarReferenciaAlSerAgregadaUnaBase(Referencia<BaseEspacial> enReferencia) {

        baseReferenciada = enReferencia;
    }

    @Override
    public void fueAgregadaEn(Casillero casillero, Pieza unaPieza) {

        unaPieza.aceptar(this);
    }

    @Override
    public void siEsBase(BaseEspacial pieza) {

        pieza.evaluar(Partidario.siEsAliado(() -> baseReferenciada.cambiar(pieza)));
    }
}
