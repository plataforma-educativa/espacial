package espacial.tableros;

import espacial.Casillero;
import espacial.Coordenada;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Tablero;

public class CasilleroInterior implements Casillero {

    private final Coordenada coordenada;
    private Tablero tablero;
    private Pieza pieza = null;

    public CasilleroInterior(Tablero contenedor, int fila, int columna) {
        
        coordenada = Coordenada.con(fila, columna);
        tablero = contenedor;
    }

    @Override
    public EspectroEspacial escanear() {

        return pieza != null ? pieza.escanear() : EspectroEspacial.VACIO;
    }

    public void ocuparCon(Pieza unaPieza) {

        pieza = unaPieza;
        pieza.fueColocadaEn(this);
    }

    @Override
    public void moverPiezaA(Casillero destino) {

        Pieza piezaMovida = pieza;
        desocupar();
        destino.ocuparCon(piezaMovida);
    }

    @Override
    public void desocupar() {

        pieza = null;
    }

    @Override
    public boolean estaOcupado() {
        
        return pieza != null;
    }

    @Override
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {
        
        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenada));
    }
}
