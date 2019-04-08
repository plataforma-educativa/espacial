package espacial.test;

public class Postcondicion extends Condicion {

    protected Postcondicion(String descripcion, Ejecutable ejecutable) {

        super(descripcion, ejecutable);
    }

    @Override
    protected String describir() {

        return "No se pudo comprobar que " + super.describir();
    }
    
}