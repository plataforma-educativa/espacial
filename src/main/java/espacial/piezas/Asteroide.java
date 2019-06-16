package espacial.piezas;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.piezas.rasgos.AsteroideAtacable;

public class Asteroide implements Pieza, AsteroideAtacable {

    private final Indicador puntos = new Indicador(300);

    private final int dureza;

    public Asteroide() {

        this(100);
    }

    public Asteroide(int valorDureza) {

        dureza = valorDureza;
    }

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    public int obtenerDureza() {

        return dureza;
    }

    @Override
    public void fueColocadaEn(Casillero casillero) {

        puntos.cuandoSeAgota(casillero::desocupar);
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

        return puntos.obtenerValor();
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos.decrementarEn(decremento);
    }
}
