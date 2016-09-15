create table st_reqs (
  id BIGSERIAL PRIMARY KEY
  ,owner_host_id INTEGER REFERENCES avs_hosts(id)
  ,station_id BIGINT REFERENCES stations(id)
  ,host_id VARCHAR(36) not NULL
  ,name VARCHAR(200) not NULL
  ,description VARCHAR(300)
  ,okato VARCHAR(11)
  ,country_guid VARCHAR(36)
  ,country_name VARCHAR(300)
  ,region_guid VARCHAR(36)
  ,region_name VARCHAR(300)
  ,area_guid VARCHAR(36)
  ,area_name VARCHAR(300)
  ,city_guid VARCHAR(36)
  ,city_name VARCHAR(300)
  ,latitude DECIMAL
  ,longitude DECIMAL
  ,created TIMESTAMP not NULL DEFAULT now()
)