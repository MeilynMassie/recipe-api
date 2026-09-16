# Detailed Engineering Notes

## Repo overview

This repository is a Spring Boot REST API for managing chefs, recipes, ingredients, and instructions. The current implementation demonstrates a clean layered architecture and a good base for an MVP-style backend.

## Architecture summary

### Layers

- `controller/` contains the REST endpoints
- `service/` contains business logic
- `repository/` contains Spring Data JPA repositories
- `model/` contains entities and partial update request DTOs
- `exception/` contains centralized exception handling

### Persistence

- PostgreSQL is used for persistence
- Flyway migrations are used for schema setup and seed data
- JPA repositories are used for CRUD operations and common query patterns

## Current endpoint inventory

The API base path is configured as `/api/v1`.

### Chef endpoints

- `GET /api/v1/chef?id={id}`
- `GET /api/v1/chef?username={username}`
- `GET /api/v1/chef/all`
- `POST /api/v1/chef`
- `DELETE /api/v1/chef/{id}`
- `PATCH /api/v1/chef/{id}`

### Recipe endpoints

- `GET /api/v1/recipe/{chefId}`
- `GET /api/v1/recipe/{chefId}/{recipeId}`
- `POST /api/v1/recipe/{chefId}`
- `DELETE /api/v1/recipe/{chefId}/{recipeId}`
- `PATCH /api/v1/recipe/{chefId}/{recipeId}`

### Ingredient endpoints

- `GET /api/v1/recipe/{recipeId}/ingredient`
- `GET /api/v1/recipe/{recipeId}/ingredient/{ingredientId}`
- `POST /api/v1/recipe/{recipeId}/ingredient`
- `DELETE /api/v1/recipe/{recipeId}/ingredient/{ingredientId}`
- `PATCH /api/v1/recipe/{recipeId}/ingredient/{ingredientId}`

### Instruction endpoints

- `GET /api/v1/recipe/{recipeId}/instruction`
- `GET /api/v1/recipe/{recipeId}/instruction/{instructionId}`
- `POST /api/v1/recipe/{recipeId}/instruction`
- `DELETE /api/v1/recipe/{recipeId}/instruction/{instructionId}`
- `PATCH /api/v1/recipe/{recipeId}/instruction/{instructionId}`

## Implemented behavior

### Chef handling

The chef flow includes:

- fetch by id
- fetch by username
- fetch all chefs
- create
- delete
- partial update

### Recipe handling

The recipe flow includes:

- fetch recipes for a chef
- fetch a single recipe
- create a recipe tied to an existing chef
- delete a recipe
- partial update on recipe details

Recipe creation also links nested ingredients and instructions to the parent recipe instance.

### Ingredient handling

The ingredient flow includes:

- fetch ingredients by recipe
- fetch a single ingredient by id and recipe
- create ingredient for a recipe
- delete ingredient
- partial update on ingredient fields

### Instruction handling

The instruction flow includes:

- fetch instructions by recipe
- fetch a single instruction by id and recipe
- create instruction for a recipe
- delete instruction
- partial update on instruction fields

## Current strengths

- Clear separation of concerns across layers
- Use of Flyway for schema evolution
- Good use of Spring Data repositories
- Partial update DTOs make update operations simpler
- Standardized `ResponseEntity` success responses across controllers
- Centralized JSON error handling through `GlobalExceptionHandler`
- Automated test coverage already exists and is growing

## Response contract now in place

The API now uses a consistent success and error response convention.

### Success responses

- `GET` requests return `200 OK`
- `POST` requests return `201 Created`
- `PATCH` requests return `204 No Content`
- `DELETE` requests return `204 No Content`

### Error responses

Errors are handled centrally through `GlobalExceptionHandler` and return a consistent JSON envelope:

```json
{
  "timestamp": "2026-09-14T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Recipe not found with id: 123",
  "path": "/api/v1/recipe/1",
  "details": {}
}
```

Validation errors use the same envelope with field-level details where available.

## Current concerns and recommended follow-up

### 1. Empty collection semantics

The repository now treats a valid parent resource with no child rows as a normal empty list response rather than a not-found error. This behavior is implemented in the recipe, ingredient, and instruction service methods.

### 2. Ownership validation

Ingredient and instruction updates should consistently validate recipe ownership, not just the target entity id. This is important for preventing accidental cross-recipe edits.

### 3. Validation strategy

Validation is intentionally applied to create flows and fully formed payloads. Partial update DTOs remain intentionally non-validating, which aligns with the repository's current design.

### 4. Integration and contract testing

The repo currently has good unit coverage, but additional HTTP-level integration tests would help verify response codes, payloads, and error mapping end-to-end.

### 5. Security and local configuration

The current project intentionally keeps local development setup lightweight. That is fine for development; however, production security and configuration management would need to be addressed later