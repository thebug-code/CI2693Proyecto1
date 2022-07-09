KC=		kotlinc
KFLAG=		-cp
LIBGRAPH=	libGrafoKt/
LIBJAR=		libGrafoKt/libGrafoKt.jar

all:	jarlib TwoSATSolverKt.class

jarlib:
	(cd $(LIBGRAPH); make)  

TwoSATSolverKt.class: TwoSATSolver.kt
	$(KC) $(KFLAG) $(LIBJAR) TwoSATSolver.kt

clean:
	(cd $(LIBGRAPH); make clean)
	rm -rf *.class META-INF
