package espacial.utiles;

public class Nombrando {

    private NombreEditable instancia;

    Nombrando(String valor) {

        instancia = new NombreSimple(valor);
    }

    public Nombrando porque(String explicacion) {

        instancia.darExplicacion(explicacion);

        return this;
    }

    public Nombre obtener() {

        return instancia;
    }

    private interface NombreEditable extends Nombre {

        void darExplicacion(String explicacion);

        @Override
        default Nombre adicionando(String calificacion) {

            return new NombreCalificado(this, calificacion);
        }

        default String obtenerEntreComillas() {

            return "'" + obtener() + "'";
        }
    }

    private static class NombreSimple implements NombreEditable {

        private final String valor;

        private String explicacion;

        private NombreSimple(String conValor) {

            valor = conValor;
        }

        @Override
        public void darExplicacion(String unaExplicacion) {

            explicacion = unaExplicacion;
        }

        @Override
        public String obtener() {

            return valor;
        }

        @Override
        public String explicar() {

            return explicacion;
        }

        @Override
        public String toString() {

            return obtenerEntreComillas();
        }
    }

    static class NombreCalificado implements NombreEditable {

        private NombreEditable nombre;

        private String calificacion;

        private NombreCalificado(NombreEditable unNombre, String conCalificacion) {

            nombre = unNombre;
            calificacion = conCalificacion;
        }

        @Override
        public String obtener() {

            return nombre.obtener() + " " + calificacion;
        }

        @Override
        public String explicar() {

            return nombre.explicar();
        }

        @Override
        public void darExplicacion(String explicacion) {

            nombre.darExplicacion(explicacion);
        }

        @Override
        public String toString() {

            return obtenerEntreComillas();
        }
    }
}
