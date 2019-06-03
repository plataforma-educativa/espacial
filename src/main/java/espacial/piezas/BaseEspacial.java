package espacial.piezas;

import espacial.Amarre;
import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Visitante;
import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.piezas.rasgos.PiezaAtacable;

import java.util.LinkedList;
import java.util.List;

public class BaseEspacial implements Pieza, PiezaAtacable {

    private static final int CAPACIDAD = 5000;
    private int puntos = 200;
    private Casillero casillero;
    private final List<Amarre> amarres;
    private int cargamento = 0;

    public BaseEspacial() {

        amarres = new LinkedList<>();
    }
    
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

    @Override
    public int buscar(SustanciaEspacial unaSustancia) {

        return cargamento;
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
    public void recibir(Carga unaCarga) {

        int cargaTotal = cargamento + unaCarga.obtenerCantidad();

        if (cargaTotal > CAPACIDAD) {

            throw  new ExcedeLaCapacidadDeCarga(CAPACIDAD, cargaTotal);
        }

        cargamento = cargaTotal;
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
