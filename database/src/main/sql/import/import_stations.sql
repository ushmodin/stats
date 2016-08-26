insert into stations (name, area_id, region_id, city_id, place_id, guid)
    SELECT p.name, p.area_id, p.region_id, p.city_id, p.id, p.guid
    FROM places p
    where
      p.status = 'A'
      and not exists(
          SELECT *
          FROM stations s
          where p.id = s.place_id
    );
