select * from chef;
select * from recipe
where id=3;
select * from instruction
where id = 3;
select * from ingredient
where id = 6;


select * from recipe r
join ingredient i on r.id = i.recipe_id
where r.id = 3;

select * from recipe r
join instruction i on r.id = i.recipe_id
where r.id = 3;
