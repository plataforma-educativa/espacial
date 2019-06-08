package espacial;

public enum SustanciaEspacial {

    ANTIMATERIA {

        @Override
        public void cargar(int cantidad, Deposito deposito) {

            deposito.cargarAntimateria(cantidad);
        }

        @Override
        public void descargar(int cantidad, Deposito deposito) {

            deposito.descargarAntimateria(cantidad);
        }

        @Override
        public int buscar(Deposito deposito) {

            return deposito.contarAntimateria();
        }
    };

    public Carga por(int cantidad) {

        return new CargaIndividual(cantidad, this);
    }

    public abstract void cargar(int cantidad, Deposito deposito);

    public abstract void descargar(int cantidad, Deposito deposito);

    public abstract int buscar(Deposito deposito);
}
