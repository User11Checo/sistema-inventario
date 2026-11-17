#!/usr/bin/env bash
# Compila el codigo y ejecuta las pruebas de la funcionalidad Producto.
# Requiere lib/junit-4.13.2.jar y lib/hamcrest-core-1.3.jar
set -e

JUNIT_VERSION=4.13.2
HAMCREST_VERSION=1.3
MAVEN=https://repo1.maven.org/maven2

mkdir -p lib out
[ -f lib/junit-$JUNIT_VERSION.jar ] || curl -sSL -o lib/junit-$JUNIT_VERSION.jar \
  $MAVEN/junit/junit/$JUNIT_VERSION/junit-$JUNIT_VERSION.jar
[ -f lib/hamcrest-core-$HAMCREST_VERSION.jar ] || curl -sSL -o lib/hamcrest-core-$HAMCREST_VERSION.jar \
  $MAVEN/org/hamcrest/hamcrest-core/$HAMCREST_VERSION/hamcrest-core-$HAMCREST_VERSION.jar

CP="lib/junit-$JUNIT_VERSION.jar:lib/hamcrest-core-$HAMCREST_VERSION.jar"

javac -d out -cp "$CP" src/*.java tests/*.java
java -cp "out:$CP" EjecutarPruebas
