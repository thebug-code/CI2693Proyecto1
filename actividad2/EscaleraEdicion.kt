import kotlin.system.exitProcess
import ve.usb.libGrafo.*

/**
 * El enfoque para la solución de ésta actividad se basa en las siguientes ideas: Crear un grafo
 * dirigido asociado al diccionario, donde cada lado (a,b) representa que entre la palabra asociada
 * al vertice [a] y la palabra asociada al vertice [b] solo hay un paso de edicion, respetando el
 * orden lexicografico. Al tener este grafo creado, el problema se reduce a encontrar el camino
 * simple mas largo del grafo
 *
 * Para obtener el camino mas largo, se ejecuta una version modificada de DFS. A medida que se
 * ejecuta, se va almacenando el camino recorrido.
 *
 * Un detalle que permite optimizar la ejecucion, es el hecho de que los vertices mantienenen un
 * orden topologico, solo se entra con dfsVisit a los vertices que son capaces de formar un camino
 * mas largo al que esta almacenado actualmente.
 *
 * Se implemento la siguiente estructura:
 *
 * DiccionarioGrafo:
 *      Esta estructura crea un grafo dirigido, asociado al diccionario. Permite un mapeo de vertices a String
 *      y de String a vertices
 *      Parametro de la clase: [filePath]. Es el nombre del archivo donde esta almacenado el diccionario.
 *
 * 
 */

    /**
 * Muestra en pantalla el mensaje [mensaje] que indica que a ocurrido un error en la ejecución del
 * programa y terminar la ejecución del mismo.
 */
fun error(mensaje: String) {
    println(mensaje)
    println("Usage: ./runEscaleraEdicion.sh <archivoEntrada>")
}

/**
 * Verifica que la entrada con los datos sea válido, en caso de que no se muestra en pantalla el
 * posible error y se finaliza la ejecución del programa.
 */
fun verificarEntrada(args: Array<String>) {
    if (args.size != 1) {
        error("Cantidad de argumentos invalida")
        exitProcess(1)
    }
}

// Main
fun main(args: Array<String>) {

    verificarEntrada(args)

    // Grafo asociado al diccionario
    var diccToGrafo: DiccionarioGrafo?

    try {
        diccToGrafo = DiccionarioGrafo(args[0])
    } catch (e: RuntimeException) {
        error(e.toString())
        exitProcess(1)
    }

    // Obtener el grafo dirigido
    var g: GrafoDirigido = diccToGrafo.obtenerGrafoDirigido()

    // Ejecutar DFS modificado
    var dfs = DFS(g)

    // Obtener un arreglo de vertices que representan el camino mas largo
    var cadena = dfs.obtenerCaminoMasLargo()

    // Imprimir el string asociado a cada vertice
    for (i in cadena) {
        print(diccToGrafo.nombreVertice(i) + " ")
    }
    println("\n" + cadena.size)
}
