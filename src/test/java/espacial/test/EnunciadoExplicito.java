package espacial.test;

public class EnunciadoExplicito implements Condicion.Enunciado {

    private String texto;

    public EnunciadoExplicito(String plantilla, Object... parametros) {

        texto = String.format(plantilla, parametros);
    }

    @Override
    public String describirFallo(Throwable causa) {

        return texto;
    }
}
