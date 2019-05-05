package espacial.tableros;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Tablero;

public class CasilleroInterior extends CasilleroDelTablero {

    private EstadoDelCasillero estado;

    public CasilleroInterior(Tablero contenedor, int fila, int columna) {
        
        super(contenedor, fila, columna);
        estado = new Vacio(this);
    }
    
    void cambiarA(EstadoDelCasillero nuevoEstado) {
        
        estado = nuevoEstado;
    }

    @Override
    public EspectroEspacial escanear() {

        return estado.alEscanear();
    }

    @Override
    public void ocuparCon(Pieza unaPieza) {

        estado.alOcuparCon(unaPieza);
        unaPieza.fueColocadaEn(this);
    }

    @Override
    public void moverPiezaA(Casillero destino) {

        estado.alMoverPiezaA(destino);
    }
    
    @Override
    public void recibirPiezaDesde(Casillero origen) {
        
        estado.alRecibirPiezaDesde(origen);
    }
    
    @Override
    public void desocupar() {

        estado.alDesocupar();
    }

    @Override
    public Pieza obtenerPieza() {
        
        return estado.alObtenerPieza();
    }
        
}
