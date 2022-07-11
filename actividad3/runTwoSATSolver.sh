#!/bin/bash

if [ $# -eq 1 ]
then
    kotlin -cp libGrafoKt/libGrafoKt.jar:. TwoSATSolverKt $1
else
    kotlin -cp libGrafoKt/libGrafoKt.jar:. TwoSATSolverKt $*
fi
