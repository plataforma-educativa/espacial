package espacial.piezas;

import espacial.Ataque;
import espacial.Carga;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.piezas.rasgos.PiezaAtacable;

public class Asteroide implements Pieza, PiezaAtacable {

    private int puntos = 90;

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnAsteroide();
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

        ataque.aplicarSobre(this);
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsAsteroide(this);
    }

    @Override
    public int obtenerPuntos() {

        return puntos;
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos -= decremento;
    }

    @Override
    public void recibir(Carga unaCarga) {

        throw new NoPuedeRecibirUnaCarga(this, unaCarga);
    }
}
