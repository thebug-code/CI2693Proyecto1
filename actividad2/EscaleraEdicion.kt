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
 * Para obtener el camino mas largo, se ejecuta un
 * El primer detalle del q, es que por mantener el orden lexicografico, el grafo
 * es un DAG, y por como se mapean los String a los verticesuyos vertices mantienen un orden topologico
 * 
 */
fun main(args: Array<String>) {


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