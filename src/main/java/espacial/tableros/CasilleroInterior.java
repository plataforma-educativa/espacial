package espacial.tableros;

import espacial.Casillero;
import espacial.Coordenada;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.Tablero;

public class CasilleroInterior implements Casillero {

    private final Coordenada coordenada;
    private final Tablero tablero;
    private EstadoDelCasillero estado;

    public CasilleroInterior(Tablero contenedor, int fila, int columna) {
        
        coordenada = Coordenada.con(fila, columna);
        tablero = contenedor;
        estado = new Vacio();
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
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {
        
        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenada));
    }
    
    @Override
    public Pieza obtenerPieza() {
        
        return estado.alObtenerPieza();
    }
    
    private class Vacio implements EstadoDelCasillero {

        @Override
        public EspectroEspacial alEscanear() {
            
            return EspectroEspacial.VACIO;
        }

        @Override
        public void alOcuparCon(Pieza unaPieza) {

            cambiarA(new Ocupado(unaPieza));
        }

        @Override
        public void alDesocupar() {
            
        }

        @Override
        public void alMoverPiezaA(Casillero destino) {
            
        }

        @Override
        public void alRecibirPiezaDesde(Casillero origen) {

            Pieza pieza = origen.obtenerPieza();
            origen.desocupar();
            ocuparCon(pieza);
        }

        @Override
        public Pieza alObtenerPieza() {

            return null;
        }

    }
    
    private class Ocupado implements EstadoDelCasillero {

        private Pieza pieza;
        
        public Ocupado(Pieza porPieza) {
            
            pieza = porPieza;
        }
        
        @Override
        public EspectroEspacial alEscanear() {
            
            return pieza.escanear();
        }

        @Override
        public void alOcuparCon(Pieza unaPieza) {

            cambiarA(new Ocupado(unaPieza));
        }

        @Override
        public void alDesocupar() {
            
            cambiarA(new Vacio());
        }

        @Override
        public void alMoverPiezaA(Casillero destino) {
            
            destino.recibirPiezaDesde(CasilleroInterior.this);
        }

        @Override
        public void alRecibirPiezaDesde(Casillero origen) {

            origen.obtenerPieza().chocarCon(pieza);
        }

        @Override
        public Pieza alObtenerPieza() {

            return pieza;
        }

    }
}
