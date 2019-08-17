package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.Chocable;
import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.Obstaculo;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.VisitanteDeCasilleros;
import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;

public class CasilleroBorde extends CasilleroDelTablero implements Obstaculo {

    public CasilleroBorde(TableroContenedor contenedor) {

        this(contenedor, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public CasilleroBorde(TableroContenedor contenedor, int fila, int columna) {

        super(contenedor, fila, columna);
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
    public void evaluar(Condicional condicional) {

        condicional.siEsNeutral();
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
    public void aceptar(Casillero.Consumidor unConsumidor) {

        unConsumidor.aceptar(this);
    }

    @Override
    public void aceptar(VisitanteDeCasilleros unVistante) {

        unVistante.siEsBorde(this);
    }

    @Override
    public String toString() {

        return "Casillero" + coordenadas + " -> BORDE";
    }

    @Override
    public void fueAtacadoCon(Ataque ataque) {

        /* No se ve afectado por ningún ataque */
    }
}