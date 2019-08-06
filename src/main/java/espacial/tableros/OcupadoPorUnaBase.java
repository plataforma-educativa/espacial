package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.ConsumidorDeCasilleros;
import espacial.EspectroEspacial;
import espacial.Partidario;
import espacial.Pieza;
import espacial.SustanciaEspacial;

public class OcupadoPorUnaBase extends EstadoDelCasillero {

    private final Pieza base;
    
    public OcupadoPorUnaBase(CasilleroInterior casillero, Pieza base) {

        super(casillero);
        this.base = base;
    }

    @Override
    public EspectroEspacial alEscanear() {

        return base.escanear();
    }

    @Override
    public int alBuscar(SustanciaEspacial unaSustancia) {

        return base.buscar(unaSustancia);
    }

    @Override
    public void alEvaluar(Partidario.Condicional condicional) {

        base.evaluar(condicional);
    }

    @Override
    public Pieza alObtenerPieza() {

        return base;
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {
        
        cambiarPor(new OcupadoPorUnaBaseConNaveEnManiobras(contexto, base, unaPieza));

        unaPieza.fueColocadaEn(contexto);

        contexto.fueAgregadaAlTablero(unaPieza);
    }

    @Override
    public void alDesocupar() {
        
        cambiarPor(new Vacio(contexto));
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
        destino.recibirPiezaDesde(contexto);
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {

        origen.obtenerPieza().chocarCon(base);
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

        base.fueAtacadoCon(unAtaque);
    }

    @Override
    public void alEntregar(Carga unaCarga) {

        base.entregar(unaCarga);
    }

    @Override
    public void alRecibir(Carga unaCarga) {

        base.recibir(unaCarga);
    }

    @Override
    public void alAceptar(ConsumidorDeCasilleros unConsumidor) {

        unConsumidor.aceptar(contexto, base);
    }
}
