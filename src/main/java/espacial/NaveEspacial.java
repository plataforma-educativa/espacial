package espacial;

import espacial.utiles.Nombre;

public interface NaveEspacial extends Pieza, Chocable {

    void despegar();

    void moverEn(Direccion direccionElegida);

    int obtenerCantidadDeTorpedosDeFotones();

    void atacarEn(Direccion direccionElegida);

    EspectroEspacial escanearEn(Direccion direccionElegida);

    int buscarEn(Direccion direccionElegida, SustanciaEspacial unaSustancia);

    void cargarDesde(Direccion direccionElegida, Carga unaCarga);

    void descargarEn(Direccion direccionElegida, Carga unaCarga);

    int obtenerNivelDeEscudos();

    void fueAmarradaCon(Amarre amarre);

    @Override
    default void chocarCon(Obstaculo obstaculo) {

        obstaculo.fueChocadaPor(this);
    }

    int obtenerNivelDeCarga();

    Nombre nombrar();
}
