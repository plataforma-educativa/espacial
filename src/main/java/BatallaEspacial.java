public class BatallaEspacial {

    private static BatallaEspacial instancia = null;
    
    public BatallaEspacial() {

        instancia = this;
    }

    public static BatallaEspacial obtener() {

        return instancia;
    }

    public Nave[] obtenerNaves() {
        
        return null;
    }
}
