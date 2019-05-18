package espacial.piezas;

import java.util.Optional;

import espacial.*;
import espacial.excepciones.LaNaveNoEstaEnLaBase;
import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.piezas.rasgos.NaveChocable;

public class CazaEspacial implements PiezaMovil, NaveChocable {

    private int nivelDeEscudos = 100;
    private Optional<Casillero> casillero = Optional.empty();
    private Optional<Amarre> amarre = Optional.empty();

    @Override
    public void despegar() {

        Amarre amarreActual = amarre.orElseThrow(LaNaveNoEstaEnLaBase::new);

        amarreActual.soltar();
    }

    @Override
    public void fueColocadaEn(Casillero unCasillero) {
        
        casillero = Optional.of(unCasillero);
    }
    
    @Override
    public void fueAmarradaCon(Amarre unAmarre) {
    
        amarre = Optional.of(unAmarre);
    }
    
    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.NAVE;
    }

    @Override
    public void moverEn(Direccion direccionElegida) {
        
        Casillero origen = casillero.orElseThrow(LaNaveNoEstaEnUnCasillero::new);
        
        Casillero destino = origen.obtenerContiguoEn(direccionElegida);
        
        origen.moverPiezaA(destino);
    }

    @Override
    public int obtenerNivelDeEscudos() {
        
        return nivelDeEscudos;
    }

    @Override
    public int obtenerPuntos() {

        return obtenerNivelDeEscudos();
    }

    @Override
    public void disminuirNivelDeEscudosEn(int diferencia) {

        nivelDeEscudos -= diferencia;
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsNave(this);
    }
}
