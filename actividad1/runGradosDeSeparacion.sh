#!/bin/bash

if [ $# -eq 4 ]
then
    kotlin -cp libGrafoKt/libGrafoKt.jar:. GradosDeSeparacionKt $1 "$2" "$3" "$4"
elif [ $# -eq 3 ]
then
    kotlin -cp libGrafoKt/libGrafoKt.jar:. GradosDeSeparacionKt $1 "$2" "$3"
else
    kotlin -cp libGrafoKt/libGrafoKt.jar:. GradosDeSeparacionKt $*
fi
