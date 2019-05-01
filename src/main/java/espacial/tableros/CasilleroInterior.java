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

    @Override
    public EspectroEspacial escanear() {

        return estado.escanear();
    }

    @Override
    public void ocuparCon(Pieza unaPieza) {

        estado = estado.ocuparCon(unaPieza);
        unaPieza.fueColocadaEn(this);
    }

    @Override
    public void moverPiezaA(Casillero destino) {

        estado = estado.moverPiezaA(destino);
    }
    
    @Override
    public void recibirPiezaDesde(Casillero origen) {
        
        estado = estado.recibirPiezaDesde(origen);
    }
    
    @Override
    public void desocupar() {

        estado = estado.desocupar();
    }

    @Override
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {
        
        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenada));
    }
    
    @Override
    public Pieza obtenerPieza() {
        
        return estado.obtenerPieza();
    }
    
    private class Vacio implements EstadoDelCasillero {

        @Override
        public EspectroEspacial escanear() {
            
            return EspectroEspacial.VACIO;
        }

        @Override
        public EstadoDelCasillero ocuparCon(Pieza unaPieza) {
            
            return new Ocupado(unaPieza);
        }

        @Override
        public EstadoDelCasillero desocupar() {
            
            return estado;
        }

        @Override
        public EstadoDelCasillero moverPiezaA(Casillero destino) {
            
            return estado;
        }

        @Override
        public EstadoDelCasillero recibirPiezaDesde(Casillero origen) {

            Pieza pieza = origen.obtenerPieza();
            
            origen.desocupar();
            CasilleroInterior.this.ocuparCon(pieza);
            
            return estado;
        }

        @Override
        public Pieza obtenerPieza() {

            return null;
        }

    }
    
    private class Ocupado implements EstadoDelCasillero {

        private Pieza pieza;
        
        public Ocupado(Pieza porPieza) {
            
            pieza = porPieza;
        }
        
        @Override
        public EspectroEspacial escanear() {
            
            return pieza.escanear();
        }

        @Override
        public EstadoDelCasillero ocuparCon(Pieza unaPieza) {

            return new Ocupado(unaPieza);
        }

        @Override
        public EstadoDelCasillero desocupar() {
            
            return new Vacio();
        }

        @Override
        public EstadoDelCasillero moverPiezaA(Casillero destino) {
            
            destino.recibirPiezaDesde(CasilleroInterior.this);
            
            return estado;
        }

        @Override
        public EstadoDelCasillero recibirPiezaDesde(Casillero origen) {

            origen.obtenerPieza().chocarCon(pieza);
            
            return estado;
        }

        @Override
        public Pieza obtenerPieza() {

            return pieza;
        }

    }
}
