package ve.usb.libGrafo

/**
 * Implementación del algoritmo DFS. Con la creación de la instancia, se ejecuta el algoritmo DFS
 * desde todos los vértices del grafo
 */
public class DFS(val g: Grafo) {
    private var escaleraMasLarga: MutableList<Int> = mutableListOf()
    private var escalera: MutableList<Int> = mutableListOf()
    private var n = g.obtenerNumeroDeVertices()
    private var visitado = Array(n) { false }

    /**
     * Implementacion de algoritmo DFS Precondicion: g es un grafo Postcondicion: Se recorrieron
     * todos los vertices del grafo. Tiempo de complejidad: O(|V|+|E|).
     */
    init {
        for (i in 0 until n) {
            // Se evita entrar a vertices que no son capaces de formar una escalera mas larga que la que esta actualmente almacenada 
            if (!visitado[i] && n - i > escaleraMasLarga.size) {
                dfsVisit(g, i)
            }
        }
    }

    /**
     * Implementación de algoritmo dfsVisit(g). Precondicion: [g] es un grafo, [u] un vertice desde
     * donde se comienza a recorrer. Postcondicion: Se recorrieron todos los vertices alcanzables
     * desde [u] en el grafo. Tiempo de complejidad: O(|E|).
     */
    private fun dfsVisit(g: Grafo, u: Int) {

        // Se anade el vertice a la escalera temporal 
        escalera.add(u)
        // Se marca como visitado
        visitado[u] = true

        for (ady in g.adyacentes(u)) {
            // Se evita entrar a vertices que no son capaces de formar una cadena mas larga que la que esta actualmente almacenada 
            if (n - ady.b + escalera.size > escaleraMasLarga.size) {
                dfsVisit(g, ady.b)
            }
        }

        // Si la escalera temporal tiene mayor tamano, entonces es la mas larga
        if (escalera.size > escaleraMasLarga.size) {
            escaleraMasLarga = escalera.toMutableList()
        }
        // Se vacia eliminando el ultimo elemento anadido a la escalera
        escalera.removeLast()
    }

    fun caminoMasLargo(): Iterable<Int> {
        return escaleraMasLarga
    }
}
