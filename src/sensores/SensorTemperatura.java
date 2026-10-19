package sensores;

import java.util.Random;

/**
 * Sensor de temperatura. Rango válido: [-10, 50] °C.
 */
public class SensorTemperatura extends Sensor {

    public static final int MIN = -10;
    public static final int MAX = 50;
    private static final Random RND = new Random();

    public SensorTemperatura(String id, int capacidad) {
        super(id, "C", capacidad);
    }

     @Override
     public int generarLectura() {
        // 1. Generar valor aleatorio en [MIN-5, MAX+5]
        int minimoAceptado = MIN - 5;
        int maximoAceptado = MAX + 5;
        // nextInt(bound) genera desde 0 (inclusivo) hasta bound (exclusivo)
        int valor = RND.nextInt((maximoAceptado - minimoAceptado) + 1) + minimoAceptado;
        registrarLectura(valor);
        return valor;
    }

    @Override
    public boolean fueraDeRango(int valor) {
        // Retorna true si el valor sale de los límites definidos
        return valor < MIN || valor > MAX;
    }
}