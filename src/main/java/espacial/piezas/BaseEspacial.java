package espacial.piezas;

import espacial.Amarre;
import espacial.Ataque;
import espacial.Cargamento;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.piezas.rasgos.DepositoDeAntimateria;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.piezas.rasgos.PiezaDeposito;

import java.util.LinkedList;
import java.util.List;

public class BaseEspacial implements Pieza, PiezaAtacable, PiezaDeposito, DepositoDeAntimateria {

    private static final int CAPACIDAD = 5000;
    private final List<Amarre> amarres = new LinkedList<>();
    private final Cargamento antimateria = new Cargamento(CAPACIDAD);
    private int puntos = 200;
    private Casillero casillero;

    @Override
    public void fueColocadaEn(Casillero casillero) {
     
        this.casillero = casillero;
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

        ataque.aplicarSobre(this);
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

        return amarres.toArray(new Amarre[0]);
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

    @Override
    public Cargamento obtenerAntimateria() {

        return antimateria;
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
