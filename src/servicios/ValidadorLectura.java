package servicios;

/**
 * Servicio que valida lecturas según el tipo de sensor.
 * Rangos: TEMPERATURA [-10,50], HUMEDAD [0,100], LUMINOSIDAD [0,2000].
 */
public class ValidadorLectura {

    public boolean esValida(String tipo, int valor) {
        switch (tipo) {
            case "TEMPERATURA":
                return valor >= -10 && valor <= 50;
            case "HUMEDAD":
                return valor >= 0 && valor <= 100;
            case "LUMINOSIDAD":
                return valor >= 0 && valor <= 2000;
            default:
                return false;
        }
    }
}
