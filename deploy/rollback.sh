#!/usr/bin/env bash
# Regresa el Sistema de Inventario a la version anterior conservada en el servidor.
# Uso: bash rollback.sh            -> vuelve a la version marcada como "anterior"
#      bash rollback.sh v1.0.0     -> vuelve a una version especifica ya desplegada
set -euo pipefail

BASE="$HOME/sistema-inventario"
LOG="$BASE/historial-despliegues.log"
cd "$BASE"

if [ -n "${1:-}" ]; then
  OBJETIVO="versiones/$1/sistema-inventario-$1"
else
  [ -L anterior ] || { echo "ERROR: no hay una version anterior registrada"; exit 1; }
  OBJETIVO=$(readlink anterior)
fi
[ -f "$OBJETIVO/sistema-inventario.jar" ] || { echo "ERROR: no existe $OBJETIVO"; exit 1; }

FALLIDA=$(readlink actual 2>/dev/null || echo "ninguna")
ln -sfn "$OBJETIVO" actual
echo "Version retirada: $FALLIDA"
echo "Version restaurada: $(readlink actual)"

java -jar actual/sistema-inventario.jar
java -cp actual/sistema-inventario.jar BusquedaProductos
echo "$(date '+%Y-%m-%d %H:%M:%S') ROLLBACK de $FALLIDA a $OBJETIVO" | tee -a "$LOG"
