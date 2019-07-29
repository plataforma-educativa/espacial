package espacial.piezas;

import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

class ContenedorDeSustanciaObservadoTest extends TestDePiezaObservada {

    private ContenedorDeSustancia unContenedor;

    @Override
    protected Pieza unaPieza() {

        return unContenedor;
    }

    @Test
    void fueDestruida() {

        dadoQue(fueCreadoUnContenedor());
        dadoQue(fueRegistradoUnObservador());

        repetir(5, i -> unContenedor.fueAtacadoCon(new AtaqueConTorpedoDeFotones()));

        comprobarQue(notificoAlObservorDeLaDestruccion());
    }

    private Precondicion fueCreadoUnContenedor() {

        return pre(condicion -> {

            unContenedor = new ContenedorDeAntimateria(100);
            unContenedor.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void cambioElEstadoDeUnContenedorDeAntimateria() {

        dadoQue(fueCreadoUnContenedorDeAntimateria());
        dadoQue(fueRegistradoUnObservador());

        unContenedor.recibir(SustanciaEspacial.ANTIMATERIA.por(20));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    private Precondicion fueCreadoUnContenedorDeAntimateria() {

        return pre(condicion -> {

            unContenedor = new ContenedorDeAntimateria(100);
            unContenedor.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void cambioElEstadoDeUnContenedorDeMetal() {

        dadoQue(fueCreadoUnContenedorDeMetal());
        dadoQue(fueRegistradoUnObservador());

        unContenedor.recibir(SustanciaEspacial.METAL.por(50));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    private Precondicion fueCreadoUnContenedorDeMetal() {

        return pre(condicion -> {

            unContenedor = new ContenedorDeMetal();
            unContenedor.fueColocadaEn(UN_CASILLERO);
        });
    }

    @Test
    void cambioElEstadoDeUnContenedorDeCristal() {

        dadoQue(fueCreadoUnContenedorDeCristal());
        dadoQue(fueRegistradoUnObservador());

        unContenedor.recibir(SustanciaEspacial.CRISTAL.por(50));

        comprobarQue(notificoAlObservadoDelCambioDeEstado());
    }

    private Precondicion fueCreadoUnContenedorDeCristal() {

        return pre(condicion -> {

            unContenedor = new ContenedorDeCristal();
            unContenedor.fueColocadaEn(UN_CASILLERO);
        });
    }
}
