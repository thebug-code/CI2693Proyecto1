package ve.usb.libGrafo

public class DosColoreable(val g: GrafoNoDirigido) {
    private var vertices: Array<VerticeDosColoreable> 
    private var esBipartido: Boolean 

    init {
        vertices = Array(g.obtenerNumeroDeVertices()) { VerticeDosColoreable(it) }
        esBipartido = true

        for (v in 0 until g.obtenerNumeroDeVertices()) {
            if (vertices[v].color == Color.BLANCO) dfsVisitColor(v)
        }
    }

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

    fun esBipartido(): Boolean = esBipartido

    fun obtenerKcolor(v: Int): Int {
        if (!g.estaElVerticeEnElGrafo(v)) {
            throw RuntimeException("El vértice $v no pertenece al grafo.")
        }

        return vertices[v].kcolor
    }
}
