# Guía de Gestión de Flujo de Trabajo en Tableros Kanban / Scrum

## 1. ¿Qué diferencia existe entre Backlog y Por hacer (To Do)?

* **Backlog:** Es el repositorio general de todas las tareas, requerimientos, ideas y funcionalidades identificadas para el proyecto. Son elementos que **aún no han sido priorizados ni refinados** detalladamente para su ejecución inmediata.
* **Por hacer (To Do):** Contiene un subconjunto de tareas seleccionadas directamente del Backlog para ser ejecutadas en un periodo determinado (por ejemplo, en el Sprint actual). Son tareas que ya están **priorizadas, estimadas y refinadas** (cumplen con la *Definition of Ready*).

---

## 2. ¿Cuándo una tarea debe pasar de "Por hacer" a "En progreso"?

Una tarea debe moverse a **En progreso** cuando:

1. **Hay capacidad disponible:** El responsable (desarrollador/equipo) no excede su límite de trabajo en proceso (WIP - *Work in Progress*).
2. **Está lista para iniciar:** No tiene bloqueos previos ni dependencias sin resolver.
3. **Compromiso activo:** Un miembro del equipo asume la tarea y comienza a trabajar activamente en ella en ese preciso momento.

---

## 3. ¿Por qué una tarea no debería pasar directamente de "En progreso" a "Terminado"?

Saltarse la fase intermedia de revisión vulnera los criterios de calidad y control del proyecto. Una tarea no debe marcarse como **Terminado** directamente porque:

* **Requiere verificación independiente:** Necesita pasar por pruebas (QA) y revisión de código (*Code Review*) para asegurar que cumple los requisitos sin introducir errores (*bugs*).
* **Garantiza la Definition of Done (DoD):** Permite verificar que la entrega cumple con todos los estándares acordados (documentación, pruebas unitarias, compilación exitosa).
* **Fomenta la colaboración:** Evita el sesgo del desarrollador que construyó la solución.

---

## 4. ¿Qué función cumple la columna "En revisión"?

La columna **En revisión** (o *In Review* / *QA*) actúa como un **filtro de control de calidad y validación**. Sus funciones principales son:

* Realizar **revisión de código** por parte de pares (*peer review*).
* Ejecutar **pruebas funcionales y no funcionales** por el equipo de QA.
* Validar que la solución cumpla con los criterios de aceptación fijados por el Product Owner o el cliente.
* Prevenir la llegada de código defectuoso a entornos superiores o producción.

---

## 5. ¿Qué relación tiene este tablero con la idea de entrega continua en DevOps?

El tablero Kanban/Scrum es la **representación visual del flujo de valor** que impulsa la Entrega Continua (*Continuous Delivery*) en DevOps:

* **Visibilidad del flujo:** Muestra el avance del software desde la idea (Backlog) hasta la entrega final en producción.
* **Reducción del WIP:** Al limitar el trabajo en progreso, se reduce el *Lead Time* (tiempo desde que se pide algo hasta que se entrega), acelerando los ciclos de despliegue.
* **Detección de cuellos de botella:** Permite identificar rápidamente dónde se acumula el trabajo (por ejemplo, tareas estancadas en *En revisión*), optimizando el pipeline de integración y despliegue continuo (CI/CD).
* **Entregas pequeñas e incrementales:** Fomenta la división del trabajo en partes pequeñas que pueden ser probadas, integradas y desplegadas en producción de forma rápida y constante.
