package espacial.partidas.batalla;

import espacial.BaseEspacial;
import espacial.Direccion;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Tablero;

public class DefensaDeBaseRival implements Pieza.Observador {

    private final Tablero tablero;
    private final BaseEspacial baseRival;
    private NaveEspacial defensorNorte;
    private NaveEspacial defensorSur;
    private NaveEspacial defensorEste;
    private NaveEspacial defensorOeste;

    public DefensaDeBaseRival(Tablero enTablero, BaseEspacial unaBaseRival) {

        tablero = enTablero;
        baseRival = unaBaseRival;

        amarrarNavesDefensoras();
    }

    private void amarrarNavesDefensoras() {

        defensorNorte = amararDefensor();
        defensorSur = amararDefensor();
        defensorEste = amararDefensor();
        defensorOeste = amararDefensor();
    }

    private NaveEspacial amararDefensor() {

        return tablero.enBase(baseRival).amarrarNave();
    }

    @Override
    public void fueDestruida(Pieza unaPieza) {

        if (baseRival.tieneNavesAmarradas()) {

            movilizar(defensorNorte, Direccion.NORTE);
            movilizar(defensorSur, Direccion.SUR);
            movilizar(defensorEste, Direccion.ESTE);
            movilizar(defensorOeste, Direccion.OESTE);
        }
    }

    private void movilizar(NaveEspacial defensor, Direccion direccion) {

        defensor.despegar();
        defensor.moverEn(direccion);
    }
}
