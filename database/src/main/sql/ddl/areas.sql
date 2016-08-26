create table areas (
  id SERIAL PRIMARY KEY,
  name VARCHAR(120),
  typ VARCHAR(10),
  region_id INTEGER REFERENCES regions(id),
  code VARCHAR(3) NOT NULL UNIQUE,
  guid VARCHAR(36) NOT NULL UNIQUE,
  okato char(11),
  updated TIMESTAMP not null default now(),
  status char(1) default 'A'::char not null,
  constraint ckc_status_areas check (status = ANY (ARRAY['A'::char, 'H'::char, 'D'::char]))
);

