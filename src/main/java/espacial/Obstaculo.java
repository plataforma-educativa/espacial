package espacial;

public interface Obstaculo {

    /**
     * @pre el Obstáculo fue chocado por {@code chocable}.
     * @param chocable
     */
    void fueChocadaPor(Chocable chocable);
}
