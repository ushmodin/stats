create table places (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(120),
  typ VARCHAR(10),
  area_id INTEGER REFERENCES areas(id),
  region_id integer REFERENCES regions(id),
  city_id bigint references places(id),
  code VARCHAR(3) NOT NULL UNIQUE,
  guid VARCHAR(36) NOT NULL UNIQUE,
  okato char(11),
  latitude DECIMAL,
  longitude DECIMAL,
  updated TIMESTAMP not null default now(),
  status VARCHAR(1) not null default 'A'::char,
  constraint ckc_status_places check (status = ANY (ARRAY['A'::char, 'H'::char, 'D'::char]))
);

