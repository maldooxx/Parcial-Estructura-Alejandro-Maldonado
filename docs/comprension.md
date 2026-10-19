# Comprensión del diseño — Enunciado A
**Nombre:** Jose Alejandro Maldonado Martínez
**Fecha:** 19/10/2026

Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas
concisas (2–4 líneas por pregunta). Este documento evidencia que entendiste el
contrato de diseño **antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Sensor` es una clase abstracta y no una clase concreta? Menciona
qué método obliga a cada subclase a implementar y qué gana el sistema con eso.

> Sensor es como una plantilla general, no un objeto real. Sirve para obligar a que cada sensor específico tenga sus propias reglas, pero permitiendo que el sistema los maneje a todos por igual, sin enredarse preguntando de qué tipo son.

---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT ◆— Sensor` (composición)
y `CentroMonitoreo ◇— NodoIoT` (agregación). Da un ejemplo de qué pasaría con
los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina
el centro.

> Si eliminas un Nodo, sus sensores desaparecen con él porque le pertenecen por completo. En cambio, si eliminas el Centro de Monitoreo, los Nodos siguen funcionando por su cuenta.

---

### 3. Asociación
En `TecnicoMantenimiento — NodoIoT`, ¿quién es dueño de quién? ¿Por qué se
modela como N:M y no como composición?

> Los técnicos y los nodos trabajan juntos, pero ninguno es "dueño" del otro. Un técnico atiende varios nodos y un nodo recibe a varios técnicos; ambos existen de forma totalmente independiente.

---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo**
como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura`
como parámetro versus guardarlo en un campo?

> La lectura solo le "pide prestado" el validador un segundo para revisarse a sí misma. No se lo guarda para siempre porque no lo necesita todo el tiempo, lo que hace el programa más simple y ligero.

---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué
ventaja tiene él `int[]` con buffer circular frente a un `ArrayList` para el
historial de lecturas?

> Usamos una lista que puede estirarse (ArrayList) para los sensores porque podemos ir agregando más con el tiempo. Para el historial de lecturas usamos una de tamaño fijo (int[]) que funciona como una rueda: cuando se llena, simplemente va aplastando el dato más viejo y sigue girando.