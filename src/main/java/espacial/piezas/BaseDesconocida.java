package espacial.piezas;

import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.piezas.rasgos.PiezaAtacable;

public class BaseDesconocida implements Pieza, PiezaAtacable {

    private final Indicador puntos = new Indicador(200);

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos.decrementarEn(decremento);
    }

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.BASE;
    }

    @Override
    public void aceptar(Visitante visitante) {

    }

    @Override
    public int obtenerPuntos() {

        return puntos.obtenerValor();
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnaBase();
    }

    @Override
    public String toString() {

        return describir();
    }
}
