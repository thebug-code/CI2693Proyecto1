import kotlin.system.exitProcess
import ve.usb.libGrafo.*

/**
 * Muestra en pantalla el mensaje [mensaje] que indica que a ocurrido un error en la ejecución del
 * programa y termina la ejecución del mismo.
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
