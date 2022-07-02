import ve.usb.libGrafo.*
import kotlin.system.exitProcess

fun error(mensaje: String) {
    println(mensaje)
    println("Usage: ./runGradosDeSeparacion.sh [-b] <archivoGrafo> <ente1> <ente2>")
	exitProcess(1)
}

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

    r.caminoHasta(ente2).forEach {
        if (it != ente2) print("${tabla.nombreVertice(it)} - ")
    }
    println(tabla.nombreVertice(ente2))
    if (!ind) {
        println("${r.obtenerDistancia(ente2)}")
    } else { 
        println("${r.obtenerDistancia(ente2) / 2}")
    }
}
