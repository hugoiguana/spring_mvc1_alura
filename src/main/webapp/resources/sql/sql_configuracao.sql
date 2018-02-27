
insert into Role values ('ROLE_ADMIN');
select * from role


-- Senha admin
-- Gerador da senha: https://www.dailycred.com/article/bcrypt-calculator
insert into Usuario (email, nome, senha) 
values ('hugo.iguanaa@gmail.com', 'Administrador', '$2a$04$BiWk51D7GdiTVt81lWaVsuWpSS2grNHsNZJYHOR3jdcWbBvhJndDi')

select * from usuario

insert into Usuario_Role(Usuario_email, roles_nome) values ('hugo.iguanaa@gmail.com', 'ROLE_ADMIN')