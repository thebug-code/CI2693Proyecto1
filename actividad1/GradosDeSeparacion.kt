import ve.usb.libGrafo.*
import kotlin.system.exitProcess

/**
 * El enfoque para la solución de ésta actividad es bastante sencillo. En
 * primer lugar se modifico el algoritmo BFS para encontrar el camino más
 * corto desde ente1 hasta ente2, esto dado que sabemos que el algoritmo 
 * visita primero todos los vértices a distancia k al vértice de inicio
 * antes de visitar a los que están a distancia k+1, por lo que en efecto
 * el camino obtenido desde ente1 a ente2 es el más corto y la distancia
 * la menor. Para verificar si el grafo es bipartido se implemento un 
 * algoritmo basado en DFS que "colorea" cada vértice perteneciente al mismo
 * grupo de partición de un mismo color, por lo que si, cuando se colorea un
 * vértice, existe un borde que lo conecta con un vértice previamente coloreado
 * con el mismo color, entonces se sabe que el grafo no es dos coloreable
 * y por ente no es bipartido.

 * Si el grafo tiene cualquier topología, se tiene que para obtener el camino 
 * desde ente1 a ente2, se verifica primero si ente2 es alcanzable desde ente2,
 * esto es obteniendo la distancia desde ente1 a ente2, si es -1 entonces ente2
 * no es alcanzable desde ente1, en caso contrario se llama a la función caminoHasta
 * con parámetro de entrada ente2 para hallar el camino deseado. Para éste caso,
 * el grado de sepación simplementente viene dado por la distancia ya descrita.
 * Por otro lado, para el otro caso (se indica la opción -b), se tiene que el
 * camino desde ente1 hasta ente2 viene dado por lo ya comentado en el caso 
 * anterior. Sin embargo, el grado de separación entre los vértices ente1 y ente2
 * viene dado por la distancia divida entre dos, esto porque ambos vértices pertenecen
 * al mismo grupo de partición (son del mismo tipo). Además, por propiedades de
 * grafos bipartidos se sabe que el grado de separación calculado siempre es un 
 * número entero, porque es imposible que en el camino más corto haya un número impar
 * de lados que conecten a los vértices del mismo tipo.

 * Por último, las estructuras de apoyo usadas para la solución de la actividad son:
 *
 * - Clase VerticeBFS con parámetros:
 *   • v representa el valor del vértice.
 *   • d distancia desde el enésimo vértice al vértice de inicio.
 *   • Color color de enésimo vértice (GRIS, BLANCO, NEGRO).
 *   • pred vértice predecesor del enésimo vértice.
 * 
 * - Clase VerticeDosColoreable: 
 *   • v representa el valor del vértice.
 *   • Color color del enésimo vértice (GRIS, BLANCO, NEGRO).
 *   • kclor kcolor del enésimo vértice (1 ó 2)
 *
 * Lo enterior se decidio implementar, con el proposito de no tener un arreglo por 
 * cada propiedad de los vértices en los casos respectivos. Además, de que es más 
 * sencillo de inicializar y facilita el acceso a las propiedades.
 *
 * - Clase DosColoreable, que recibe como parámetro de entrada un GrafoNoDirigido. 
 * En la creación de un objeto de este tipo se ejecuta un algoritmo basado en DFS, 
 * que determina si un grafo es dos coloreable. Además, se crean dos objetos adi-
 * cionales:
 *   • Procedimiento esBipartido(), que indica si el grafo de entrada es dos colo-
 *      reable.
 *   • Procedimiento obtenerKcolor(v: Int), que retorna el kcolor (1 ó 2) del vértice
 *      de entrada [v], obtenido luego de la ejecución del algorimo.
*/

/**
* Muestra en pantalla el mensaje [mensaje] que indica que a ocurrido un
* error en la ejecución del programa y terminar la ejecución del mismo.
*/
fun error(mensaje: String) {
    println(mensaje)
    println("Usage: ./runGradosDeSeparacion.sh [-b] <archivoGrafo> <ente1> <ente2>")
	exitProcess(1)
}

/**
 * Verifica que la entrada con los datos sea válido, en caso de que no
 * se muestra en pantalla el posible error y se finaliza la ejecución
 * del programa.
 */
fun verificarEntrada(args: Array<String>) {
    if (args.size == 4) {
        if (args[0] != "-b") {
            error("Flag no reconocida: ${args[0]}")
        } else if (!args[1].endsWith(".txt")) {
            error("El archivo con el grafo debe finalizar con '.txt")
        }
        return
    } else if (args.size == 3) {
        if (!args[0].endsWith(".txt")) {
            error("El archivo con el grafo debe finalizar con '.txt")
        }
        return
    }
    error("Entrada Inválida.")
}

// Main
fun main(args: Array<String>) {
    verificarEntrada(args)

    var ind = args[0] == "-b" 
    var tabla: TablaGrafoNoDirigido
    val ente1: Int 
    val ente2: Int
    val grafo: GrafoNoDirigido
    if (ind) {
        tabla = TablaGrafoNoDirigido(args[1])
        grafo = tabla.obtenerGrafoNoDirigido()
        ente1 = tabla.indiceVertice(args[2])
        ente2 = tabla.indiceVertice(args[3])

        /**
         * Verificar si el grafo es bipartido y si [ente1] y [ente2] pertenecen al
         * mismo grupo de partición.
        */
        val y = DosColoreable(grafo)
        if (!y.esBipartido()) {
            println("El grafo dado no es bipartido.")
	        exitProcess(1)
        } else if (y.obtenerKcolor(ente1) != y.obtenerKcolor(ente2)) {
            println("Los vertices ${args[2]} y ${args[3]} no pertenecen al mismo grupo de partición.")
	        exitProcess(1)
        }
    } else { 
        tabla = TablaGrafoNoDirigido(args[0]) 
        grafo = tabla.obtenerGrafoNoDirigido()
        ente1 = tabla.indiceVertice(args[1])
        ente2 = tabla.indiceVertice(args[2])
    }
    
    val r = BFS(grafo, ente1, ente2) 
    
    // Verificar si [ente2] es alcanzable desde [ente1]
    if (r.obtenerDistancia(ente2) == -1) {
        println("El grado de separación es cero.")
        return
    }
    
    // Obtener camino
    r.caminoHasta(ente2).forEach {
        if (it != ente2) print("${tabla.nombreVertice(it)} - ")
    }
    println(tabla.nombreVertice(ente2))
    
    // Mostrar distancia
    if (!ind) {
        println("${r.obtenerDistancia(ente2)}")
    } else { 
        println("${r.obtenerDistancia(ente2) / 2}")
    }
}
