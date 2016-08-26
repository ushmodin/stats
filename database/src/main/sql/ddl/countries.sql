CREATE TABLE countries (
    id integer PRIMARY KEY,
    short_name varchar(128) NOT NULL,
    long_name varchar(128),
    short_eng_name varchar(128) NOT NULL,
    oksm numeric(3,0),
    code2 char(2) NOT NULL,
    code3 char(3) NOT NULL,
    iata char(2),
    status char(1) DEFAULT 'A'::char NOT NULL,
    CONSTRAINT ckc_status_countries CHECK ((status = ANY (ARRAY['A'::char, 'H'::char, 'D'::char])))
);

COMMENT ON TABLE countries IS 'Справочник стран';
COMMENT ON COLUMN countries.short_name IS 'Короткое название страны';
COMMENT ON COLUMN countries.long_name IS 'Полное название страны';
COMMENT ON COLUMN countries.short_eng_name IS 'Короткое английское название страны';
COMMENT ON COLUMN countries.oksm IS 'Код ОКСМ';
COMMENT ON COLUMN countries.code2 IS 'Двухбуквенный код страны';
COMMENT ON COLUMN countries.code3 IS 'Трехбуквенный код страны';
COMMENT ON COLUMN countries.iata IS 'Код международной ассоциации воздушного транспорта';
COMMENT ON COLUMN countries.status IS 'Статус записи';

