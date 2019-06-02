package espacial.piezas;

import espacial.Ataque;
import espacial.Carga;
import espacial.Chocable;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Visitante;
import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.piezas.rasgos.PiezaAtacable;

public class ContenedorDeAntimateria implements Pieza, PiezaAtacable {

    private static final int CAPACIDAD = 1000;

    private int puntos = 50;
    private int cargamento = 0;

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
    public void recibir(Carga unaCarga) {

        int cargaTotal = cargamento + unaCarga.obtenerCantidad();

        if (cargaTotal > CAPACIDAD) {

            throw  new ExcedeLaCapacidadDeCarga(CAPACIDAD, cargaTotal);
        }

        cargamento = cargaTotal;
    }

    @Override
    public int buscar(SustanciaEspacial unaSustancia) {

        return cargamento;
    }
}
