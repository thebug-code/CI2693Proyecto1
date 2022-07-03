import ve.usb.libGrafo.*

import kotlin.system.exitProcess

fun main(args: Array<String>) {



    var t = DiccionarioGrafo(args[0])
    var g = t.obtenerGrafoDirigido()
    
    println(g)

    var dfs = DFS(g)
    var cadena = dfs.caminoMasLargo()
    for ( i in cadena) {
        print(t.nombreVertice(i) + " ")
    }
    println()

}
