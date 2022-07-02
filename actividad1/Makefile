KC=		kotlinc
KFLAG=		-cp
LIBGRAPH=	libGrafoKt/
LIBJAR=		libGrafoKt/libGrafoKt.jar

all:	jarlib GradosDeSeparacionKt.class

jarlib:
	(cd $(LIBGRAPH); make)  

GradosDeSeparacionKt.class: GradosDeSeparacion.kt
	$(KC) $(KFLAG) $(LIBJAR) GradosDeSeparacion.kt

clean:
	(cd $(LIBGRAPH); make clean)
	rm -rf *.class META-INF
