package espacial.tableros;

import espacial.Carga;
import espacial.Casillero;
import espacial.Chocable;
import espacial.ConsumidorDeCasilleros;
import espacial.Coordenadas;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Obstaculo;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;

public class CasilleroBorde implements Casillero, Obstaculo {

    private final Tablero tablero;

    public CasilleroBorde(Tablero contenedor) {

        tablero = contenedor;
    }

    @Override
    public Tablero obtenerTablero() {

        return tablero;
    }

    @Override
    public Coordenadas obtenerCoordenadas() {

        return null;
    }

    @Override
    public int obtenerNumero() {

        return 0;
    }

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public int buscar(SustanciaEspacial antimateria) {

        return 0;
    }

    @Override
    public Casillero obtenerContiguoEn(Direccion direccion) {

        return this;
    }

    @Override
    public void moverPiezaA(Casillero destino) {
        
        throw new LaOperacionNoEstaSoportada("CasilleroBorde.moverPiezaA(Casillero)");
    }
    
    @Override
    public void ocuparCon(Pieza unaPieza) {
        
        throw new LaOperacionNoEstaSoportada("CasilleroBorde.ocuparCon(Pieza)");
    }

    @Override
    public void desocupar() {

        throw new LaOperacionNoEstaSoportada("CasilleroBorde.desocupar()");
    }

    @Override
    public Pieza obtenerPieza() {

        return null;
    }

    @Override
    public void recibirPiezaDesde(Casillero origen) {
        
        origen.obtenerPieza().chocarCon(this);
    }

    @Override
    public void fueChocadaPor(Chocable chocable) {
        
        chocable.chocoContraElBordeDelTablero();
    }

    @Override
    public void entregar(Carga unaCarga) {

        throw new NoPuedeEntregarUnaCarga(unaCarga);
    }

    @Override
    public void recibir(Carga unaCarga) {

        throw new NoPuedeRecibirUnaCarga(unaCarga);
    }

    @Override
    public void aceptar(ConsumidorDeCasilleros unConsumidor) {

    }

    @Override
    public String toString() {

        return "Casillero BORDE";
    }
}