KC=	kotlinc
KFLAG=	-cp
GLIB=	ve/usb/libGrafo

all:	libGrafoKt.jar

libGrafoKt.jar: $(GLIB)/Color.class\
		$(GLIB)/Lado.class\
		$(GLIB)/Arco.class\
		$(GLIB)/Grafo.class\
		$(GLIB)/GrafoDirigido.class\
        $(GLIB)/VerticeTp.class\
		$(GLIB)/VerticeDFS.class\
		$(GLIB)/VerticeBFS.class\
		$(GLIB)/BFS.class\
		$(GLIB)/DFS.class\
        $(GLIB)/OrdenamientoTopologico.class\
        $(GLIB)/CicloDigrafo.class\
        $(GLIB)/Utilidades.class\
        $(GLIB)/CFC.class\
        $(GLIB)/VerticeDFSCC.class\
        $(GLIB)/ComponentesConexasDFS.class\
        $(GLIB)/GrafoDeImplicacion.class

	jar -cvf $@ $(GLIB)/*.class 

$(GLIB)/Color.class: $(GLIB)/Color.kt
	$(KC) $(GLIB)/Color.kt

$(GLIB)/Lado.class: $(GLIB)/Lado.kt
	$(KC) $(GLIB)/Lado.kt

$(GLIB)/Arco.class: $(GLIB)/Arco.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Arco.kt

$(GLIB)/Grafo.class: $(GLIB)/Grafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Grafo.kt

$(GLIB)/GrafoDirigido.class: $(GLIB)/GrafoDirigido.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoDirigido.kt

$(GLIB)/BFS.class: $(GLIB)/BFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/BFS.kt

$(GLIB)/DFS.class: $(GLIB)/DFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/DFS.kt

$(GLIB)/VerticeTp.class: $(GLIB)/VerticeTp.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/VerticeTp.kt

$(GLIB)/OrdenamientoTopologico.class: $(GLIB)/OrdenamientoTopologico.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/OrdenamientoTopologico.kt

$(GLIB)/VerticeDFS.class: $(GLIB)/VerticeDFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/VerticeDFS.kt

$(GLIB)/VerticeBFS.class: $(GLIB)/VerticeBFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/VerticeBFS.kt

$(GLIB)/CicloDigrafo.class: $(GLIB)/CicloDigrafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CicloDigrafo.kt

$(GLIB)/Utilidades.class: $(GLIB)/Utilidades.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Utilidades.kt

$(GLIB)/CFC.class: $(GLIB)/CFC.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CFC.kt

$(GLIB)/ComponentesConexasDFS.class: $(GLIB)/ComponentesConexasDFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/ComponentesConexasDFS.kt

$(GLIB)/VerticeDFSCC.class: $(GLIB)/VerticeDFSCC.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/VerticeDFSCC.kt

$(GLIB)/GrafoDeImplicacion.class: $(GLIB)/GrafoDeImplicacion.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoDeImplicacion.kt

clean:
	rm -rf libGrafoKt.jar META-INF $(GLIB)/*.class
