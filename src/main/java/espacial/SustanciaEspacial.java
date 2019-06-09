package espacial;

import espacial.excepciones.ParametroInvalido;

public enum SustanciaEspacial {

    ANTIMATERIA {

        @Override
        public void cargar(int cantidad, Deposito enDeposito) {

            enDeposito.cargarAntimateria(cantidad);
        }

        @Override
        public void descargar(int cantidad, Deposito enDeposito) {

            enDeposito.descargarAntimateria(cantidad);
        }

        @Override
        public int buscarEn(Deposito deposito) {

            return deposito.contarAntimateria();
        }
    },

    METAL {

        @Override
        public void cargar(int cantidad, Deposito enDeposito) {

            enDeposito.cargarMetal(cantidad);
        }

        @Override
        public void descargar(int cantidad, Deposito enDeposito) {

            enDeposito.descargarMetal(cantidad);
        }

        @Override
        public int buscarEn(Deposito deposito) {

            return deposito.contarMetal();
        }
    },

    CRISTAL {

        @Override
        public void cargar(int cantidad, Deposito enDeposito) {

            enDeposito.cargarCristal(cantidad);
        }

        @Override
        public void descargar(int cantidad, Deposito enDeposito) {

            enDeposito.descargarCristal(cantidad);
        }

        @Override
        public int buscarEn(Deposito deposito) {

            return deposito.contarCristal();
        }
    };

    public Carga por(int cantidad) {

        if (cantidad <= 0) {

            throw new ParametroInvalido("La cantidad de Sustancia debe ser mayor a 0");
        }

        return new CargaIndividual(cantidad, this);
    }

    public abstract void cargar(int cantidad, Deposito enDeposito);

    public abstract void descargar(int cantidad, Deposito enDeposito);

    public abstract int buscarEn(Deposito deposito);
}
