package ve.usb.libGrafo

/**
 * Modificacion del algoritmo DFS. Con la creación de la instancia, se ejecuta el algoritmo DFS
 * desde todos los vértices del grafo
 */
public class DFS(val g: Grafo) {
    // Almacenara el camino mas largo
    private var caminoMasLargo: MutableList<Int> = mutableListOf()
    private var caminoTemporal: MutableList<Int> = mutableListOf()
    private var n = g.obtenerNumeroDeVertices()
    private var visitado = Array(n) { false }

    /**
     * Implementacion de algoritmo DFS modificado para conseguir el camino con mas lados en un DAG
     * cuyos vertices [0, 1, ..., n-1] mantienen un orden topologico
     * Precondicion: [g] es un DAG. Los vertices de [g] tienen un orden topologico
     * Postcondicion: Se recorrieron todos los vertices del grafo 
     * y se consiguio el camino con mayor cantidad de lados
     * Tiempo de complejidad: O(|V|+|E|).
     */
    init {
        for (i in 0 until n) {

            // Tamano maximo del camino que se puede formar desde i (Por ser un orden topologico)
            var tamMax = n - i

            // Para optimizar la ejecucion, solo se entra a los vertices capaces de formar un camino mas largo que el actual
            if (!visitado[i] && tamMax > caminoMasLargo.size) {
                dfsVisit(g, i)
            }
        }
    }

    /**
     * Implementación de algoritmo dfsVisit(g).
     * Precondicion: [g] es un grafo, [u] pertenece al grafo.
     * Postcondicion: Se recorrieron todos los vertices alcanzables desde [u] en el grafo.
     * Tiempo de complejidad: O(|E|).
     */
    private fun dfsVisit(g: Grafo, u: Int) {

        // Se anade el vertice a la camino temporal 
        caminoTemporal.add(u)

        // Se marca como visitado para evitar entrar a vertices repetidamente
        visitado[u] = true

        for (ady in g.adyacentes(u)) {

            // Tamano del camino temporal + el tamano maximo que se puede formar desde el vertice [ady.b]
            var tamcaminoTemporalMax = caminoTemporal.size + (n - ady.b)

            // Solo se entra a los vertices que pueden formar una camino mas largo que el actual 
            if (tamcaminoTemporalMax > caminoMasLargo.size) {
                dfsVisit(g, ady.b)
            }
        }

        // Si la camino temporal tiene mayor tamano, entonces es el mas largo
        if (caminoTemporal.size > caminoMasLargo.size) {
            caminoMasLargo = caminoTemporal.toMutableList()
        }

        // Se vacia eliminando el ultimo elemento anadido a la caminoTemporal
        caminoTemporal.removeLast()
    }

    /**
     * Retorna el camino mas largo del DAG
     * Precondicion: True
     * Postcondicion: [caminoMasLargo] es el camino con mayor cantidad de lados
     * Tiempo de ejecucion: O(1)
     */
    fun obtenerCaminoMasLargo(): List<Int> {
        return caminoMasLargo
    }
}
