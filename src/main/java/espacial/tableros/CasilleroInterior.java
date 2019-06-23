package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.Coordenada;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Tablero;

public class CasilleroInterior implements Casillero {

    private final Tablero tablero;
    private final Coordenada coordenada;
    private EstadoDelCasillero estado;

    public CasilleroInterior(Tablero contenedor, int fila, int columna) {

        tablero = contenedor;
        coordenada = Coordenada.con(fila, columna);
        estado = new Vacio(this);
    }
    
    void cambiarA(EstadoDelCasillero nuevoEstado) {
        
        estado = nuevoEstado;
    }

    @Override
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {

        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenada));
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

    @Override
    public void entregar(Carga unaCarga) {

        estado.alEntregar(unaCarga);
    }

    @Override
    public void recibir(Carga unaCarga) {

        estado.alRecibir(unaCarga);
    }

    @Override
    public String toString() {

        return "Casillero" + coordenada + " -> " + escanear();
    }
}
