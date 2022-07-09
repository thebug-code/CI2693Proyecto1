import ve.usb.libGrafo.*
import kotlin.system.exitProcess

/**
 * El enfoque para la solución de esta actividad corresponde al algoritmo de Aspvall, 
 * Plass y Tarjan (1979) el cual en tiempo lineal resuelve instancias de 2SAT satisfacibles. 
 * El algoritmo esta basado en componentes fuertemente conectados. Por lo que los pasos que
 * se realizan en la solución de las instancias 2SAT son los siguientes:

 * (a) Se contruye un grafo dirigido denominado grafo de implicación en donde hay un vértice
 * por cada literal o literal negado de la expresión booleana y un arco que conecta un vértice 
 * con otro, siempre y cuando los literales esten relacionados por una implicación en la forma
 * normal implicativa.
 * En detalle, para la construcción del grafo se tiene que:
 *      - Se procesa el archivo de entrada, de modo que por cada clásula de la expresión 
 *      se crea una objeto Pair de enteros que posteriomente se agrega a una LinkedList.
 *      Para el mapeo del literal xi de la enesima cláusula contenida en el archivo a un 
 *      entero, se implemento una funcion que dado el literal suma o resta uno a xi.toInt().
 *      Es decir, si xi.toInt() es mayor o igual que cero se suma 1. Si no se resta 1. 
 *      Y si x == "-0" se retorna -1.
 *
 *      Con lo anterior se tiene que, el intervalo [1, nLit] corresponde a variables no com-
 *      plentadas y el intervalo [-nLit, -1] corresponde a variables complementadas, nLit re-
 *      presenta el número de literales de la exp, la cuál se determina en este mismo proceso.
 *    
 *      Posteriormente se procede a agregar los arcos al grafo. Dado lo recién descrito se tiene
 *      que la variable x es mapeada a x - 1 y la variable -x es mapeada a nLit + x (si es positivo)
 *      o nLit -(-x) (si es negativo). Veamos un ejemplo:
 *     
 *      Instancia contenida en el arhivo de entrada:
 *       0 -1
 *       -0 1
 *       -0 -1
 *       0 -2
 *
 *      Se procesa el archivo y se obtiene la siguiente LinkedList de pares:
 *      (1, -2) -> (-1, 2) -> (-1, -2) -> (1, -3)
 *      
 *      Dónde:
 *
 *      1 (int) es el literal 0 (formato de archivo).
 *      -2 (int) es el literal -1 (formato de archivo).
 *      2 (int) es el literal 1 (formato de archivo).
 *      -1 (int) es el literal -0 (formato de archivo).
 *      -3 (int) es el literal -2 (formato de archivo).
 *
 *      Se agregan los arcos al grafo obteniendo 
 *          |0| -> [1] -> [4] 
 *          |1| -> [0] -> [3] 
 *          |2| -> [0] 
 *          |3| -> [4] -> [5] 
 *          |4| -> [3] 
 *          |5|
 *     
 *      Dónde:
 *
 *      3 es el complemento del 0. 
 *      4 es el complemento del 1. 
 *      5 es el complemento del 2. 
 *
 * (b) Se encuentran las componentes fuertementes conexas del grafo, usando objeto CFC implementado
 *  con anterioridad.
 *
 * (c) Se comprueba si alguna componente fuertemente conexa contiene tanto una variable como su negación. 
 * en caso afirmativo se informa que la instancia no es satisfactoria, en caso contrario se realiza el 
 * siguiente paso.
 *
 * (d) Se construye el grafo componente asociado a las componentes fuertementes conexas calculadas en el paso (b).
 *
 * (e) Se aplica un ordenamiento topologico al grafo componente.
 *
 * (d) Se establecen como falso todas las asinaciones de los literales del grafo y se recorre el orden topologico
 * hasta encontrar el literal xi o ¬xi, las asinaciones se hacen de la siguiente manera:
 * 
 *      Si se encuentra primero ¬xi, se tiene que C(¬xi) < C(xi) => xi = true
 *      Si se encuentra primero xi, se tiene que C(xi) < C(¬xi) => xi = false
 *
 *      Dónde:
 *     
 *      C es una función que asigna a un literal de la fórmula booleana su CFC.
 *
 * (e) Se muestra en pantalla la asignación que hace verdadera la fórmula.

 * Por último, las estructuras de apoyo usadas para la solución de la actividad son:
 *
 * - Clase GrafoDeImplicación, que recibe como parámetro de entrada un archivo con la
 * fórmula booleana. En la misma están los procedimientos que llevan a cabo el paso (a).
 */

/**
 * Muestra en pantalla el mensaje [mensaje] que indica que a ocurrido un
 * error en la ejecución del programa y terminar la ejecución del mismo.
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
