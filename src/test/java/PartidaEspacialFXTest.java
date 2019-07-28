import espacial.partidas.PartidaEspacialFX;

import java.util.concurrent.TimeUnit;

class PartidaEspacialFXTest {

    public static void main(String[] args) throws InterruptedException {

        BatallaEspacial batalla = new BatallaEspacial();
        Nave nave = new Nave();
        nave.despegar();
        nave.avanzarAlSur();
        nave.avanzarAlSur();
        nave.avanzarAlSur();

        Nave nave2 = new Nave();
        nave2.despegar();
        nave2.avanzarAlSur();
        nave2.avanzarAlSur();
        nave2.avanzarAlSur();
        nave2.avanzarAlOeste();

        //new Nave().despegar();

        PartidaEspacialFX partidaEspacial = new PartidaEspacialFX(batalla.obtenerTablero());
        partidaEspacial.iniciar();

        Nave atacante = new Nave();
        atacante.despegar();
        TimeUnit.SECONDS.sleep(1);
        atacante.avanzarAlNorte();
        TimeUnit.SECONDS.sleep(5);
        atacante.atacarAlSur();
        TimeUnit.SECONDS.sleep(1);

        nave2.cargarDesdeOeste(Sustancia.ANTIMATERIA, 1);

        for (int i = 0; i < 10; i++) {

            TimeUnit.SECONDS.sleep(1);
            nave.avanzarAlEste();
            TimeUnit.SECONDS.sleep(1);
            nave.avanzarAlSur();
        }


    }

}