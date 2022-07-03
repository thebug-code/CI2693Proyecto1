KC=	kotlinc
KFLAG=	-cp
GLIB=	ve/usb/libGrafo

all:	libGrafoKt.jar

libGrafoKt.jar:	$(GLIB)/Lado.class\
		$(GLIB)/Arco.class\
		$(GLIB)/Grafo.class\
		$(GLIB)/GrafoDirigido.class\
		$(GLIB)/DFS.class\
		$(GLIB)/DiccionarioGrafo.class

	jar -cvf $@ $(GLIB)/*.class 

$(GLIB)/Lado.class: $(GLIB)/Lado.kt
	$(KC) $(GLIB)/Lado.kt

$(GLIB)/Arco.class: $(GLIB)/Arco.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Arco.kt

$(GLIB)/Grafo.class: $(GLIB)/Grafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Grafo.kt

$(GLIB)/GrafoDirigido.class: $(GLIB)/GrafoDirigido.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoDirigido.kt

$(GLIB)/DFS.class: $(GLIB)/DFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/DFS.kt

$(GLIB)/DiccionarioGrafo.class: $(GLIB)/DiccionarioGrafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/DiccionarioGrafo.kt

clean:
	rm -rf libGrafoKt.jar META-INF $(GLIB)/*.class
