# simulacion

API de productos (Spring Boot) usada para simular la estrategia de branching
`task -> feature -> team -> develop -> QA -> release -> main` y detectar conflictos.

## Pipeline de PR (`.github/workflows/pr-validation.yml`)

- **build**: `mvn verify` en todos los PRs.
- **version-check**: la `<version>` del `pom.xml` del PR debe ser **mayor** que la de la rama
  destino. Aplica a PRs hacia `team`, `develop`, `QA`, `release`, `main` (con o sin prefijo `simN/`).

Ambos checks son obligatorios mediante un ruleset de GitHub: un PR en rojo o con conflictos no se puede aceptar.

## Simulaciones

| Prefijo | Flujo |
|---------|-------|
| `sim1/` | Flujo actual del equipo: task desde team, feature desde develop, merges no-ff, ramas reutilizadas |
| `sim2/` | Resolver el estado de sim1 con task-resolution (pasos 8-10 del diagrama) |
| `sim3/` | Flujo de desarrollador del diagrama al pie de la letra, hasta develop |
