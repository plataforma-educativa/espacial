package espacial.piezas;

import java.util.LinkedList;
import java.util.List;

import espacial.*;
import espacial.piezas.rasgos.PiezaAtacable;

public class BaseEspacial implements Pieza, PiezaAtacable {

    private int puntos = 200;
    private Casillero casillero;
    private final List<Amarre> amarres;

    public BaseEspacial() {

        amarres = new LinkedList<>();
    }
    
    @Override
    public void fueColocadaEn(Casillero casillero) {
     
        this.casillero = casillero;
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

    }

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.BASE;
    }

    public void amarrar(NaveEspacial pieza) {
        
        Amarre amarre = new AmarreConBaseEspacial(pieza);
        amarres.add(amarre);
        pieza.fueAmarradaCon(amarre);
    }

    public Amarre[] obtenerAmarres() {

        return amarres.toArray(new Amarre[amarres.size()]);
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsBase(this);
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnaBase();
    }

    @Override
    public int obtenerPuntos() {

        return puntos;
    }

    @Override
    public void decrementarPuntosEn(int decremento) {

        puntos -= decremento;
    }

    private class AmarreConBaseEspacial implements Amarre {
        
        private final NaveEspacial pieza;
        
        private AmarreConBaseEspacial(NaveEspacial unaPieza) {
            
            pieza = unaPieza;
        }
        
        public NaveEspacial obtenerPieza() {
            
            return pieza;
        }
        
        @Override
        public void soltar() {

            if (amarres.remove(this)) {

                casillero.ocuparCon(pieza);
            }
        }
    }
}
