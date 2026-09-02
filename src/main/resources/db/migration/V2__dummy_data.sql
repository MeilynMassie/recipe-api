-- Create chef
insert into chef(username, password, email) values ('melon', '$2a$12$Jx3p.8/MKVi6DYUXCE/XcezJf0btZqDdpmPbAH98jq1xXOUyofyYK', 'melon@gmail.com');
insert into chef(username, password, email) values ('me', '$2a$12$TkgmGwQEC0cVDNniG3B4PuDMsNjGde9rqZHRKhasQB8Ng9ddVG6xC', 'me@gmail.com');

-- Create recipe
insert into recipe(chef_id, name, description, prep_time, cook_time, servings) values (1, 'Cactus Juice', 'It''ll quench ya!', 5, 0, 2);
insert into recipe(chef_id, name, description, prep_time, cook_time, servings) values (1, 'Something dubious', 'No more stomach issues!', 5, 0, 2);

-- Create list of ingredients
insert into ingredient(recipe_id, name, quantity, unit) values (1, 'Cactus', 2, 'spikeys');
insert into ingredient(recipe_id, name, quantity, unit) values (1, 'Water', 6, 'cups from the clouds');
insert into ingredient(recipe_id, name, quantity, unit) values (1, 'Sokka', 1, 'whole sha bang');
insert into ingredient(recipe_id, name, quantity, unit) values (2, 'Mystery Ingredient', 1, 'pinch');


-- Create list of instructions
insert into instruction(recipe_id, step_number, description) values (1, 1, 'Take a machete to the cactus.');
insert into instruction(recipe_id, step_number, description) values (1, 2, 'Blend cactus with water.');
insert into instruction(recipe_id, step_number, description) values (1, 3, 'Strain the mixture.');
insert into instruction(recipe_id, step_number, description) values (1, 4, 'Enjoy with Sokka and have nice dreams.');
insert into instruction(recipe_id, step_number, description) values (2, 1, 'Add mystery ingredient to your dish.');

