package espacial.test;

public class Precondicion extends Condicion {

    protected Precondicion(String descripcion, Ejecutable ejecutable) {
        
        super(descripcion, ejecutable);
    }
    
    @Override
    protected String describir() {

        return "No se fue posible establecer que " + super.describir();
    }

}
