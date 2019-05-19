package espacial.piezas;

import espacial.*;
import espacial.piezas.rasgos.PiezaAtacable;

public class ContenedorDeAntimateria implements Pieza, PiezaAtacable {

    private int puntos;

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

    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsContenedor(this);
    }

    @Override
    public int obtenerPuntos() {

        return 50;
    }
}
