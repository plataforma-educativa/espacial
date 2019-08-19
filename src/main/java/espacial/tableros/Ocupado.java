package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Partidario;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.excepciones.ElCasilleroEstaOcupado;

public class Ocupado extends EstadoDelCasillero {

    private final Pieza pieza;

    public Ocupado(CasilleroInterior contexto, Pieza porPieza) {

        super(contexto);
        pieza = porPieza;
    }

    @Override
    public EspectroEspacial alEscanear() {

        return pieza.escanear();
    }

    @Override
    public int alBuscar(SustanciaEspacial unaSustancia) {

        return pieza.buscar(unaSustancia);
    }

    @Override
    public void alEvaluar(Partidario.Condicional condicional) {

        pieza.evaluar(condicional);
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {

        throw new ElCasilleroEstaOcupado(contexto);
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

        origen.obtenerPieza().chocarCon(pieza);
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

        pieza.fueAtacadoCon(unAtaque);
    }

    @Override
    public void alEntregar(Carga unaCarga) {

        pieza.entregar(unaCarga);
    }

    @Override
    public void alRecibir(Carga unaCarga) {

        pieza.recibir(unaCarga);
    }

    @Override
    public void alAceptar(Casillero.Consumidor unConsumidor) {

        unConsumidor.aceptar(contexto, pieza);
    }

    @Override
    public Pieza alObtenerPieza() {

        return pieza;
    }
}
