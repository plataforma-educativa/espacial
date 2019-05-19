package espacial.piezas;

import espacial.*;
import espacial.piezas.rasgos.PiezaAtacable;

public class ContenedorDeAntimateria implements Pieza, PiezaAtacable {

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
}
