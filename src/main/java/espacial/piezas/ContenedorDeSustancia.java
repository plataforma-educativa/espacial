package espacial.piezas;

import espacial.Ataque;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Visitante;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.piezas.rasgos.PiezaDeposito;

public abstract class ContenedorDeSustancia implements PiezaDeposito, PiezaAtacable {

    private int puntos = 50;

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.CONTENEDOR;
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnContenedor();
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos -= decremento;
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

        ataque.aplicarSobre(this);
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsContenedor(this);
    }

    @Override
    public int obtenerPuntos() {

        return puntos;
    }

    protected int obtenerCapacidad() {

        return 1000;
    }
}
