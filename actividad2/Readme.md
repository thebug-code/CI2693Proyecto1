El enfoque para la solución de ésta actividad se basa en las siguientes ideas: Crear un grafo dirigido asociado al diccionario, donde cada lado $(a,b)$ representa que entre la palabra asociada al vertice $a$ y la palabra asociada al vertice $b$ solo hay un paso de edicion, respetando el orden lexicografico. Al tener este grafo creado, el problema se reduce a encontrar el camino simple mas largo del grafo

Para obtener el camino mas largo, se ejecuta una version modificada de DFS. A medida que se ejecuta, se va almacenando el camino recorrido.

Un detalle que permite optimizar la ejecución, es el hecho de que los vertices mantienenen un orden topológico, solo se entra con `dfsVisit` a los vertices que son capaces de formar un camino más largo al que esta almacenado actualmente.

Se implemento la siguiente estructura:

- DiccionarioGrafo:

Esta estructura crea un grafo dirigido, asociado al diccionario. Permite un mapeo de vertices a String y de String a vertices

Parámetro de la clase: $[filePath]$. Es el nombre del archivo donde esta almacenado el diccionario.
