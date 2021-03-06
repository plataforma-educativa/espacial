package espacial.utiles;

import espacial.excepciones.ErrorEspacial;

import java.util.List;

class AleatorioConsumiendo<T> extends Aleatorio<T> {

    private final List<T> valores;

    public AleatorioConsumiendo(List<T> disponibles) {

        valores = disponibles;
    }

    @Override
    public T obtener() {

        if (valores.isEmpty()) {

            throw new ErrorEspacial("No quedan valores aleatorios disponibles");
        }

        return valores.remove(implementacion.nextInt(valores.size()));
    }
}
