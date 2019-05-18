package espacial;

public interface PiezaMovil extends Pieza, Chocable {
    
    void despegar();
    
    void moverEn(Direccion direccion);

    int obtenerNivelDeEscudos();
    
    void fueAmarradaCon(Amarre amarre);

    @Override
    default void chocarCon(Obstaculo obstaculo) {

        obstaculo.fueChocadaPor(this);
    }
}
