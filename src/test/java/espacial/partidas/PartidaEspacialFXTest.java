package espacial.partidas;

import espacial.Tablero;

class PartidaEspacialFXTest {

    public static void main(String[] args) {

        PartidaEspacialFX partidaEspacial = new PartidaEspacialFX(new Tablero());
        partidaEspacial.iniciar();
    }

}