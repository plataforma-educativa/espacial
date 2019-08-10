package espacial.excepciones;

import espacial.Carga;
import espacial.Pieza;

public class NoPuedeRecibirUnaCarga extends ErrorEspacial {

    private static final long serialVersionUID = 3320138903445440461L;

    public NoPuedeRecibirUnaCarga(Pieza unaPieza, Carga unaCarga) {

        super("%s no puede recibir una carga de '%s'", unaPieza.escanear(), unaCarga);
    }

    public NoPuedeRecibirUnaCarga(Carga unaCarga) {

        super("No puede recibir una carga de '%s'", unaCarga);
    }

}
