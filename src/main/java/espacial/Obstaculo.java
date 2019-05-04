package espacial;

public interface Obstaculo {

    /**
     * @pre el Obstáculo fue chocado por {@code piezaMovil}.
     * @param piezaMovil
     */
    void fueChocadaPor(PiezaMovil piezaMovil);
}
