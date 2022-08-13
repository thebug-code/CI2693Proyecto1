 El enfoque para la solución de esta actividad corresponde al algoritmo de Aspvall, Plass y Tarjan (1979) el cual en tiempo lineal resuelve instancias de 2SAT satisfacibles. El algoritmo esta basado en componentes fuertemente conectados. Por lo que, los pasos que se realizan en la solución de las instancias 2SAT son los siguientes:

1. Se contruye un grafo dirigido denominado grafo de implicación en donde hay un vértice por cada literal o literal negado de la expresión booleana y un arco que conecta un vértice con otro, siempre y cuando los literales esten relacionados por una implicación en la forma normal implicativa.
 
En detalle, para la construcción del grafo se tiene que:

- Se procesa el archivo de entrada, de modo que por cada clásula de la expresión se crea un estructura `Pair` de enteros que posteriomente se agrega a una `LinkedList`. Para el mapeo del literal xi de la enésima cláusula contenida en el archivo a un entero, se implemento una función que dado el literal suma o resta uno a `xi.toInt()`. Es decir, si `xi.toInt()` es mayor o igual que cero se suma 1. Si no se resta 1. Y si `x == "-0"` se retorna -1.

 Con lo anterior se tiene que, el intervalo $[1, nLit]$ corresponde a variables no complentadas y el intervalo $[-nLit, -1]$ corresponde a variables complementadas, $nLit$ representa el número de literales de la expresión, la cuál se determina en este mismo proceso.
 
Posteriormente se procede a agregar los arcos al grafo. Dado lo recién descrito se tiene que la variable x es mapeada a x - 1 y la variable -x es mapeada a $nLit + x$ (si es positivo) o $nLit -(-x)$ (si es negativo). Veamos un ejemplo:

Instancia contenida en el arhivo de entrada:
 0 -1 <br>
 -0 1 <br>
 -0 -1 <br>
 0 -2 <br>
 
Se procesa el archivo y se obtiene la siguiente LinkedList de pares:
 (1, -2) -> (-1, 2) -> (-1, -2) -> (1, -3)
 
Dónde:

 1 (int) es el literal 0 (formato de archivo). <br>
 -2 (int) es el literal -1 (formato de archivo). <br>
 2 (int) es el literal 1 (formato de archivo). <br>
 -1 (int) es el literal -0 (formato de archivo). <br>
 -3 (int) es el literal -2 (formato de archivo). <br>

Se agregan los arcos al grafo obteniendo 
 |0| -> [1] -> [4]  <br>
 |1| -> [0] -> [3]  <br>
 |2| -> [0] <br>
 |3| -> [4] -> [5] <br>
 |4| -> [3] <br>
 |5|
 
Dónde:

 3 es el complemento del 0. <br>
 4 es el complemento del 1. <br>
 5 es el complemento del 2. <br>
 
2. Se encuentran las componentes fuertementes conexas del grafo, usando objeto CFC implementado con anterioridad.
3. Se comprueba si alguna componente fuertemente conexa contiene tanto una variable como su negación. En caso afirmativo se informa que la instancia no es satisfactoria, en caso contrario se realiza el siguiente paso.
4. Se construye el grafo componente asociado a las componentes fuertementes conexas calculadas en el paso 2.
5. Se aplica un ordenamiento topologico al grafo componente, bajo la relación de orden parcial $<$.
6. Se establecen como falso todas las asignaciones de los literales del grafo y se recorre el orden topologico hasta encontrar el literal $x_i$ o $\neg x_i$, las asignaciones se hacen de la siguiente manera:
 - Si se encuentra primero $ \neg x_i$, se tiene que $C(\neg x_i) < C(x_i) \implies x_i = true$
 - Si se encuentra primero xi, se tiene que $C(x_i) < C(\neg x_i) \implies x_i = false$
  
 Dónde:
 
 $C$ es una función que asigna a un literal de la fórmula booleana su CFC.
 
7. Se muestra en pantalla la asignación que hace verdadera la fórmula.

Por último, las estructuras de apoyo usadas para la solución de la actividad son:

 - Clase `GrafoDeImplicacion`, que recibe como parámetro de entrada un archivo con la fórmula booleana. En la misma están los procedimientos que llevan a cabo el paso 1.
