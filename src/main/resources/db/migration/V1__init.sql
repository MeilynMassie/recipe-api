-- Sequences
CREATE SEQUENCE chef_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE recipe_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE ingredient_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE instruction_id_seq START WITH 1 INCREMENT BY 1;

-- Chefs
CREATE TABLE chef (
    id BIGINT PRIMARY KEY DEFAULT nextval('chef_id_seq'),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);

-- Recipes
CREATE TABLE recipe (
    id BIGINT PRIMARY KEY DEFAULT nextval('recipe_id_seq'),
    chef_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    prep_time INTEGER,
    cook_time INTEGER,
    servings INTEGER,

    CONSTRAINT fk_recipe_chef
        FOREIGN KEY (chef_id)
        REFERENCES chef(id)
        ON DELETE CASCADE
);

-- Ingredients
CREATE TABLE ingredient (
    id BIGINT PRIMARY KEY DEFAULT nextval('ingredient_id_seq'),
    recipe_id BIGINT NOT NULL,
    quantity VARCHAR(50),
    unit VARCHAR(50),
    name VARCHAR(255) NOT NULL,
    section VARCHAR(50),

    CONSTRAINT fk_ingredient_recipe
        FOREIGN KEY (recipe_id)
        REFERENCES recipe(id)
        ON DELETE CASCADE
);

-- Instructions
CREATE TABLE instruction (
    id BIGINT PRIMARY KEY DEFAULT nextval('instruction_id_seq'),
    recipe_id BIGINT NOT NULL,
    step_number INTEGER NOT NULL,
    description TEXT NOT NULL,

    CONSTRAINT fk_instruction_recipe
        FOREIGN KEY (recipe_id)
        REFERENCES recipe(id)
        ON DELETE CASCADE,

    CONSTRAINT unique_recipe_step
        UNIQUE (recipe_id, step_number)
);