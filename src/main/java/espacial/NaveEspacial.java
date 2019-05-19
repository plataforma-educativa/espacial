package espacial;

public interface NaveEspacial extends Pieza, Chocable {

    void despegar();

    void moverEn(Direccion direccionElegida);

    void atacarEn(Direccion direccionElegida);

    int obtenerNivelDeEscudos();

    void fueAmarradaCon(Amarre amarre);

    @Override
    default void chocarCon(Obstaculo obstaculo) {

        obstaculo.fueChocadaPor(this);
    }
}
