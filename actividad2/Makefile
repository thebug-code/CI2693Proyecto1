KC=		kotlinc
KFLAG=		-cp
LIBGRAPH=	libGrafoKt/
LIBJAR=		libGrafoKt/libGrafoKt.jar

all:	jarlib EscaleraEdicion.class

jarlib:
	(cd $(LIBGRAPH); make)  

EscaleraEdicion.class: EscaleraEdicion.kt
	$(KC) $(KFLAG) $(LIBJAR) EscaleraEdicion.kt

clean:
	(cd $(LIBGRAPH); make clean)
	rm -rf *.class META-INF
