insert into users (login, password) values ('user1', 'password1');
insert into roles (name) values ('ROLE_LESSOR');
insert into roles (name) values ('ROLE_RENTER');
insert into users_roles (user_id,role_id) values(1,1);
insert into users (login, password) values ('user2', 'password2');
insert into users_roles (user_id,role_id) values(1,1);
insert into users_roles (user_id,role_id) values(2,2);
