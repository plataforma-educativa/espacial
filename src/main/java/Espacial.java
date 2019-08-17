import espacial.Tablero;

public enum Espacial {

    BASE {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConBase();
        }
    },
    NAVE {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConNave();
        }
    },
    ASTEROIDE {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConAsteroide();
        }
    },
    CONTENEDOR_ANTIMATERIA {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConContenedorDeAntimateria();
        }
    },
    CONTENEDOR_CRISTAL {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConContenedorDeCristal();
        }
    },
    CONTENEDOR_METAL {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConContenedorDeMetal();
        }
    },
    AGUJERO_NEGRO {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConAgujeroNegro();
        }
    },
    BASE_DESCONOCIDA {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).ocuparConBaseDesconocida();
        }
    };

    public abstract void colocarEn(Tablero tablero, int fila, int columna);
}
