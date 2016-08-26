CREATE TABLE stations (
  id        BIGSERIAL PRIMARY KEY,
  name      VARCHAR(200)                NOT NULL,
  address   VARCHAR(300),
  typ       VARCHAR(10)                 NOT NULL,
  area_id   INTEGER REFERENCES areas (id),
  region_id INTEGER REFERENCES regions (id),
  city_id   BIGINT REFERENCES places (id),
  place_id  BIGINT REFERENCES places (id),
  guid      VARCHAR(36)                 NOT NULL UNIQUE,
  latitude  DECIMAL,
  longitude DECIMAL,
  updated   TIMESTAMP DEFAULT now()     NOT NULL,
  status    CHAR(1) DEFAULT 'A' :: CHAR NOT NULL,
  CONSTRAINT ckc_status_stations CHECK (status = ANY (ARRAY ['A' :: CHAR, 'H' :: CHAR, 'D' :: CHAR]))
);