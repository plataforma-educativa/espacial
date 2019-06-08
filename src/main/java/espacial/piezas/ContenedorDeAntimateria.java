package espacial.piezas;

import espacial.Ataque;
import espacial.Cargamento;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Visitante;
import espacial.piezas.rasgos.DepositoDeAntimateria;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.piezas.rasgos.PiezaDeposito;

public class ContenedorDeAntimateria implements PiezaDeposito, PiezaAtacable, DepositoDeAntimateria {

    private static final int CAPACIDAD = 1000;

    private final Cargamento antimateria = new Cargamento(CAPACIDAD);
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

    @Override
    public Cargamento obtenerAntimateria() {

        return antimateria;
    }
}
