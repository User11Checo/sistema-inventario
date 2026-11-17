# Historial de versiones

Todas las versiones se generan automáticamente con el workflow `.github/workflows/release.yml`, al enviar una etiqueta `v*` o al ejecutarlo manualmente desde Actions indicando la versión.

## v1.0.2 – Sistema de Inventario
- Corrección del error en `mostrarInformacion()` que mostraba el precio multiplicado por 10.
- Validación satisfactoria de la funcionalidad Producto: 7 de 7 pruebas correctas.
- Paquete `sistema-inventario-v1.0.2.zip` publicado en GitHub Releases.

## v1.0.1 – No publicada
- Se introdujo intencionalmente un error en la visualización del precio.
- Las pruebas CP04 y CP06 fallaron durante la liberación y el workflow se detuvo sin publicar la versión.
- Como las pruebas fallaron, no se creó la etiqueta ni el Release v1.0.1; el código corregido se liberó como v1.0.2.

## v1.0.0 – Sistema de Inventario
- Primera versión del Sistema de Inventario.
- Incorporación de la funcionalidad Producto: código, nombre, precio, existencia y `mostrarInformacion()`.
- Búsqueda de productos por código y por nombre (`BusquedaProductos`).
- Implementación de pruebas automatizadas con JUnit 4 (CP01–CP07).
- Workflows de GitHub Actions para pruebas (`pruebas.yml`), construcción (`build.yml`) y liberación (`release.yml`).
