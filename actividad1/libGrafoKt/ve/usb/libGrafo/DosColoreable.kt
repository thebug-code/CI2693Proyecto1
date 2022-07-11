package ve.usb.libGrafo

/**
 * Implementación de un algoritmo basado en DFS que determina si un grafo
 * es dos coloreable o no. El algoritmo se ejecuta al momento de la creación 
 * de una instancia de la clase.
 */
public class DosColoreable(val g: GrafoNoDirigido) {
    private var vertices: Array<VerticeDosColoreable> 
    private var esBipartido: Boolean 
    
    /**
     * Ejecutar algoritmo DFS modificaso para determinar si el grafo [g] es bipartido.
     * Precondición: [g] es un grafo no dirigido.
     * Postcondicion: todos lo vértices del grafo fueron explorados.
     * Tiempo de la operación: O(|E|+|V|).
     */
    init {
        vertices = Array(g.obtenerNumeroDeVertices()) { VerticeDosColoreable(it) }
        esBipartido = true

        for (v in 0 until g.obtenerNumeroDeVertices()) {
            if (vertices[v].color == Color.BLANCO) dfsVisitColor(v)
        }
    }
    
    /** 
     * Recorre recursivamente todos lo vértices alcanzables desde [v]
     * en el grafo [g].
     * Precondición: [v] pertenece al grafo.
     * Postcondición: todos los vértices alcanzables desde [v] fueron 
     * visitados.
     */
    fun dfsVisitColor(v: Int) {
        vertices[v].color = Color.GRIS
        
        g.adyacentes(v).forEach {
            if (vertices[it.u].color == Color.BLANCO) {
                vertices[it.u].kcolor = if (vertices[v].kcolor == 1) 2 else 1
                dfsVisitColor(it.u)
            } else {
                if (vertices[v].kcolor == vertices[it.u].kcolor) esBipartido = false
            }
        }
        // Se termina de explorar v
        vertices[v].color = Color.NEGRO
    }
    
    /** 
     * Indica si el grafo [g] es bipartido o no, en caso afirmativo retorna true,
     * de lo contrario false.
     * Precondición: true.
     * Postcondición: true si [g] es bipartido, false de lo contrario.
     * Tiempo de la operación: O(1).
     */
    fun esBipartido(): Boolean = esBipartido

    /**
     * Retorna el kcolor del vértice [v]. 
     * Precondición: [v] pertenece al grafo.
     * Postcondición: el valor retornado es 1 o 2.
     * Tiempo de la operación: O(1).
     */
    fun obtenerKcolor(v: Int): Int {
        if (!g.estaElVerticeEnElGrafo(v)) {
            throw RuntimeException("El vértice $v no pertenece al grafo.")
        }

        return vertices[v].kcolor
    }
}
