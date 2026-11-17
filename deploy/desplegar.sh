#!/usr/bin/env bash
# Despliega una version liberada del Sistema de Inventario en el servidor.
# Uso: bash desplegar.sh v1.0.0
# Requiere que sistema-inventario-<version>.zip este en ~/sistema-inventario/paquetes/
set -euo pipefail

VERSION="${1:?Indica la version a desplegar, por ejemplo v1.0.0}"
BASE="$HOME/sistema-inventario"
ZIP="$BASE/paquetes/sistema-inventario-$VERSION.zip"
DESTINO="$BASE/versiones/$VERSION"
APP="$DESTINO/sistema-inventario-$VERSION"
LOG="$BASE/historial-despliegues.log"

registrar() { echo "$(date '+%Y-%m-%d %H:%M:%S') $*" | tee -a "$LOG"; }

echo "== 1. Verificar requisitos del entorno =="
command -v java  >/dev/null || { echo "ERROR: Java no esta instalado"; exit 1; }
command -v unzip >/dev/null || { echo "ERROR: unzip no esta instalado"; exit 1; }
JAVA_MAYOR=$(java -version 2>&1 | grep -oE 'version "[0-9]+' | grep -oE '[0-9]+$')
java -version 2>&1 | grep -v JAVA_TOOL | head -1
[ "$JAVA_MAYOR" -ge 21 ] || { echo "ERROR: se requiere Java 21 o superior (encontrado: $JAVA_MAYOR)"; exit 1; }
[ -f "$ZIP" ] || { echo "ERROR: no existe el paquete $ZIP"; exit 1; }

echo "== 2. Extraer el paquete $VERSION =="
mkdir -p "$DESTINO"
unzip -o -q "$ZIP" -d "$DESTINO"
ls -R "$DESTINO"

echo "== 3. Activar la version $VERSION =="
cd "$BASE"
if [ -L actual ]; then
  ANTERIOR=$(readlink actual)
  ln -sfn "$ANTERIOR" anterior
  echo "Version anterior conservada: $ANTERIOR"
fi
ln -sfn "versiones/$VERSION/sistema-inventario-$VERSION" actual
echo "Version activa: $(readlink actual)"

echo "== 4. Verificar el funcionamiento =="
if java -jar actual/sistema-inventario.jar \
   && java -cp actual/sistema-inventario.jar BusquedaProductos; then
  registrar "DESPLIEGUE CORRECTO $VERSION"
else
  registrar "FALLO LA VERIFICACION DE $VERSION"
  if [ -L anterior ]; then
    echo "== Recuperando la version anterior =="
    bash "$BASE/deploy/rollback.sh"
  fi
  exit 1
fi
