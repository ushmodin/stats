create table avs_hosts (
  id serial PRIMARY KEY,
  name VARCHAR(150) not NULL,
  descr VARCHAR(300),
  inn VARCHAR (12),
  ip VARCHAR(15),
  guid VARCHAR(36) not NULL UNIQUE,
  created TIMESTAMP not NULL DEFAULT now()
);