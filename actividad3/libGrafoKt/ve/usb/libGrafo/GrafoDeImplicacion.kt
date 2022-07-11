package ve.usb.libGrafo

import java.io.File
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max

/**
 * Esta clase recibe como entrada un archivo que contiene una fórmula boolenea que 
 * esta en 2-CNF. Es decir, esta en forma normal conjuntiva y cada una de las
 * cláusulas que la componen tiene exactamente dos literales diferentes. Al momen
 * de instanciar un objeto de este tipo se crea un grafo de implicación, el cual
 * es un grafo dirigido en donde hay un vértice por cada literal o literal negado
 * de la expresión booleana y un arco que conecta un vértice con otro, siempre
 * y cuando los literales estén relacinados por una implicación en la forma im-
 * implicativa normal de la instancia contenida en el archivo.
 */
public class GrafoDeImplicacion(val nombreArchivo: String) {
    private val g : GrafoDirigido 
    private val literales: LinkedList<Pair<Int, Int>>
    private var nLit : Int = Int.MIN_VALUE
    
    init {
        val file = File(nombreArchivo)
        if (!file.exists()) {
            throw RuntimeException("El archivo $nombreArchivo no existe o no se puede leer.")
        }
        
        val lines : List<String> = file.readLines()

        // Procesar archivo de entrada
        literales = LinkedList()
        lines.forEach {
            val p = it.split(" ")

            // Par con la cláusula t_i
            val pair = Pair(formatear(p[0]), formatear(p[1]))

            // Almacenar las cláusulas en una lista
            literales.add(pair)
            
            // Obtener el número de literales
            val m = max(abs(pair.first), abs(pair.second))
            if (m > nLit) nLit = m
        }
        
        // Agregar cláusulas al digrafo
        g = GrafoDirigido(2 * nLit)
        literales.forEach {
            g.agregarArco(Arco(this.idNegado(it.first), this.id(it.second)))
            g.agregarArco(Arco(this.idNegado(it.second), this.id(it.first)))
        }
    }
    
    /** 
     * Retorna el grafo de implicación asociado a la instancia de la 
     * expresión boolena en 2-CNF contenida en el archivo de entrada.
     * Precondición: true.
     * Postcondición: true.
     * Tiempo de la operación: O(1).
     */
    fun obtenerGrafoDeImplicacion(): GrafoDirigido = this.g
    
    /**
     * Retorna un entero que representa el mapeo del literal [xi]
     * (formateado a int) a un vértice válido del grafo de implicación.
     * Precondición: Para variables no complementadas se tiene que, 1 <= [xi] <= nLit, o
     *               para variables complementadas se tiene que, -nLit <= [i] <= -1 
     * Postcondición: [id] corresponde un vértice válido del grafo y 0 <= [id] < 2 * nLit
     */
    fun id(xi: Int): Int {
        if (xi > 0) return xi - 1
        return this.nLit - xi - 1
    }

    /**
    * Retorna un entero que representa al mapeo del literal [xi] (formateado a int) a un vértice
    * complementado válido del grafo de implicación. 
    * Precondición: Para variables no complementadas se tiene que, 1 <= [xi] <= nLit, o
    *               para variables complementadas se tiene que, -nLit <= [xi] <= -1 
    * Postcondición: Si 1 <= [xi] <= nLit => [idNegado] = nLit + [xi] - 1 o 
    *                si -nLit <= [xi] <= 1 => [idNegado] = -[xi] - 1.
    */
    fun idNegado(xi: Int): Int{
        if (xi > 0) return this.nLit + xi - 1
        return -xi - 1
    }

    /**
     * Retorna un entero que corresponde a la suma o resta del valor 1 del
     * entero asociado al literal [x] de la expresión booleana.
     * Precondición: x es un string asociado a un literal de la exp booleana.
     * Postcondición: El valor r retornado es tal que:
                     1 <= r <= nLit para variables no complementadas.
                     -nLit <= r <= -1 para variables complementadas.
     *               Dónde nLit es el número de literales.
     */
    private fun formatear(x: String): Int {
        if (x == "-0") return -1
        else if (x.toInt() >= 0) return x.toInt() + 1
        return x.toInt() - 1
    }
}
