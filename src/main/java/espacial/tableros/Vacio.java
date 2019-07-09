package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.ConsumidorDeCasilleros;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.excepciones.Defecto;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;

public class Vacio extends EstadoDelCasillero {

    public Vacio(CasilleroInterior contexto) {
        
        super(contexto);
    }

    @Override
    public EspectroEspacial alEscanear() {
        
        return EspectroEspacial.VACIO;
    }

    @Override
    public int alBuscar(SustanciaEspacial unaSustancia) {

        return 0;
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {

        cambiarPor(estadoResultanteSegunElTipoDe(unaPieza));

        unaPieza.fueColocadaEn(contexto);

        contexto.fueAgregadaAlTablero(unaPieza);
    }

    private EstadoDelCasillero estadoResultanteSegunElTipoDe(Pieza unaPieza) {

        OcuparCasilleroVacio ocuparCasilleroVacio = new OcuparCasilleroVacio(contexto);

        unaPieza.aceptar(ocuparCasilleroVacio);

        return ocuparCasilleroVacio.obtenerEstadoResultante();
    }

    @Override
    public void alDesocupar() {
        
        throw new Defecto("No se puede desocupar un Casillero Vacío");
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
        throw new Defecto("No se puede mover una Pieza desde un Casillero Vacío");
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {

        Pieza pieza = origen.obtenerPieza();

        origen.desocupar();

        cambiarPor(estadoResultanteSegunElTipoDe(pieza));

        pieza.fueColocadaEn(contexto);
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

    }

    @Override
    public void alEntregar(Carga unaCarga) {

        throw new NoPuedeEntregarUnaCarga(unaCarga);
    }

    @Override
    public void alRecibir(Carga unaCarga) {

        throw new NoPuedeRecibirUnaCarga(unaCarga);
    }

    @Override
    public void alAceptar(ConsumidorDeCasilleros unConsumidor) {

        unConsumidor.aceptar(contexto);
    }

    @Override
    public Pieza alObtenerPieza() {

        throw new Defecto("No se puede obtener Pieza en un Casillero Vacío");
    }
}
