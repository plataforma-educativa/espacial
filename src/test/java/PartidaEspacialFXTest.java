import espacial.partidas.PartidaEspacialFX;

import java.util.concurrent.TimeUnit;

class PartidaEspacialFXTest {

    public static void main(String[] args) {

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


        //new Nave().despegar();

        PartidaEspacialFX partidaEspacial = new PartidaEspacialFX(batalla.obtenerTablero());
        partidaEspacial.iniciar();

        try {

            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}