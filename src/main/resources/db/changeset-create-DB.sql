-- drops

DROP TABLE IF EXISTS public.users_roles;

DROP SEQUENCE IF EXISTS public.users_roles_id_seq;

DROP TABLE IF EXISTS public.users CASCADE;

DROP SEQUENCE IF EXISTS public.users_id_seq;

DROP TABLE IF EXISTS public.roles;

DROP SEQUENCE IF EXISTS public.roles_id_seq;

DROP TABLE IF EXISTS public.tools CASCADE;

DROP SEQUENCE IF EXISTS public.tools_id_seq;

DROP TABLE IF EXISTS public.orders CASCADE ;

DROP SEQUENCE IF EXISTS public.orders_id_seq;

DROP TABLE IF EXISTS public.addresses CASCADE;

DROP SEQUENCE IF EXISTS public.addresses_id_seq;

DROP TABLE IF EXISTS public.persons;

DROP SEQUENCE IF EXISTS public.persons_id_seq;


-- creates

CREATE SEQUENCE public.users_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_id_seq'),
    login varchar NOT NULL,
    password varchar NOT NULL DEFAULT 'password',
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

CREATE SEQUENCE IF NOT EXISTS public.roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.roles
(
    role_id integer NOT NULL DEFAULT nextval('roles_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

CREATE SEQUENCE IF NOT EXISTS public.users_roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_role_id integer NOT NULL DEFAULT nextval('users_roles_id_seq'),
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_role_id),
    CONSTRAINT users_roles_roles_fkey
          FOREIGN KEY(role_id)
    	  REFERENCES public.roles(role_id)ON DELETE CASCADE,
    CONSTRAINT users_roles_users_fkey
              FOREIGN KEY(user_id)
        	  REFERENCES public.users(user_id)ON DELETE CASCADE
);
-- //---------------------------------------------------------------------------------


CREATE SEQUENCE public.persons_id_seq
    INCREMENT 1
    START WITH 1;
CREATE TABLE IF NOT EXISTS public.persons
(
    person_id integer NOT NULL  DEFAULT nextval('persons_id_seq'),
    user_id integer NOT NULL,
	name varchar NOT NULL,
	phone_number varchar NOT NULL,
	CONSTRAINT persons_pkey PRIMARY KEY (person_id),
	  CONSTRAINT person_user_fkey
          FOREIGN KEY(user_id)
    	  REFERENCES public.users(user_id)
    	  ON DELETE CASCADE
);

CREATE SEQUENCE public.addresses_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id integer NOT NULL  DEFAULT nextval('addresses_id_seq'),
    district varchar NOT NULL,
    street varchar NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (address_id)
);

CREATE SEQUENCE public.tools_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.tools
(
    tool_id integer NOT NULL DEFAULT nextval('tools_id_seq'),
    name varchar NOT NULL,
	specifications varchar,
	address_id integer NOT NULL,
	person_id integer NOT NULL,
    price real NOT NULL DEFAULT 0.0,
    CONSTRAINT tools_pkey PRIMARY KEY (tool_id),
	 CONSTRAINT tools_address_fkey
          FOREIGN KEY(address_id)
    	  REFERENCES public.addresses(address_id)
    	  ON DELETE CASCADE,
     CONSTRAINT tools_persons_fkey
              FOREIGN KEY(person_id)
        	  REFERENCES public.persons(person_id)
        	  ON DELETE CASCADE
);


CREATE SEQUENCE public.orders_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id integer NOT NULL DEFAULT nextval('orders_id_seq'),
    person_id integer NOT NULL,
	tool_id integer NOT NULL,
	start_date date NOT NULL,
    stop_date date NOT NULL,
	stopped boolean NOT NULL DEFAULT FALSE,
	completed boolean NOT NULL DEFAULT FALSE,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
	 CONSTRAINT orders_persons_fkey
          FOREIGN KEY(person_id)
    	  REFERENCES public.persons(person_id)
    	  ON DELETE CASCADE,
	 CONSTRAINT orders_tools_fkey
          FOREIGN KEY(tool_id)
    	  REFERENCES public.tools(tool_id)
    	  ON DELETE CASCADE
);