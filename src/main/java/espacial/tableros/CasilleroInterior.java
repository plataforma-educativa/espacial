package espacial.tableros;

import espacial.Ataque;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
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
    public int buscar(SustanciaEspacial sustancia) {

        return estado.alBuscar(sustancia);
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

    @Override
    public void fueAtacadoCon(Ataque unAtaque) {

        estado.alSerAtacadoCon(unAtaque);
    }
}
