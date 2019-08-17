import espacial.Tablero;

public enum Espacial {

    BASE {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarBase();
        }
    },
    NAVE {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarNave();
        }
    },
    ASTEROIDE {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarAsteroide();
        }
    },
    CONTENEDOR_ANTIMATERIA {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarContenedorDeAntimateria();
        }
    },
    CONTENEDOR_CRISTAL {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarContenedorDeCristal();
        }
    },
    CONTENEDOR_METAL {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarContenedorDeMetal();
        }
    },
    AGUJERO_NEGRO {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarAgujeroNegro();
        }
    },
    BASE_DESCONOCIDA {

        @Override
        public void colocarEn(Tablero tablero, int fila, int columna) {

            tablero.enCasillero(fila, columna).colocarBaseDesconocida();
        }
    };

    public abstract void colocarEn(Tablero tablero, int fila, int columna);
}
