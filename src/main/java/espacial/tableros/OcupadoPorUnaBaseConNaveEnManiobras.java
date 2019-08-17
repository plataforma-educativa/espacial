package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Partidario;
import espacial.Pieza;
import espacial.SustanciaEspacial;

public class OcupadoPorUnaBaseConNaveEnManiobras extends EstadoDelCasillero {

    private final Pieza base;
    private final Pieza nave;
    
    public OcupadoPorUnaBaseConNaveEnManiobras(CasilleroInterior casillero,
                                               Pieza base,
                                               Pieza nave) {

        super(casillero);
        this.base = base;
        this.nave = nave;
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
    
        return nave;
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {

        unaPieza.chocarCon(nave);
    }

    @Override
    public void alDesocupar() {
        
        cambiarPor(new OcupadoPorUnaBase(contexto, base));
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
        destino.recibirPiezaDesde(contexto);
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {
        
        origen.obtenerPieza().chocarCon(nave);
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

        nave.fueAtacadoCon(unAtaque);
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
    public void alAceptar(Casillero.Consumidor unConsumidor) {

        unConsumidor.aceptar(contexto, base, nave);
    }

}
