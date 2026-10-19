package red;

import sensores.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nodo IoT que COMPONE sensores.
 * Los sensores se crean dentro del nodo (composición real).
 */
public class NodoIoT {

    private String id;
    private ArrayList<Sensor> sensores;

    public NodoIoT(String id) {
        this.id = id;
        this.sensores = new ArrayList<>();
    }

    /**
     * Crea y agrega un sensor del tipo indicado dentro del nodo.
     * Tipo: "TEMPERATURA", "HUMEDAD", "LUMINOSIDAD".
     * Retorna el sensor creado, o null si el tipo es inválido.
     */
    public Sensor agregarSensor(String tipo, String idSensor, int capacidad) {
        Sensor nuevoSensor = null;

        // Polimorfismo: instanciamos la subclase correspondiente según el tipo
        // Nota: Si tus constructores de las subclases no piden la unidad (ej. "°C"), omítela.
        switch (tipo) {
            case "TEMPERATURA":
                nuevoSensor = new SensorTemperatura(idSensor, capacidad);
                break;
            case "HUMEDAD":
                nuevoSensor = new SensorHumedad(idSensor, capacidad);
                break;
            case "LUMINOSIDAD":
                nuevoSensor = new SensorLuminosidad(idSensor,capacidad);
                break;
        }

        // Composición real: el nodo es dueño y guarda el sensor internamente
        if (nuevoSensor != null) {
            this.sensores.add(nuevoSensor);
        }

        return nuevoSensor; // Retorna null si el tipo fue inválido
    }

    /**
     * Promedio de temperatura del nodo. Retorna 0 si no hay sensores de temperatura.
     * Pista: usar instanceof para filtrar.
     */
    public double promedioTemperatura() {
        double suma = 0;
        int contador = 0;

        for (Sensor s : sensores) {
            if (s instanceof SensorTemperatura) {
                suma += s.promedioUltimasLecturas();
                contador++;
            }
        }

        return (contador == 0) ? 0 : (suma / contador);
    }

    /**
     * Lista de sensores cuya última lectura está fuera de rango.
     */
    public List<Sensor> sensoresFueraDeRango() {
        List<Sensor> fueraDeRango = new ArrayList<>();

        for (Sensor s : sensores) {
            if (s.getCantidadLecturas() > 0) {
                // Calcular el índice de la ÚLTIMA lectura ingresada en el buffer circular
                int indiceUltimaLectura = s.getIndiceActual() - 1;
                if (indiceUltimaLectura < 0) {
                    indiceUltimaLectura = s.getUltimasLecturas().length - 1;
                }

                int ultimaLectura = s.getUltimasLecturas()[indiceUltimaLectura];

                // Usamos el método abstracto implementado en las subclases
                if (s.fueraDeRango(ultimaLectura)) {
                    fueraDeRango.add(s);
                }
            }
        }

        return fueraDeRango;
    }

    public String getId() { return id; }
    public ArrayList<Sensor> getSensores() { return sensores; }
}