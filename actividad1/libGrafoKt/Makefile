KC=	kotlinc
KFLAG=	-cp
GLIB=	ve/usb/libGrafo

all:	libGrafoKt.jar

libGrafoKt.jar: $(GLIB)/Color.class\
		$(GLIB)/Lado.class\
		$(GLIB)/Arista.class\
		$(GLIB)/Grafo.class\
		$(GLIB)/GrafoNoDirigido.class\
		$(GLIB)/VerticeBFS.class\
		$(GLIB)/BFS.class\
		$(GLIB)/TablaGrafoNoDirigido.class\
		$(GLIB)/VerticeDosColoreable.class\
		$(GLIB)/DosColoreable.class

	jar -cvf $@ $(GLIB)/*.class 

$(GLIB)/Color.class: $(GLIB)/Color.kt
	$(KC) $(GLIB)/Color.kt

$(GLIB)/Lado.class: $(GLIB)/Lado.kt
	$(KC) $(GLIB)/Lado.kt

$(GLIB)/Arista.class: $(GLIB)/Arista.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Arista.kt

$(GLIB)/Grafo.class: $(GLIB)/Grafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Grafo.kt

$(GLIB)/GrafoNoDirigido.class: $(GLIB)/GrafoNoDirigido.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoNoDirigido.kt

$(GLIB)/BFS.class: $(GLIB)/BFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/BFS.kt

$(GLIB)/VerticeBFS.class: $(GLIB)/VerticeBFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/VerticeBFS.kt

$(GLIB)/TablaGrafoNoDirigido.class: $(GLIB)/TablaGrafoNoDirigido.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/TablaGrafoNoDirigido.kt

$(GLIB)/VerticeDosColoreable.class: $(GLIB)/VerticeDosColoreable.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/VerticeDosColoreable.kt

$(GLIB)/DosColoreable.class: $(GLIB)/DosColoreable.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/DosColoreable.kt

clean:
	rm -rf libGrafoKt.jar META-INF $(GLIB)/*.class
