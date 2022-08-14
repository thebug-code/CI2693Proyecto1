import ve.usb.libGrafo.*
import kotlin.system.exitProcess

/**
 * Muestra en pantalla el mensaje [mensaje] que indica que a ocurrido un
 * error en la ejecución del programa y termina la ejecución del mismo.
*/
fun error(mensaje: String) {
    println(mensaje)
    println("Usage: ./runTwoSATSolver.sh <archivoFormula>")
    exitProcess(1)
}

/**
 * Verifica que la entrada con el archivo sea válido, en caso de que no
 * se muestra en pantalla un error y se finaliza la ejecución del programa.
 */
fun verificarEntrada(args: Array<String>) {
    if (args.size != 1) {
        error("Entrada Inválida.")
    } else if (!args[0].endsWith(".txt")) {
        error("El archivo ${args[0]} debe finalizar con .txt")
    }
    return
}

// Main
fun main(args: Array<String>) {
    verificarEntrada(args)

    // Construir grafo de implicación
    val t = GrafoDeImplicacion(args[0])

    // Obtener grafo de implicación
    val grafo = t.obtenerGrafoDeImplicacion()

    // Obtener las CFC del grafo
    val cc = CFC(grafo)

    /** 
     * Verificar si la expresión boolenada es satisfacible
     * (si no existe algún literal xi que está en la misma
     * componente conexa que su complemento)
    */
    val n: Int = grafo.obtenerNumeroDeVertices()
    var esSatisfacible : Boolean = true
    for(i in n - 1 downTo n / 2) {
        esSatisfacible = !cc.estanEnLaMismaCFC(i, i - (n / 2))
    }
    
    /**
     * Si la exp es satisfacible se procede a hallar una asignación
     * que la haga verdadera.
     */
    if (esSatisfacible) {
        // Obtener grafo componente
        val grafoComponente = cc.obtenerGrafoComponente()

        // Obtener el ordenamiento topologico del grafo componente
        val ordenTopologico = OrdenamientoTopologico(grafoComponente).obtenerOrdenTopologico()
        
        // Se recorre el orden topologico hasta encontrar el literal xi o ¬xi 
        val asignaciones = BooleanArray(n / 2)
        for(i in n - 1 downTo n / 2) {
            for (x in ordenTopologico) {
                if (cc.obtenerIdentificadorCFC(i - (n / 2)) == x) {
                    // Se encontro primero xi, C(xi) < C(¬xi) => xi = false
                    break
                } else if (cc.obtenerIdentificadorCFC(i) == x) {
                    // Se encontro primero ¬xi, C(¬xi) < C(xi) => xi = true
                    asignaciones[i - (n / 2)] = true
                    break
                }
            }
        }
        println(asignaciones.joinToString(" ").uppercase())
    } else {
        println("No hay asignación válida.")
    }
}
