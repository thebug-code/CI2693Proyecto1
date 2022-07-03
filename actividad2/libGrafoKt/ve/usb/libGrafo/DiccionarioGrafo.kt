package ve.usb.libGrafo

import java.io.File
import kotlin.math.min

public class DiccionarioGrafo(val filePath: String) {

    private var verticeToString: HashMap<Int, String>
    private var stringToVertice: HashMap<String, Int>
    private var g: GrafoDirigido

    init {
        val file = File(filePath)
        if (!file.exists()) {
            throw RuntimeException("El archivo dado no existe.")
        }

        val lines: List<String> = file.readLines()
        var n = lines.size
        verticeToString = HashMap(n)
        stringToVertice = HashMap(n)
        g = GrafoDirigido(n)
        var contador = 0

        for (palabra in lines) {
            if (stringToVertice[palabra] == null) {
                stringToVertice[palabra] = contador
                verticeToString[contador++] = palabra
            }
        }

        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                if (distanciaDeEdicion(lines[i], lines[j]) == 1) {
                    g.agregarArco(Arco(stringToVertice[lines[i]]!!, stringToVertice[lines[j]]!!))
                }
            }
        }

        println(verticeToString)
    }

    /**
     * Indica si el índice si el indice [v] de un vértice pertenece al digrafo. En caso afirmativo
     * retorna true, de lo contrario false. Tiempo de la operación: O(1).
     */
    fun contieneVertice(v: Int): Boolean = v >= 0 && v < g.obtenerNumeroDeVertices()

    /**
     * Retorna el índice asociado al vertice de nombre [nombre]. Si no existe ningun vértice
     * asociado con ese nombre se lanza un RuntimeException. Precondición: nombre tiene un vértice
     * asociado. Tiempo de la operación: O(1).
     */
    fun indiceVertice(nombre: String): Int {
        // Verificar si el nombre tiene un vertice asociado
        if (stringToVertice[nombre] == null) {
            throw RuntimeException("No existe un vertice asociado con el nombre dado.")
        }

        return this.stringToVertice[nombre]!!
    }

    /**
     * Retorna el nombre asociado del vertice con índice [v]. Si [v] no corresponde a un índice
     * asociado con un vertice del digrafo entonces se lanza un RuntimeException. Precondición: [v]
     * corresponde a un indice con un vértice del grafo asociado. Tiempo de la operación: O(1).
     */
    fun nombreVertice(v: Int): String {
        // Verificar si el indice dado tiene un nombre asociado
        if (v < 0 || v >= g.obtenerNumeroDeVertices()) {
            throw RuntimeException("No existe un nombre asociado con el indice dado.")
        }

        return this.verticeToString[v]!!
    }

    /**
     * Retorna el digrafo asociado al grafo con vértices con nombre, indicado en el archivo de
     * entrada. Tiempo de la operación: O(1).
     */
    fun obtenerGrafoDirigido(): GrafoDirigido = this.g

    /* Distancia de edicion (Levenshtein Distance) */
    fun distanciaDeEdicion(X: String, Y: String): Int {
        val m = X.length
        val n = Y.length
        val T = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            T[i][0] = i
        }
        for (j in 1..n) {
            T[0][j] = j
        }
        var cost: Int
        for (i in 1..m) {
            for (j in 1..n) {
                cost = if (X[i - 1] == Y[j - 1]) 0 else 1
                T[i][j] = min(min(T[i - 1][j] + 1, T[i][j - 1] + 1), T[i - 1][j - 1] + cost)
            }
        }
        return T[m][n]
    }
}
