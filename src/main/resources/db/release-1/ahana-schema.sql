CREATE TABLE user_profile(
  oid character varying(20) NOT NULL,
  first_name character varying(30) NOT NULL,
  last_name character varying(30) NOT NULL,
  last_logged_on timestamp,
  password character varying(100) NOT NULL,
  password_exp_date timestamp NOT NULL,
  user_id character varying(100) NOT NULL,
  user_status character varying(5) NOT NULL,
  user_type character varying(20),
  account_expired boolean NOT NULL DEFAULT false,
  account_locked boolean NOT NULL DEFAULT false,
  CONSTRAINT user_profile_pkey PRIMARY KEY (oid),
  CONSTRAINT user_profile_user_id_key UNIQUE (user_id));
  
  CREATE TABLE roles(
  oid character varying(20) NOT NULL,
  role_name character varying(30) NOT NULL,
  status character varying(5),
  CONSTRAINT roles_pkey PRIMARY KEY (oid));
  
CREATE TABLE user_roles(
  user_oid character varying(20) NOT NULL,
  role_oid character varying(20) NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_oid, role_oid),
  CONSTRAINT fk7342994993ad290f FOREIGN KEY (role_oid)
  REFERENCES roles (oid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);
  
CREATE TABLE seed_container(
  high_oid bigint DEFAULT 0,
  seed_id character varying(3),
  seq_id serial NOT NULL,
  type character varying(10),
  CONSTRAINT seed_container_pkey PRIMARY KEY (seq_id));
  
CREATE INDEX id_index ON lookup (code,category,description) USING BTREE;
  
  