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
    private var visitadoRecursividad: Array<Boolean>

    /**
     * Implementacion de algoritmo DFS Precondicion: g es un grafo Postcondicion: Se recorrieron
     * todos los vertices del grafo. Tiempo de complejidad: O(|V|+|E|).
     */
    init {
        visitadoRecursividad = Array(g.obtenerNumeroDeVertices()) { false }

        for (i in 0 until n) {
            /* Solo se ejecuta dfsVisit si no se ha visitado el vertice y 
             */

            if (!visitado[i] && n - i > escaleraMasLarga.size) {
                dfsVisit(g, i)
                println("Visitados Recursion del $i: " + visitadoRecursividad.toList())
                visitadoRecursividad = Array(g.obtenerNumeroDeVertices()) { false }
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
        visitado[u] = true

        println(escalera)

        for (ady in g.adyacentes(u)) {
            println("Posible tamano de cadena a formar desde ${ady.b}:   ${n-ady.b + escalera.size} ")
            // Se evita entrar a vertices que no son capaces de formar una cadena mas larga que la que esta actualmente almacenada 
            if (n - ady.b + escalera.size > escaleraMasLarga.size) {
                dfsVisit(g, ady.b)
            }
        }

        // Si la escalera temporal tiene mayor tamano, entonces es la mas larga
        if (escalera.size > escaleraMasLarga.size) {
            escaleraMasLarga = escalera.toMutableList()
            println("MAS LARGA: ")
        }
        println("Eliminando: " + escalera.removeLast())
    }

    fun caminoMasLargo(): Iterable<Int> {
        return escaleraMasLarga
    }
}
