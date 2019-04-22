package espacial;

/**
 * El Tablero del Juego está compuesto por un conjunto de Casilleros. Cada
 * Casillero podrá estar ocupado por una Pieza del Juego.
 * 
 * @author Mariano Tugnarelli
 *
 */
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

    /**
     * @param direccionElegida
     * @return Casillero contiguo en Dirección {@code direccion} al Casillero
     */
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {
        
        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenada));
    }
}
