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


            for (int i = 0; i < 10; i++) {

                TimeUnit.SECONDS.sleep(5);
                System.out.println("avanzarAlOeste " + i);
                nave.avanzarAlEste();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}