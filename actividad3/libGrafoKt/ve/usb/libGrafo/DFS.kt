package ve.usb.libGrafo
import java.util.LinkedList

 /**
  * Implementación del algoritmo DFS.
  * Con la creación de la instancia, se ejecuta el algoritmo DFS
  * desde todos los vértices del grafo
 */
public class DFS(val g: GrafoDirigido) {
    private var vertices: Array<VerticeDFS> 
    private var backEdges: LinkedList<Lado> = LinkedList()
    private var tiempo: Int

    /**
     * Implementacion de algoritmo DFS
     * Precondicion: g es un grafo
     * Postcondicion: Se recorrieron todos los vertices del grafo.
     * Tiempo de complejidad: O(|V|+|E|).
     */
    init {
        vertices = Array(g.obtenerNumeroDeVertices()) { it -> VerticeDFS(it) }
        tiempo = 0

        for (u in vertices) {
            if (u.color == Color.BLANCO) dfsVisit(g, u.valor)
        }
    }

    /**
     * Implementación de algoritmo dfsVisit(g).
     * Precondicion: [g] es un grafo, [u] un vertice desde donde se comienza a recorrer.
     * Postcondicion: Se recorrieron todos los vertices alcanzables desde [u] en el grafo.
     * Tiempo de complejidad: O(|E|).
    */
    private fun dfsVisit(g: Grafo, u: Int) {
        tiempo++
        vertices[u].ti = tiempo
        vertices[u].color = Color.GRIS

        for (ady in g.adyacentes(u)) {
            // Si el color es blanco, pertenece al arbol
            if (vertices[ady.b].color == Color.BLANCO) {
                vertices[ady.b].pred = vertices[u]
                dfsVisit(g, ady.b)
            } else if (vertices[ady.b].color == Color.GRIS) {
                backEdges.add(ady)
            }
        }
        vertices[u].color = Color.NEGRO
        tiempo++
        vertices[u].tf = tiempo
    }


    /**
     * Retorna el predecesor de un vertice [v]. Si [v] no tiene predecesor se retorna null.
     * Precondicion: [v] pertenece al grafo.
     * Postcondicion: true.
     * Tiempo de complejidad: O(1).
    */
    fun obtenerPredecesor(v: Int): Int? {
        // Verificar que el vertice pertenece al grafo
        if (!estaElVerticeEnElGrafo(v)) {
            throw RuntimeException("El vertice dado no pertenece al grafo.")
        }

        return vertices[v].pred?.valor
    }

    /**
     * Retorna un par con el tiempo inical y final de un vértice [v] despues de la ejecución de DFS.
     * Precondicion: [v] pertenece al grafo.
     * Postcondicion: true.
     * Tiempo de complejidad: O(1).
    */
    fun obtenerTiempos(v: Int): Pair<Int, Int> {
        // Verificar que el vertice pertenece al grafo
        if (!estaElVerticeEnElGrafo(v)) {
            throw RuntimeException("El vertice dado no esta en el grafo.")
        }

        return Pair(vertices[v].ti, vertices[v].tf)
    }

    /**
     * Retorna true si hay back edges o false en caso contrario.
     * Precondicion: True.
     * Postcondicion: True.
     * Tiempo de complejidad: O(1).
    */
    fun hayLadosDeVuelta(): Boolean = backEdges.size != 0 

    /**
     * Retorna los back edges. Si no existen ese tipo de lados, entonces se lanza un
     * RuntimeException.
     * Precondicion: True.
     * Postcondicion: True.
     * Tiempo de complejidad: O(1).
    */
    fun ladosDeVuelta(): Iterator<Lado> {
        if (!hayLadosDeVuelta()) {
            throw RuntimeException("No hay back edges.")
        }

        return backEdges.iterator()
    }

   /**
     * Indica si el vertice [v] pertenece al grafo. En caso afirmativo retorna true de lo
     * contrario false.
     * Precondicion: True.
     * Postcondicion: True.
     * Tiempo de complejidad: O(1).
    */
    fun estaElVerticeEnElGrafo(v: Int): Boolean = v >= 0 && v < g.obtenerNumeroDeVertices()
}
