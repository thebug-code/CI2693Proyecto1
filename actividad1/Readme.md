El enfoque para la solución de esta actividad es bastante sencillo. En primer lugar se modifico el algoritmo BFS para encontrar el camino más corto desde ente1 hasta ente2, esto dado que sabemos que el algoritmo visita primero todos los vértices a distancia $k$ al vértice de inicio antes de visitar a los que están a distancia $k+1$, por lo que en efecto el camino obtenido desde ente1 a ente2 es el más corto y la distancia la menor. Para verificar si el grafo es bipartido se implemento un  algoritmo basado en DFS que "colorea" cada vértice perteneciente al mismo grupo de partición de un mismo color, por lo que si, cuando se colorea un vértice, existe un borde que lo conecta con un vértice previamente coloreado con el mismo color, entonces se sabe que el grafo no es dos coloreable y por ente no es bipartido.

 Si el grafo tiene cualquier topología, se tiene que para obtener el camino  desde ente1 a ente2, se verifica primero si ente2 es alcanzable desde ente2, esto es obteniendo la distancia desde ente1 a ente2, si es -1 entonces ente2 no es alcanzable desde ente1, en caso contrario se llama a la función `caminoHasta` con parámetro de entrada ente2 para hallar el camino deseado. Para éste caso, el grado de sepación simplementente viene dado por la distancia ya descrita. Por otro lado, para el otro caso (se indica la opción -b), se tiene que el camino desde ente1 hasta ente2 viene dado por lo ya comentado en el caso anterior. Sin embargo, el grado de separación entre los vértices ente1 y ente2 viene dado por la distancia divida entre dos, esto porque ambos vértices pertenecen al mismo grupo de partición (son del mismo tipo). Además, por propiedades de grafos bipartidos se sabe que el grado de separación calculado siempre es un número entero, porque es imposible que en el camino más corto haya un número impar de lados que conecten a los vértices del mismo tipo.
 
Por último, las estructuras de apoyo usadas para la solución de la actividad son:

- Clase `VerticeBFS` con parámetros:
  - $v$ representa el valor del vértice.
  - $d$ distancia desde el enésimo vértice al vértice de inicio.
  - $Color$ color de enésimo vértice (GRIS, BLANCO, NEGRO).
  - $pred$ vértice predecesor del enésimo vértice.
 
- Clase `VerticeDosColoreable`: 
  - $v$ representa el valor del vértice.
  - $Color$ color del enésimo vértice (GRIS, BLANCO, NEGRO).
  - $kclor$ kcolor del enésimo vértice (1 ó 2)
 
Lo enterior se decidio implementar, con el proposito de no tener un arreglo por cada propiedad de los vértices en los casos respectivos. Además, de que es más sencillo de inicializar y facilita el acceso a las propiedades.
