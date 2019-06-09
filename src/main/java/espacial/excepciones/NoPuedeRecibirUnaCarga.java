package espacial.excepciones;

import espacial.Carga;
import espacial.Pieza;

public class NoPuedeRecibirUnaCarga extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 3320138903445440461L;

    public NoPuedeRecibirUnaCarga(Pieza unaPieza, Carga unaCarga) {

        super("%s no puede recibir una carga de '%s'", unaPieza.escanear(), unaCarga);
    }
}
