import ve.usb.libGrafo.*
import kotlin.system.exitProcess

/**
 * El enfoque para la solución de ésta actividad se basa en las siguientes ideas:
 * Crear un grafo dirigido asociado al diccionario, donde cada lado (a,b) 
 * representa que entre la palabra asociada al vertice [a] y la palabra asociada al
 * vertice [b] solo hay un paso de edicion, respetando el orden lexicografico.
 * Al tener este grafo creado, el problema se reduce a encontrar el camino simple mas largo
 * del grafo
 * 
 * Para obtener el camino mas largo, se ejecuta una version modificada de DFS. A medida que se
 * ejecuta, se va almacenando el camino recorrido.
 * 
 * Un detalle que permite optimizar la ejecucion, es el hecho de que los vertices mantienenen
 * un orden topologico, solo se entra con dfsVisit a los vertices que son capaces de formar un 
 * camino mas largo al que esta almacenado actualmente.
 * 
 * Se implemento la siguiente estructura:
 * 
 * DiccionarioGrafo:
 *      Parametro de la clase: filePath. Es el nombre del archivo donde esta almacenado el diccionario.
 *      Esta estructura crea un grafo dirigido, asociado al diccionario. Permite un mapeo de vertices a String
 *      y de String a vertices.
 * 
 */
fun main(args: Array<String>) {


    if (args.size != 1) {
        println("Error en la cantidad de argumentos")
        println("Usage: ./runEscaleraEdicion.sh <archivoEntrada>")
        exitProcess(1)
    }

    // Cargar diccionario
    var diccToGrafo = DiccionarioGrafo(args[0])

    // Obtener el grafo dirigido asociado
    var g = diccToGrafo.obtenerGrafoDirigido()
    
    // Ejecutar DFS modificado
    var dfs = DFS(g)

    // Obtener un arreglo de vertices que representan el camino mas largo
    var cadena = dfs.obtenerCaminoMasLargo()

    // Imprimir el string asociado a cada vertice
    for ( i in cadena) {
        print(diccToGrafo.nombreVertice(i) + " ")
    }
    println("\n" + cadena.size)

}


fun preocondicionArg(argsSize: Int) {
    if (argsSize != 1) {
        println("Error. ")
    }
}