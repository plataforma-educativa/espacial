package espacial.test;

public class EnunciadoExplicito implements Condicion.Enunciado {

    private final String texto;

    public EnunciadoExplicito(String plantilla, Object... parametros) {

        texto = String.format(plantilla, parametros);
    }

    @Override
    public String describirFallo(Throwable causa) {

        return texto;
    }
}
