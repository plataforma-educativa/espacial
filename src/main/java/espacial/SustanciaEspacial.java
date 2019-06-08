package espacial;

public enum SustanciaEspacial {

    ANTIMATERIA {

        @Override
        public void cargar(int cantidad, Transporte transporte) {

            transporte.cargarAntimateria(cantidad);
        }

        @Override
        public void descargar(int cantidad, Transporte transporte) {

            transporte.descargarAntimateria(cantidad);
        }

        @Override
        public int buscar(Transporte transporte) {

            return transporte.contarAntimateria();
        }
    };

    public Carga por(int cantidad) {

        return new CargaIndividual(cantidad, this);
    }

    public abstract void cargar(int cantidad, Transporte transporte);

    public abstract void descargar(int cantidad, Transporte transporte);

    public abstract int buscar(Transporte transporte);
}
