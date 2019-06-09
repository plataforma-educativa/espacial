package espacial.excepciones;

import espacial.Carga;
import espacial.Pieza;

public class NoPuedeEntregarUnaCarga extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 4025811230145729830L;

    public NoPuedeEntregarUnaCarga(Pieza unaPieza, Carga unaCarga) {

        super("%s no puede entregar una carga de '%s'", unaPieza.escanear(), unaCarga);
    }
}
