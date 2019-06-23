package espacial.piezas;

import espacial.Ataque;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Visitante;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.piezas.rasgos.PiezaDeposito;

public abstract class ContenedorDeSustancia implements PiezaDeposito, PiezaAtacable {

    private final Indicador puntos = new Indicador(50);

    @Override
    public EspectroEspacial escanear() {

        return EspectroEspacial.CONTENEDOR;
    }

    @Override
    public void fueColocadaEn(Casillero casillero) {

        puntos.cuandoSeAgota(() -> casillero.desocupar());
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnContenedor();
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos.decrementarEn(decremento);
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

        return puntos.obtenerValor();
    }

    protected int obtenerCapacidad() {

        return 1000;
    }

    @Override
    public String toString() {

        return describir();
    }
}
