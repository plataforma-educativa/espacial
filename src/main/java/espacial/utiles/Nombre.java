package espacial.utiles;

public class Nombre {

    private final String valor;

    private String explicacion;

    private Nombre(String conValor) {

        valor = conValor;
    }

    public static Nombrando sera(String valor) {

        return new Nombrando(valor);
    }

    public String obtener() {

        return valor;
    }

    public String explicar() {

        return explicacion;
    }

    @Override
    public String toString() {

        return "'" + obtener() + "'";
    }

    public static class Nombrando {

        private final Nombre instancia;


        private Nombrando(String valor) {

            instancia = new Nombre(valor);
        }

        public Nombrando porque(String explicacion) {

            instancia.explicacion = explicacion;

            return this;
        }

        public Nombre obtener() {

            return instancia;
        }
    }
}
