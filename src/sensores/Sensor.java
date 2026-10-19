package sensores;

/**
 * Clase abstracta que representa un sensor genérico de la red IoT.
 * Mantiene un historial de las últimas N lecturas en un arreglo de enteros
 * (buffer circular).
 */
public abstract class Sensor {

    private String id;
    private String unidad;
    private int[] ultimasLecturas;   // int[] obligatorio
    private int indiceActual;
    private int cantidadLecturas;

    public Sensor(String id, String unidad, int capacidad) {
        this.id = id;
        this.unidad = unidad;
        this.ultimasLecturas = new int[capacidad];
        this.indiceActual = 0;
        this.cantidadLecturas = 0;
    }

    /**
     * Registra un valor en el buffer circular de tamaño fijo.
     * Si el buffer está lleno, sobrescribe la lectura más antigua.
     */
    public void registrarLectura(int valor) {
        // Guarda el valor en la posición actual
        this.ultimasLecturas[this.indiceActual] = valor;

        // Avanza el índice circularmente usando el módulo de la longitud del arreglo
        this.indiceActual = (this.indiceActual + 1) % this.ultimasLecturas.length;

        // Incrementa la cantidad de lecturas hasta llegar al tope de la capacidad
        if (this.cantidadLecturas < this.ultimasLecturas.length) {
            this.cantidadLecturas++;
        }
    }

    /**
     * Promedio de las lecturas registradas. Si no hay, retorna 0.
     */
    public double promedioUltimasLecturas() {
        if (this.cantidadLecturas == 0) {
            return 0;
        }

        double suma = 0;
        // Solo itera hasta la cantidad de lecturas reales (no toda la capacidad si no está llena)
        for (int i = 0; i < this.cantidadLecturas; i++) {
            suma += this.ultimasLecturas[i];
        }

        return suma / this.cantidadLecturas;
    }

    public abstract int generarLectura();

    /**
     * Indica si un valor está fuera del rango esperado.
     * Cada subclase define su rango.
     */
    public abstract boolean fueraDeRango(int valor);

    // Getters encapsulados
    public String getId() { return id; }
    public String getUnidad() { return unidad; }
    public int[] getUltimasLecturas() { return ultimasLecturas; }
    public int getCantidadLecturas() { return cantidadLecturas; }
    public int getIndiceActual() { return indiceActual; }

    protected void setIndiceActual(int indiceActual) { this.indiceActual = indiceActual; }
    protected void setCantidadLecturas(int cantidadLecturas) { this.cantidadLecturas = cantidadLecturas; }
}