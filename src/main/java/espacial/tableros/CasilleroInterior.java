package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.ConsumidorDeCasilleros;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import espacial.VisitanteDeCasilleros;

public class CasilleroInterior extends CasilleroDelTablero {

    private EstadoDelCasillero estado;

    public CasilleroInterior(Tablero contenedor, int fila, int columna) {

        super(contenedor, fila, columna);
        estado = new Vacio(this);
    }

    void cambiarA(EstadoDelCasillero nuevoEstado) {

        estado = nuevoEstado;
    }

    void agregar(Pieza unaPieza) {

        tablero.fueAgregadaEn(this, unaPieza);
    }

    @Override
    public Casillero obtenerContiguoEn(Direccion direccionElegida) {

        return tablero.obtenerCasilleroEn(direccionElegida.trasladar(coordenadas));
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
    public void aceptar(ConsumidorDeCasilleros unConsumidor) {

        estado.alAceptar(unConsumidor);
    }

    @Override
    public void aceptar(VisitanteDeCasilleros unVistante) {

        unVistante.siEsInterior(this);
    }

    @Override
    public String toString() {

        return "Casillero" + coordenadas + " -> " + escanear();
    }
}
